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
 * File created @ [1 Apr 2014, 22:40:20]
 */
package common.steamcraft.common.util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.google.common.collect.ForwardingList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 * 1 Apr 201422:40:20
 */
public class TankManager<T extends Tank> extends ForwardingList<T> implements IFluidHandler, List<T> {

	private List<T> tanks = new ArrayList<T>();

	public TankManager() {
	}

	public TankManager(T... tanks) {
		addAll(Arrays.asList(tanks));
	}

	@Override
	protected List<T> delegate() {
		return tanks;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		for (Tank tank : tanks) {
			int used = tank.fill(resource, doFill);
			if (used > 0)
				return used;
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		for (Tank tank : tanks) {
			if (!resource.isFluidEqual(tank.getFluid()))
				continue;
			FluidStack drained = tank.drain(resource.amount, doDrain);
			if (drained != null && drained.amount > 0)
				return drained;
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		for (Tank tank : tanks) {
			FluidStack drained = tank.drain(maxDrain, doDrain);
			if (drained != null && drained.amount > 0)
				return drained;
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidTankInfo[] info = new FluidTankInfo[size()];
		for (int i = 0; i < size(); i++) {
			info[i] = get(i).getInfo();
		}
		return info;
	}

	public void writeToNBT(NBTTagCompound data) {
		for (Tank tank : tanks) {
			tank.writeToNBT(data);
		}
	}

	public void readFromNBT(NBTTagCompound data) {
		for (Tank tank : tanks) {
			tank.readFromNBT(data);
		}
	}

	public void writeData(ByteBuffer data) {
		for (Tank tank : tanks) {
			FluidStack fluidStack = tank.getFluid();
			if (fluidStack != null && fluidStack.getFluid() != null) {
				data.putShort((short) fluidStack.getFluid().getID());
				data.putInt(fluidStack.amount);
				data.putInt(fluidStack.getFluid().getColor(fluidStack));
			} else {
				data.putShort((short) -1);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void readData(ByteBuffer data) {
		for (Tank tank : tanks) {
			int fluidId = data.getShort();
			if (fluidId > 0) {
				tank.setFluid(new FluidStack(fluidId, data.getInt()));
				tank.colorRenderCache = data.getInt();
			} else {
				tank.setFluid(null);
				tank.colorRenderCache = 0xFFFFFF;
			}
		}
	}
}
