
package steamcraft.common.tiles;

import java.util.ArrayList;

import boilerplate.api.IOpenableGUI;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.api.tile.ISpannerTile;
import steamcraft.client.gui.GuiChangeExtractions;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitPackets;
import steamcraft.common.packets.CopperPipeFluidPacket;
import steamcraft.common.packets.CopperPipePacket;
import steamcraft.common.tiles.container.ContainerChangeExtractions;

/**
 * @author decebaldecebal
 *
 */
public class TileCopperPipe extends TileEntity implements IFluidHandler, ISpannerTile, IOpenableGUI
{
	private static int ticksTillFluidUpdate = 200; // update the fluid in pipe
													// every 10 seconds

	private static int copperPipeCapacity = 500;
	private static int copperPipeExtract = 50;
	private static int copperPipeTransfer = 200;

	protected int pipeCapacity = copperPipeCapacity;
	protected int pipeExtract = copperPipeExtract;
	protected int pipeTransfer = copperPipeTransfer;

	public FluidNetwork network;
	private boolean isMaster = false;

	public Fluid fluidInPipe;
	public float fluidScaled = 0;
	private int ticksSinceUpdate = ticksTillFluidUpdate / 2; // first time
																// update faster

	public ForgeDirection[] connections = new ForgeDirection[6];
	public ForgeDirection[] extractions = new ForgeDirection[6];
	private Coords masterCoords = null;

