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
 * File created @ [29 Mar 2014, 17:45:03]
 */
package common.steamcraft.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import common.steamcraft.common.block.machines.BlockContainerMod;
import common.steamcraft.common.block.tile.TileEntityCopperTank;
import common.steamcraft.common.util.InventoryUtils;

/**
 * @author warlordjones
 * Some Code derived from Buildcraft - because I am a noob.
 * 29 Mar 201417:45:03
 */
public class BlockCopperTank extends BlockContainerMod
{

	/**
	 * @param id
	 * @param mat
	 */
	public BlockCopperTank(int id, Material mat) {
		super(id, mat);
		
	}
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft.world.World)
	 */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCopperTank();
	}
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		ItemStack current = entityplayer.inventory.getCurrentItem();

		if (current != null) {
			FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);

			TileEntityCopperTank tank = (TileEntityCopperTank) world.getBlockTileEntity(i, j, k);

			// Handle filled containers
			if (liquid != null) {
				int qty = tank.fill(ForgeDirection.UNKNOWN, liquid, true);

				if (qty != 0 && !entityplayer.capabilities.isCreativeMode) {
					entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, InventoryUtils.consumeItem(current));
				}

				return true;

				// Handle empty containers
			} else {
				FluidStack available = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;

				if (available != null) {
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);

					liquid = FluidContainerRegistry.getFluidForFilledItem(filled);

					if (liquid != null) {
						if (!entityplayer.capabilities.isCreativeMode) {
							if (current.stackSize > 1) {
								if (!entityplayer.inventory.addItemStackToInventory(filled))
									return false;
								else {
									entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, InventoryUtils.consumeItem(current));
								}
							} else {
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, InventoryUtils.consumeItem(current));
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, filled);
							}
						}

						tank.drain(ForgeDirection.UNKNOWN, liquid.amount, true);

						return true;
					}
				}
			}
		}
		return false;
	}

}
