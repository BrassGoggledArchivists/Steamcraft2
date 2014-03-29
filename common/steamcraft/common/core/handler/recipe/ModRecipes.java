package common.steamcraft.common.core.handler.recipe;

import java.util.Random;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.block.ModOres;
import common.steamcraft.common.item.ModArmors;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.item.ModTools;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

	public static void initRecipes() 
	{
		//Blocks
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockAluminum, 1, 0), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 0)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockCopper), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 1)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockTin), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockZinc), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 3)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockCastIron), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 4)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBrass), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 5)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBronze), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 6)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockSteel), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.ingotsMetal, 1, 7)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockUranium), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.uraniumOre)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockVolucite), new Object[] {"III", "III", "III", 'I', new ItemStack(ModItems.etherium)});

		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockObsidianTile), new Object[] {"XX", "XX", 'X', new ItemStack(Block.obsidian)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.castIronLampOff), new Object[] {"ccc", "flf", " f ", 'c', new ItemStack(ModItems.ingotsMetal, 1, 4), 'f', ModBlocks.railingCastIron, 'l', ModItems.lightBulb});
		//Items
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 0), new Object[]{new ItemStack(ModBlocks.blockAluminum)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 1), new Object[]{new ItemStack(ModBlocks.blockCopper)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 2), new Object[]{new ItemStack(ModBlocks.blockTin)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 3), new Object[]{new ItemStack(ModBlocks.blockZinc)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 4), new Object[]{new ItemStack(ModBlocks.blockCastIron)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 5), new Object[]{new ItemStack(ModBlocks.blockBrass)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 6), new Object[]{new ItemStack(ModBlocks.blockBronze)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 7), new Object[]{new ItemStack(ModBlocks.blockSteel)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.uraniumOre, 9), new Object[]{new ItemStack(ModBlocks.blockUranium)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.etherium, 9), new Object[]{new ItemStack(ModBlocks.blockVolucite)});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.lightBulb), new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c', ModItems.copperWire});
		//Duplicate Recipes are deliberate, until I find a better way to be able to use copper or iron partPiles ;)
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ModItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ModItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ModItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ModItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.emptyCanister), new Object[]{" a ", "a a", " a ", 'a', new ItemStack(ModItems.ingotsMetal, 1, 0)});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillBase), new Object[]{"   ", "ccc", " c ", 'c', new ItemStack(ModItems.ingotsMetal, 1, 4)});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillCore), new Object[]{" c ", "ccc", "   ", 'c', new ItemStack(ModItems.ingotsMetal, 1, 4)});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillWood), new Object[]{" M ","MCM"," B ", 'M', Block.wood, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillStone), new Object[]{" M ","MCM"," B ", 'M', Block.stone, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillIron), new Object[]{" M ","MCM"," B ", 'M', Block.blockIron, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillGold), new Object[]{" M ","MCM"," B ", 'M', Block.blockGold, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillDiamond), new Object[]{" M ","MCM"," B ", 'M', Block.blockDiamond, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillObsidian), new Object[]{" M ","MCM"," B ", 'M', Block.obsidian, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillEtherium), new Object[]{" M ","MCM"," B ", 'M',  ModBlocks.blockVolucite, 'C', ModTools.drillCore, 'B', ModTools.drillBase});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.bootsEtherium), new Object[]{"   ", "M M", "M M", 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.legsEtherium), new Object[]{"MMM", "M M", "M M", 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.plateEtherium), new Object[]{"M M", "MMM", "MMM", 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.helmetEtherium), new Object[]{"MMM", "M M", "   ", 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.bootsObsidian), new Object[]{"   ", "M M", "M M", 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.legsObsidian), new Object[]{"MMM", "M M", "M M", 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.plateObsidian), new Object[]{"M M", "MMM", "MMM", 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModArmors.helmetObsidian), new Object[]{"MMM", "M M", "   ", 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.swordEtherium), new Object[]{" M ", " M ", " S ", 'S', Item.stick, 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.pickaxeEtherium), new Object[]{"MMM", " S ", " S ", 'S', Item.stick, 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.axeEtherium), new Object[]{"MM ", "M  ", " S ", 'S', Item.stick, 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.shovelEtherium), new Object[]{" M ", " S ", " S ", 'S', Item.stick, 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.hoeEtherium), new Object[]{"MM ", " S ", " S ", 'S', Item.stick, 'M', ModItems.etherium});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.swordObsidian), new Object[]{" M ", " M ", " S ", 'S', Item.stick, 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.pickaxeObsidian), new Object[]{"MMM", " S ", " S ", 'S', Item.stick, 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.axeObsidian), new Object[]{"MM ", "M  ", " S ", 'S', Item.stick, 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.shovelObsidian), new Object[]{" M ", " S ", " S ", 'S', Item.stick, 'M', ModItems.obsidianSlate});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.hoeObsidian), new Object[]{"MM ", " S ", " S ", 'S', Item.stick, 'M', ModItems.obsidianSlate});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.guideBook), new Object[] {Item.book, ModItems.lightBulb});
		GameRegistry.addRecipe(new ItemStack(ModTools.chisel), new Object[] {"   ", " i ", " s ", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Item.stick)});
			//Parts
		//There MUST be a neater/shorter way to do this???
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 0), new Object[] {new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 1),new Object[] {new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 2),new Object[] {new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 3),new Object[] {new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE)});
		
	}
	public static void initSmelting()
	{
		//Ores ---> Ingots
		GameRegistry.addSmelting(ModOres.oreAluminum.blockID, new ItemStack(ModItems.ingotsMetal, 1, 0), 0.7F);
		GameRegistry.addSmelting(ModOres.oreCopper.blockID, new ItemStack(ModItems.ingotsMetal, 1, 1), 0.7F);
		GameRegistry.addSmelting(ModOres.oreTin.blockID, new ItemStack(ModItems.ingotsMetal, 1, 2), 0.7F);
		GameRegistry.addSmelting(ModOres.oreZinc.blockID, new ItemStack(ModItems.ingotsMetal, 1, 3), 0.7F);
		//Misc
		GameRegistry.addSmelting(Block.blockIron.blockID, new ItemStack(ModBlocks.blockCastIron), 0.2F);
		GameRegistry.addSmelting(ModItems.uraniumOre.itemID, new ItemStack(ModItems.uraniumPellet), 0.5F);
	}

}
