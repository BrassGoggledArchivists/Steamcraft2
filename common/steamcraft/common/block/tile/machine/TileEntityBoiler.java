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
 * File created @ [2 Apr 2014, 09:34:04]
 */
package common.steamcraft.common.block.tile.machine;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import common.steamcraft.common.util.Tank;

/**
 * @author warlordjones
 *
 * 2 Apr 201409:34:04
 */
public class TileEntityBoiler extends TileEntityMachine {
	public final Tank waterTank = new Tank("internalWaterTank", FluidContainerRegistry.BUCKET_VOLUME * 16, this);
	public final Tank steamTank = new Tank("internalSteamTank", FluidContainerRegistry.BUCKET_VOLUME * 16, this);
	public void onUpdate()
	{
		waterTank.drain(10, true);
		steamTank.fill(new FluidStack(FluidRegistry.LAVA, 10), true);
	}
}
