/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [1 Apr 2014, 22:27:36]
 */
package common.steamcraft.common.block.tile;

import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.steamcraft.common.util.SafeTimeTracker;
import common.steamcraft.common.util.Tank;
import common.steamcraft.common.util.TankManager;

/**
 * @author warlordjones
 *
 * 1 Apr 201422:27:36
 */
public class TileEntityCopperTank extends TileEntity implements IFluidHandler{
	public final Tank tank = new Tank("copperTank", FluidContainerRegistry.BUCKET_VOLUME * 16, this);
	public final TankManager tankManager = new TankManager(tank);
	public boolean hasUpdate = false;
	public SafeTimeTracker tracker = new SafeTimeTracker();
	private int prevLightValue = 0;

	/* UPDATING */
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			int lightValue = getFluidLightLevel();
			if (prevLightValue != lightValue) {
				prevLightValue = lightValue;
				worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
			}
			return;
		}

		// Have liquid flow down into tanks below if any.
		if (tank.getFluid() != null) {
			moveFluidBelow();
		}

		if (hasUpdate && tracker.markTimeIfDelay(worldObj, 2)) {
			//sendNetworkUpdate();
			hasUpdate = false;
		}
	}
	/* SAVING & LOADING */
	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tankManager.readFromNBT(data);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tankManager.writeToNBT(data);
	}

	/* HELPER FUNCTIONS */
	/**
	 * @return Last tank block below this one or this one if it is the last.
	 */
	public TileEntityCopperTank getBottomTank() {

		TileEntityCopperTank lastTank = this;

		while (true) {
			TileEntityCopperTank below = getTankBelow(lastTank);
			if (below != null) {
				lastTank = below;
			} else {
				break;
			}
		}

		return lastTank;
	}

	public TileEntityCopperTank getTopTank() {

		TileEntityCopperTank lastTank = this;

		while (true) {
			TileEntityCopperTank above = getTankAbove(lastTank);
			if (above != null) {
				lastTank = above;
			} else {
				break;
			}
		}

		return lastTank;
	}

	public static TileEntityCopperTank getTankBelow(TileEntityCopperTank tile) {
		TileEntity below = tile.getWorldObj().getBlockTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);
		if (below instanceof TileEntityCopperTank) {
			return (TileEntityCopperTank) below;
		} else {
			return null;
		}
	}

	public static TileEntityCopperTank getTankAbove(TileEntityCopperTank tile) {
		TileEntity above = tile.getWorldObj().getBlockTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);
		if (above instanceof TileEntityCopperTank) {
			return (TileEntityCopperTank) above;
		} else {
			return null;
		}
	}

	public void moveFluidBelow() {
		TileEntityCopperTank below = getTankBelow(this);
		if (below == null) {
			return;
		}

		int used = below.tank.fill(tank.getFluid(), true);
		if (used > 0) {
			hasUpdate = true; // not redundant because tank.drain operates on an IFluidTank, not a tile
			below.hasUpdate = true; // redundant because below.fill sets hasUpdate

			tank.drain(used, true);
		}
	}

	/* ITANKCONTAINER */
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		resource = resource.copy();
		int totalUsed = 0;
		TileEntityCopperTank tankToFill = getBottomTank();

		FluidStack liquid = tankToFill.tank.getFluid();
		if (liquid != null && liquid.amount > 0 && !liquid.isFluidEqual(resource)) {
			return 0;
		}

		while (tankToFill != null && resource.amount > 0) {
			int used = tankToFill.tank.fill(resource, doFill);
			resource.amount -= used;
			if (used > 0) {
				tankToFill.hasUpdate = true;
			}

			totalUsed += used;
			tankToFill = getTankAbove(tankToFill);
		}
		return totalUsed;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxEmpty, boolean doDrain) {
		TileEntityCopperTank bottom = getBottomTank();
		bottom.hasUpdate = true;
		return bottom.tank.drain(maxEmpty, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		if (!resource.isFluidEqual(tank.getFluid()))
			return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {
		FluidTank compositeTank = new FluidTank(tank.getCapacity());

		TileEntityCopperTank tile = getBottomTank();

		int capacity = tank.getCapacity();

		if (tile != null && tile.tank.getFluid() != null) {
			compositeTank.setFluid(tile.tank.getFluid().copy());
		} else {
			return new FluidTankInfo[]{compositeTank.getInfo()};
		}

		tile = getTankAbove(tile);

		while (tile != null) {

			FluidStack liquid = tile.tank.getFluid();
			if (liquid == null || liquid.amount == 0) {
				// NOOP
			} else if (!compositeTank.getFluid().isFluidEqual(liquid)) {
				break;
			} else {
				compositeTank.getFluid().amount += liquid.amount;
			}

			capacity += tile.tank.getCapacity();
			tile = getTankAbove(tile);
		}

		compositeTank.setCapacity(capacity);
		return new FluidTankInfo[]{compositeTank.getInfo()};
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	public int getFluidLightLevel() {
		FluidStack tankFluid = tank.getFluid();
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}
}
