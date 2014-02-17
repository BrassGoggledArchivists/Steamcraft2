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
 * File created @ [Jan 30, 2014, 6:11:43 PM]
 */
package common.steamcraft.common.block;

import common.steamcraft.common.lib.BlockIDs;
import common.steamcraft.common.lib.BlockNames;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * @author MrArcane111
 *
 */
public class ModOres 
{
	public static Block oreBornite;
	public static Block oreBrimstone;
	public static Block orePhosphate;
	public static Block oreUranite;
	public static Block oreVolucite;
	
	public static void initOres()
	{
		oreBornite = new BlockOreMod(BlockIDs.oreBorniteID, "bornite").setUnlocalizedName(BlockNames.ORE_BORNITE);
		oreBrimstone = new BlockOreMod(BlockIDs.oreBrimstoneID, "brimstone").setUnlocalizedName(BlockNames.ORE_BRIMSTONE);
		orePhosphate = new BlockOreMod(BlockIDs.orePhosphateID, "phosphate").setUnlocalizedName(BlockNames.ORE_PHOSPHATE);
		oreUranite = new BlockUraniteOre(BlockIDs.oreUraniteID).setUnlocalizedName(BlockNames.ORE_URANITE);
		oreVolucite = new BlockOreMod(BlockIDs.oreVoluciteID, "volucite").setUnlocalizedName(BlockNames.ORE_VOLUCITE);
		
		registerOres();
		initOreNames();
		initOreHarvestLevels();
		registerOreDictionary();
	}
	
	public static void registerOres()
	{
		GameRegistry.registerBlock(oreBornite, BlockNames.ORE_BORNITE);
		GameRegistry.registerBlock(oreBrimstone, BlockNames.ORE_BRIMSTONE);
		GameRegistry.registerBlock(orePhosphate, BlockNames.ORE_PHOSPHATE);
		GameRegistry.registerBlock(oreUranite, BlockNames.ORE_URANITE);
		GameRegistry.registerBlock(oreVolucite, BlockNames.ORE_VOLUCITE);
	}
	
	public static void initOreNames()
	{
		LanguageRegistry.addName(oreBornite, "Bornite Ore");
		LanguageRegistry.addName(oreBrimstone, "Brimstone Ore");
		LanguageRegistry.addName(orePhosphate, "Phosphate Ore");
		LanguageRegistry.addName(oreUranite, "Uranite Ore");
		LanguageRegistry.addName(oreVolucite, "Volucite Ore");
	}
	
	public static void initOreHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(oreBornite, 0, "pickaxe", 1); 
		MinecraftForge.setBlockHarvestLevel(oreBrimstone, 0, "pickaxe", 1); 
		MinecraftForge.setBlockHarvestLevel(orePhosphate, 0, "pickaxe", 2); 
		MinecraftForge.setBlockHarvestLevel(oreUranite, 0, "pickaxe", 2); 
		MinecraftForge.setBlockHarvestLevel(oreVolucite, 0, "pickaxe", 4);
	}
	
	public static void registerOreDictionary()
	{
		// TODO: 
	}
}