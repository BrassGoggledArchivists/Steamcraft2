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
 * File created @ [2 Apr 2014, 09:35:18]
 */
package common.steamcraft.common.block.machines;

import common.steamcraft.common.block.tile.machine.TileEntityBoiler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author warlordjones
 *
 * 2 Apr 201409:35:18
 */
public class BlockBoiler extends BlockContainer{

	/**
	 * @param par1
	 * @param par2Material
	 */
	protected BlockBoiler(int par1, Material par2Material) {
		super(par1, par2Material);
		setUnlocalizedName("boiler");
		
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft.world.World)
	 */
	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityBoiler();
	}
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		if(entityplayer.getHeldItem() == new ItemStack(Item.bucketWater))
		{
			TileEntityBoiler te = new TileEntityBoiler();
			te.waterTank.fill(new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME), true);
			entityplayer.setCurrentItemOrArmor(0, new ItemStack(Item.bucketEmpty));;
		}
		return false;
	}

}
