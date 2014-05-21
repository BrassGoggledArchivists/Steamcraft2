/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/15/14, 11:15]
 */
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCastIronFence extends BlockFence
{
    public BlockCastIronFence()
    {
        super(LibInfo.PREFIX + "blockCastIron", Material.iron);
        this.setHardness(4.5F);
        this.setResistance(20.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setCreativeTab(Steamcraft.tabSC2);
        setUnlocalizedName("blockCastIronFence");
    }

    @Override
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);

        if ((block != this) && (block != ConfigBlocks.blockCastIronGate))
            return ((block != null) && (block.getMaterial().isOpaque()) && (block.renderAsNormalBlock()) ? block.getMaterial() != Material.cactus : false);
        else
            return true;
    }
}