	private static float pixel = 1F / 16f;

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.isMaster)
			{
				if (this.network.updateNetworkForPipes)
				{
					this.network.updateNetworkForPipes = false;
					this.updateConnections();
				}

				this.network.updateNetwork(this);
			}

			this.ticksSinceUpdate++;

			if (this.network != null)
				if (this.network.fluidScaled != this.fluidScaled)
					this.updateClientFluid();
				else if (this.ticksSinceUpdate >= ticksTillFluidUpdate)
				{
					this.ticksSinceUpdate = 0;
					this.updateClientFluid();
				}
		}
	}

	private void updateClientFluid()
	{
		if (this.network.tank.getFluid() != null)
		{
			this.fluidScaled = this.network.fluidScaled;

			InitPackets.network.sendToAllAround(
					new CopperPipeFluidPacket(this.xCoord, this.yCoord, this.zCoord, this.network.fluidScaled,
							this.network.tank.getFluid().getFluid().getName()),
					new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 50));
		}
		else
		{
			this.fluidScaled = 0;

			InitPackets.network.sendToAllAround(new CopperPipeFluidPacket(this.xCoord, this.yCoord, this.zCoord, 0, "water"),
					new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 50));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagList extractions = new NBTTagList();

		for (int i = 0; i < 6; i++)
			if (this.extractions[i] != null)
			{
				NBTTagCompound conn = new NBTTagCompound();
				conn.setByte("index", (byte) i);
				writeDirectionToNBT(conn, this.extractions[i]);

				extractions.appendTag(conn);
			}

		tag.setTag("extractions", extractions);
		tag.setBoolean("master", this.isMaster);

		if (this.isMaster)
			this.network.writeToNBT(tag);
	}

	private static void writeDirectionToNBT(NBTTagCompound tag, ForgeDirection dir)
	{
		byte index = -1;

		if (dir != null)
			switch (dir)
			{
			case DOWN:
				index = 0;
				break;
			case UP:
				index = 1;
				break;
			case NORTH:
				index = 2;
				break;
			case SOUTH:
				index = 3;
				break;
			case WEST:
				index = 4;
				break;
			case EAST:
				index = 5;
				break;
			default:
				index = -1;
				break;
			}

		tag.setByte("dirIndex", index);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.extractions = new ForgeDirection[6];

		NBTTagList extractions = tag.getTagList("extractions", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < extractions.tagCount(); i++)
		{
			NBTTagCompound dirTag = extractions.getCompoundTagAt(i);

			this.extractions[dirTag.getByte("index")] = readDirectionFromNBT(dirTag);
		}

		this.isMaster = tag.getBoolean("master");

		if (this.isMaster)
		{
			this.network = FluidNetwork.readFromNBT(tag, this.pipeCapacity, this.pipeExtract, this.pipeTransfer);
			this.setMaster(this);
		}
	}

	private static ForgeDirection readDirectionFromNBT(NBTTagCompound tag)
	{
		byte index = tag.getByte("dirIndex");

		ForgeDirection dir = ForgeDirection.getOrientation(index);

		if (dir == ForgeDirection.UNKNOWN)
			dir = null;

		return dir;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();

		NBTTagList extractions = new NBTTagList();

		for (int i = 0; i < 6; i++)
			if (this.extractions[i] != null)
			{
				NBTTagCompound conn = new NBTTagCompound();
				conn.setByte("index", (byte) i);
				writeDirectionToNBT(conn, this.extractions[i]);

				extractions.appendTag(conn);
			}

		NBTTagList connections = new NBTTagList();

		for (int i = 0; i < 6; i++)
			if (this.connections[i] != null)
			{
				NBTTagCompound conn = new NBTTagCompound();
				conn.setByte("index", (byte) i);
				writeDirectionToNBT(conn, this.connections[i]);

				connections.appendTag(conn);
			}

		tag.setTag("extractions", extractions);
		tag.setTag("connections", connections);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		this.extractions = new ForgeDirection[6];

		NBTTagList extractions = packet.func_148857_g().getTagList("extractions", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < extractions.tagCount(); i++)
		{
			NBTTagCompound tag = extractions.getCompoundTagAt(i);

			this.extractions[tag.getByte("index")] = readDirectionFromNBT(tag);
		}

		this.connections = new ForgeDirection[6];

		NBTTagList connections = packet.func_148857_g().getTagList("connections", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < connections.tagCount(); i++)
		{
			NBTTagCompound tag = connections.getCompoundTagAt(i);

			this.connections[tag.getByte("index")] = readDirectionFromNBT(tag);
		}
	}

	@Override
	public void changeExtraction()
	{
		if (!this.worldObj.isRemote)
		{
			for (int i = 0; i < 6; i++)
			{
				ForgeDirection dir = this.connections[i];

				if ((dir != null) && this.isFluidHandler(dir))
				{
					Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

					if (this.extractions[i] == null)
					{
						this.extractions[i] = dir;

						this.network.outputs.remove(temp);

						if (!this.network.inputs.contains(temp))
							this.network.inputs.add(temp);

						break;
					}
					else
					{
						this.extractions[i] = null;

						this.network.inputs.remove(temp);

						if (!this.network.outputs.contains(temp))
							this.network.outputs.add(temp);
					}
				}
			}
			this.updateClientConnections();
		}
	}

	@Override
	public void changeExtraction(int dirIndex)
	{
		if (!this.worldObj.isRemote)
		{
			ForgeDirection dir = this.connections[dirIndex];

			if (dir != null)
			{
				Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

				if (this.extractions[dirIndex] == null)
				{
					this.extractions[dirIndex] = this.connections[dirIndex];

					this.network.outputs.remove(temp);

					if (!this.network.inputs.contains(temp))
						this.network.inputs.add(temp);
				}
				else
				{
					this.extractions[dirIndex] = null;

					this.network.inputs.remove(temp);

					if (!this.network.outputs.contains(temp))
						this.network.outputs.add(temp);
				}

				this.updateClientConnections();
			}
		}
	}

	@Override
	public ForgeDirection[] getExtractableConnections()
	{
		ForgeDirection[] extractableConnections = new ForgeDirection[6];

		for (int i = 0; i < 6; i++)
		{
			ForgeDirection dir = this.connections[i];
			if ((dir != null) && this.isFluidHandler(dir))
				extractableConnections[i] = dir;
		}

		return extractableConnections;
	}

	@Override
	public ForgeDirection[] getExtractions()
	{
		return this.extractions;
	}

	private void removeConnections(int i)
	{
		if ((this.connections[i] != null) && !this.worldObj.isRemote)
		{
			ForgeDirection dir = this.connections[i];

			Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

			this.network.outputs.remove(temp);
			this.network.inputs.remove(temp);

			this.extractions[i] = null;
			this.connections[i] = null;
		}
	}

	public void updateConnections()
	{
		if (!this.worldObj.isRemote)
		{
			byte flag = 0;
			if (this.canConnect(ForgeDirection.DOWN))
			{
				flag = this.updateNetwork(ForgeDirection.DOWN);
				if (flag == -1)
					return;
				else if (flag == 1)
					this.connections[0] = ForgeDirection.DOWN;
			}
			else
				this.removeConnections(0);

			if (this.canConnect(ForgeDirection.UP))
			{
				flag = this.updateNetwork(ForgeDirection.UP);
				if (flag == -1)
					return;
				else if (flag == 1)
					this.connections[1] = ForgeDirection.UP;
			}
			else
				this.removeConnections(1);

			if (this.canConnect(ForgeDirection.NORTH))
			{
				flag = this.updateNetwork(ForgeDirection.NORTH);
				if (flag == -1)
					return;
				else if (flag == 1)
					this.connections[2] = ForgeDirection.NORTH;
			}
			else
				this.removeConnections(2);

			if (this.canConnect(ForgeDirection.SOUTH))
			{
				flag = this.updateNetwork(ForgeDirection.SOUTH);
				if (flag == -1)
					return;
				else if (flag == 1)
					this.connections[3] = ForgeDirection.SOUTH;
			}
			else
				this.removeConnections(3);

			if (this.canConnect(ForgeDirection.WEST))
			{
				flag = this.updateNetwork(ForgeDirection.WEST);
				if (flag == -1)
					return;
				else if (flag == 1)
					this.connections[4] = ForgeDirection.WEST;
			}
			else
				this.removeConnections(4);

			if (this.canConnect(ForgeDirection.EAST))
			{
				flag = this.updateNetwork(ForgeDirection.EAST);
				if (flag == -1)
					return;
				else if (flag == 1)
					this.connections[5] = ForgeDirection.EAST;
			}
			else
				this.removeConnections(5);

			if (this.network == null)
			{
				this.network = new FluidNetwork(1, this.pipeCapacity, this.pipeExtract, this.pipeTransfer);
				this.setMaster(this);
			}

			for (int i = 0; i < 6; i++)
			{
				ForgeDirection dir = this.connections[i];
				if ((dir != null) && this.isFluidHandler(dir))
				{
					Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

					if (this.extractions[i] == null)
					{
						if (!this.network.outputs.contains(temp))
							this.network.outputs.add(temp);
					}
					else if (!this.network.inputs.contains(temp))
						this.network.inputs.add(temp);
				}
			}

			this.updateClientConnections();
		}
	}

	private void updateClientConnections()
	{
		if (this.network != null)
		{
			InitPackets.network.sendToAllAround(new CopperPipePacket(this.xCoord, this.yCoord, this.zCoord, this.connections, this.extractions),
					new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 100));
		}
	}

	private byte updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);

		if (tile instanceof TileCopperPipe)
		{
			TileCopperPipe pipe = (TileCopperPipe) tile;

			if (pipe.network != null) // Should only happen on world load
			{
				if (!pipe.network.equals(this.network))
				{
					if (this.network == null)
					{
						this.setMaster(pipe.getMaster());
						this.network.changeSize(1);

						pipe.updateOneConnection(dir.getOpposite());
					}
					else
					{
						if ((this.network.tank.getFluid() == null) || (pipe.network.tank.getFluid() == null))
						{
							FluidNetwork temp = this.network;

							pipe.network = this.network = pipe.network.tank.getFluid() != null ? pipe.network : this.network;

							this.network.changeSize(1);

							if (!temp.equals(this.network))
							{
								this.setMaster(pipe.getMaster());
								pipe.updateOneConnection(dir.getOpposite());
								this.updateConnections();
								return -1;
							}
							else
							{
								pipe.setMaster(this.getMaster());
								pipe.updateConnections();
							}
						}
						else if (this.network.tank.getFluid().getFluid().equals(pipe.network.tank.getFluid().getFluid()))
						{
							FluidStack fluid = new FluidStack(this.network.tank.getFluid(),
									this.network.tank.getFluidAmount() + pipe.network.tank.getFluidAmount());

							if (this.network.size > pipe.network.size)
							{
								pipe.setMaster(this.getMaster());

								this.network.tank.setFluid(fluid);
								this.network.changeSize(1);

								pipe.updateConnections();
							}
							else
							{
								this.setMaster(pipe.getMaster());

								this.network.tank.setFluid(fluid);
								this.network.changeSize(1);

								pipe.updateOneConnection(dir.getOpposite());
								this.updateConnections();
								return -1;
							}
						}
						else
							return 0;
					}
				}
				else
					pipe.updateOneConnection(dir.getOpposite());
			}
			else if (this.network != null)
			{
				pipe.setMaster(this.getMaster());
				this.network.changeSize(1);
				pipe.updateConnections();
			}
		}
		return 1;
	}

	private void updateOneConnection(ForgeDirection dir)
	{
		int index = 0;

		switch (dir)
		{
		case DOWN:
			index = 0;
			break;
		case UP:
			index = 1;
			break;
		case NORTH:
			index = 2;
			break;
		case SOUTH:
			index = 3;
			break;
		case WEST:
			index = 4;
			break;
		case EAST:
			index = 5;
			break;
		default:
			index = -1;
			break;
		}

		this.connections[index] = dir;

		this.updateClientConnections();
	}

	public void removeFromNetwork()
	{
		if (this.network != null)
		{
			this.network.changeSize(-1);

			if (this.network.size != 0)
			{
				FluidStack fluid = this.network.tank.getFluid();

				for (ForgeDirection dir : this.connections)
					if (dir != null)
						if (this.isPipe(dir))
						{
							TileCopperPipe pipe = (TileCopperPipe) this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
									this.zCoord + dir.offsetZ);

							pipe.network.setSize(0);
							pipe.network.tank.setFluid(null);

							pipe.network = new FluidNetwork(1, this.pipeCapacity, this.pipeExtract, this.pipeTransfer);
							pipe.setMaster(pipe);
							if (this.network != null)
							{
								pipe.network.tempFluid = fluid;
								this.network = null;
							}
						}
			}
			this.setMaster(null);
		}
	}

	public void setMaster(TileCopperPipe master)
	{
		if (master != null)
		{
			this.masterCoords = new Coords(master.xCoord, master.yCoord, master.zCoord, null);
			this.network = master.network;
			if (master == this)
				this.isMaster = true;
			else
				this.isMaster = false;
		}
		else
		{
			this.masterCoords = null;
			this.isMaster = false;
			this.network = null;
		}
	}

	public TileCopperPipe getMaster()
	{
		if (this.masterCoords != null)
			return (TileCopperPipe) this.worldObj.getTileEntity(this.masterCoords.x, this.masterCoords.y, this.masterCoords.z);
		return null;
	}

	protected boolean canConnect(ForgeDirection dir)
	{
		return this.isPipe(dir) || this.isFluidHandler(dir);
	}

	public boolean isPipe(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) == InitBlocks.blockCopperPipe;
	}

	protected boolean isFluidHandler(ForgeDirection dir)
	{
		TileEntity te = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		return (te instanceof IFluidHandler) && (((IFluidHandler) te).getTankInfo(dir.getOpposite()) != null) && !this.isPipe(dir)
				&& !this.isSteelPipe(dir);
	}

	protected boolean isSteelPipe(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) == InitBlocks.blockSteelPipe;
	}

	@SideOnly(Side.CLIENT)
	public ForgeDirection onlyOneOpposite()
	{
		ForgeDirection main = null;
		boolean isOpposite = false;

		for (ForgeDirection dir : this.connections)
		{
			if ((main == null) && (dir != null))
				main = dir;

			if ((dir != null) && (main != dir))
				if (dir.getOpposite() != main)
					return null;
				else
					isOpposite = true;
		}

		if (isOpposite)
			return main;

		return null;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return false;

		return (this.network != null) && ((this.network.tank.getFluid() == null) || (this.network.tank.getFluid().getFluid() == fluid));
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return false;

		return (this.network != null) && ((this.network.tank.getFluid() == null) || (this.network.tank.getFluid().getFluid() == fluid));
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return null;

		if ((this.network != null) && (this.network.tank.getFluid() != null) && this.network.tank.getFluid().isFluidEqual(resource))
		{
			int amount = Math.min(resource.amount, this.pipeTransfer);

			return this.network.tank.drain(amount, doDrain);
		}

		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return null;

		if ((this.network != null) && (this.network.tank.getFluid() != null))
		{
			int amount = Math.min(maxDrain, this.pipeTransfer);

			return this.network.tank.drain(amount, doDrain);
		}

		return null;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return 0;

		if ((this.network != null))
		{
			int amount = Math.min(resource.amount, this.pipeExtract);

			return this.network.tank.fill(new FluidStack(resource, amount), doFill);
		}

		return 0;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		if (this.network != null)
			return new FluidTankInfo[] { this.network.tank.getInfo() };

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiChangeExtractions(world.getTileEntity(x, y, z), player.dimension);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerChangeExtractions();
	}

	public static class FluidNetwork
	{
		private int capacityPerPipe;
		private int maxExtractPerTile;
		private int maxTransferPerTile;

		public boolean updateNetworkForPipes = false;

		private FluidTank tank;
		private int size;
		private float fluidScaled = 0;
		private FluidStack tempFluid = null;

		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();

		public FluidNetwork(int size, int pipeCapacity, int pipeExtract, int pipeTransfer)
		{
			this.size = size;

			this.capacityPerPipe = pipeCapacity;
			this.maxExtractPerTile = pipeExtract;
			this.maxTransferPerTile = pipeTransfer;
			this.tank = new FluidTank(this.capacityPerPipe * size);
		}

		public void updateNetwork(TileCopperPipe pipe)
		{
			this.updateFluidScaled();

			if (this.tempFluid != null)
			{
				this.tempFluid.amount = Math.min(this.tempFluid.amount, this.tank.getCapacity());
				this.tank.setFluid(this.tempFluid);
				this.tempFluid = null;
			}

			if (this.tank.getFluidAmount() == 0)
				this.tank.setFluid(null);

			this.updateInputs(pipe.worldObj);
			this.updateOutputs(pipe);
		}

		private void updateFluidScaled()
		{
			if (this.tank.getFluid() != null)
			{
				this.fluidScaled = (this.tank.getFluidAmount() / (float) (this.size * this.capacityPerPipe)) * pixel;

				if (this.fluidScaled > pixel)
					this.fluidScaled = pixel;
			}
			else
				this.fluidScaled = 0;

		}

		private void updateInputs(World world)
		{
			int distribute = 0;
			int tempSize = this.inputs.size();

			for (Coords coords : this.inputs)
			{
				if (tempSize != 0)
					distribute = (this.tank.getCapacity() - this.tank.getFluidAmount()) / tempSize;
				else
					break;

				if (coords != null)
				{
					if (this.tank.getFluidAmount() != this.tank.getCapacity())
					{
						if (world.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) world.getTileEntity(coords.x, coords.y, coords.z);
							if ((tile != null) && (tile.getTankInfo(coords.dir) != null))
							{
								for (FluidTankInfo info : tile.getTankInfo(coords.dir))
								{
									if ((info.fluid != null) && tile.canDrain(coords.dir, info.fluid.getFluid()))
									{
										int canFill = this.tank
												.fill(new FluidStack(info.fluid.getFluid(), Math.min(distribute, this.maxExtractPerTile)), false);

										this.tank.fill(tile.drain(coords.dir, canFill, true), true);

										tempSize--;
									}
								}
							}
						}
					}
					else
						break;
				}
			}
		}

		private void updateOutputs(TileCopperPipe pipe)
		{
			int distribute = 0;
			int tempSize = this.outputs.size();

			for (Coords coords : this.outputs)
			{
				if (tempSize != 0)
					distribute = this.tank.getFluidAmount() / tempSize;
				else
					break;

				if (coords != null)
				{
					if (this.tank.getFluidAmount() > 0)
					{
						if (pipe.worldObj.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) pipe.worldObj.getTileEntity(coords.x, coords.y, coords.z);

							if (tile != null)
							{
								short transfered = 0;

								if (tile.canFill(coords.dir, this.tank.getFluid().getFluid()))
									transfered = (short) tile.fill(coords.dir,
											new FluidStack(this.tank.getFluid(), Math.min(distribute, this.maxTransferPerTile)), true);

								this.tank.drain(transfered, true);

								tempSize--;
							}
						}
					}
					else
						break;

					if (tempSize == (this.outputs.size() / 2))
						this.updateFluidScaled();
				}
			}
		}

		public void setSize(int size)
		{
			this.size = size;
			this.tank.setCapacity(this.capacityPerPipe * this.size);
		}

		public void changeSize(int with)
		{
			this.size += with;
			this.tank.setCapacity(this.capacityPerPipe * this.size);
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();

			temp.setFloat("fluidScaled", this.fluidScaled);
			this.tank.writeToNBT(temp);

			tag.setTag("network", temp);
		}

		public static FluidNetwork readFromNBT(NBTTagCompound tag, int pipeCapacity, int pipeExtract, int pipeTransfer)
		{
			NBTTagCompound temp = tag.getCompoundTag("network");

			FluidNetwork network = new FluidNetwork(1, pipeCapacity, pipeExtract, pipeTransfer);

			network.fluidScaled = temp.getFloat("fluidScaled");
			network.tank.readFromNBT(temp);
			network.updateNetworkForPipes = true;

			return network;
		}
	}

	public static class Coords
	{
		public int x;
		public int y;
		public int z;
		public ForgeDirection dir;

		public Coords(int x, int y, int z, ForgeDirection dir)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.dir = dir;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (obj instanceof Coords)
			{
				Coords coord = (Coords) obj;
				if ((this.x == coord.x) && (this.y == coord.y) && (this.z == coord.z) && (this.dir == coord.dir))
					return true;
			}
			return false;
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			tag.setInteger("coordX", this.x);
			tag.setInteger("coordY", this.y);
			tag.setInteger("coordZ", this.z);

			writeDirectionToNBT(tag, this.dir);
		}

		public static Coords readFromNBT(NBTTagCompound tag)
		{
			return new Coords(tag.getInteger("coordX"), tag.getInteger("coordY"), tag.getInteger("coordZ"), readDirectionFromNBT(tag));
		}
	}
}