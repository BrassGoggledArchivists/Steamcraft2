/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockCustomSlab extends BlockSlab
{
	String type;

	public BlockCustomSlab(String type)
	{
		super(false, Material.wood);
		this.type = type;
		this.setCreativeTab(Steamcraft.tabSC2);
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int p_150002_1_)
	{
		return ModInfo.PREFIX + "block" + type + "Planks";
	}

}
