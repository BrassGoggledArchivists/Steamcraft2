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
 * File created @ [Feb 17, 2014, 2:44:47 PM]
 */
package common.steamcraft.common.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import common.steamcraft.common.block.machines.BlockContainerMod;
import common.steamcraft.common.block.tile.TileEntityCopperPipe;
import common.steamcraft.common.item.ModTools;
import common.steamcraft.common.lib2.BlockIDs;
import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.ItemIDs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111
 *
 */
public class BlockCopperPipe extends BlockContainerMod {
	public BlockCopperPipe(int id, Material mat) {
		super(id, mat.iron);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityCopperPipe te = (TileEntityCopperPipe)world.getBlockTileEntity(x, y, z);

		if(te != null) {
			if(player.getHeldItem() == new ItemStack(ModTools.spanner))
			{
			te.cycleConnections(-1);
			}
			return true;
		}

		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCopperPipe();
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		return side + 1;
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.35F, 0.35F, 0.35F, 0.70F, 0.70F, 0.70F);
		float minx = (float)this.minX;
		float maxx = (float)this.maxX;
		float miny = (float)this.minY;
		float maxy = (float)this.maxY;
		float minz = (float)this.minZ;
		float maxz = (float)this.maxZ;

		if (par1IBlockAccess.getBlockId(x-1, y, z) == this.blockID)
			minx = 0;
		
		if (par1IBlockAccess.getBlockId(x+1, y, z) == this.blockID)
			maxx = 1;
		
		if (par1IBlockAccess.getBlockId(x, y-1, z) == this.blockID)
			miny = 0;
		
		if (par1IBlockAccess.getBlockId(x, y+1, z) == this.blockID)
			maxy = 1;
		
		if (par1IBlockAccess.getBlockId(x, y, z-1) == this.blockID)
			minz = 0;
		
		if (par1IBlockAccess.getBlockId(x, y, z+1) == this.blockID)
			maxz = 1;	
		
		if (par1IBlockAccess.getBlockId(x-1, y, z) == BlockIDs.copperTankID)
			minx = 0;

		if (par1IBlockAccess.getBlockId(x+1, y, z) == BlockIDs.copperTankID)
			maxx = 1;

		if (par1IBlockAccess.getBlockId(x, y-1, z) == BlockIDs.copperTankID)
			miny = 0;

		if (par1IBlockAccess.getBlockId(x, y+1, z) == BlockIDs.copperTankID)
			maxy = 1;

		if (par1IBlockAccess.getBlockId(x, y, z-1) == BlockIDs.copperTankID)
			minz = 0;

		if (par1IBlockAccess.getBlockId(x, y, z+1) == BlockIDs.copperTankID)
			maxz = 1;

		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}
}
