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
 * File created @ [Feb 4, 2014, 5:13:56 PM]
 */
package common.steamcraft.mod.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import common.steamcraft.mod.common.block.ModBlocks;
import common.steamcraft.mod.common.lib.ItemIDs;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * @author MrArcane111, general3214, EntireCraft
 *
 */
public class ModItems 
{
	public static Item etherium;
	public static Item chemicSalt;
	public static Item bornite;
	public static Item obsidianSlate;
	public static Item ingotBrass;
	public static Item ingotCastIron;
	public static Item lightBulb;
	public static Item phosphorus;
	public static Item uraniumOre;
	public static Item uraniumPellet;
	public static Item reactorCore;
	public static Item teaSeed;
	public static Item teaLeaves;
	public static Item coldKettle;
	public static Item hotKettle;
	public static Item emptyTeacup;
	public static Item fullTeacup;
	public static Item emptyKettle;
	public static Item copperWire;
	public static Item brassWatch;
	public static Item canisterSteam;
	public static Item canisterGas;
	public static Item steamCanister;
	public static Item gasCanister;
    public static Item emptyCanister;
	
	public static void initItems()
	{
		etherium = new ItemMod(ItemIDs.etheriumID).setUnlocalizedName("etherium");
		chemicSalt = new ItemMod(ItemIDs.chemicSaltID).setUnlocalizedName("sulphur");
		bornite = new ItemMod(ItemIDs.borniteID).setUnlocalizedName("copper");
		obsidianSlate = new ItemMod(ItemIDs.obsidianSlateID).setUnlocalizedName("obsidianslate");
		ingotBrass = new ItemMod(ItemIDs.ingotBrassID).setUnlocalizedName("ingotbrass");
		ingotCastIron = new ItemMod(ItemIDs.ingotCastIronID).setUnlocalizedName("ingotcastiron");
		lightBulb = new ItemMod(ItemIDs.lightBulbID).setUnlocalizedName("lightbulb");
		phosphorus = new ItemMod(ItemIDs.phosphorusID).setUnlocalizedName("phosphorus");
		uraniumOre = new ItemMod(ItemIDs.uraniumStoneID).setUnlocalizedName("uraniumstone");
		uraniumPellet = new ItemMod(ItemIDs.uraniumID).setUnlocalizedName("uraniumpellet");
		reactorCore = new ItemMod(ItemIDs.reactorCoreID).setUnlocalizedName("reactorcore");
		teaSeed = new ItemTeaSeeds(ItemIDs.teaSeedID, ModBlocks.teaPlant.blockID, Block.tilledField.blockID).setUnlocalizedName("teaseed"); 
		teaLeaves = new ItemMod(ItemIDs.teaLeavesID).setUnlocalizedName("tealeaves");
		//coldKettle = new ItemKettle(SC_ItemIDs.coldKettleID, 300).setUnlocalizedName("kettle");
		//hotKettle = new ItemKettle(SC_ItemIDs.hotKettleID, 300).setUnlocalizedName("kettle");
		//emptyKettle = new ItemKettle(SC_ItemIDs.emptyKettleID, 300).setUnlocalizedName("kettle");
		//fullTeacup = new ItemTeacup(SC_ItemIDs.fullTeacupID, 4, 0.6F, false).setUnlocalizedName("teacupfull");
		//emptyTeacup = new ItemTeacup(SC_ItemIDs.emptyTeacupID, 0, 0.0F, false).setUnlocalizedName("teacupempty");
		copperWire = new ItemCopperWire(ItemIDs.copperWireID).setUnlocalizedName("copperwire");
		brassWatch = new ItemBrassWatch(ItemIDs.brassWatchID);
		canisterSteam = new ItemCanister(ItemIDs.steamCanisterID, 500, "").setUnlocalizedName("steamcanister");
		canisterGas = new ItemCanister(ItemIDs.gasCanisterID, 1000, "").setUnlocalizedName("gascanister");
		steamCanister = new ItemCanister(ItemIDs.steamCanisterID, 500, "steam").setUnlocalizedName("steamcanister");
		gasCanister = new ItemCanister(ItemIDs.gasCanisterID, 1000, "compressed gas").setUnlocalizedName("gascanister");
		emptyCanister = new ItemMod(ItemIDs.emptyCanisterID).setUnlocalizedName("emptycanister");
		
		initNames();
		ModTools.initTools();
		ModArmors.initArmors();
		ModGuns.initGuns();
	}
	
	public static void initNames()
	{
		LanguageRegistry.addName(etherium, "Etherium Crystal");
		LanguageRegistry.addName(chemicSalt, "Sulfur");
		LanguageRegistry.addName(bornite, "Purified Copper");
		LanguageRegistry.addName(obsidianSlate, "Obsidian Slate");
		LanguageRegistry.addName(ingotBrass, "Brass Ingot");
		LanguageRegistry.addName(ingotCastIron, "Cast Iron Ingot");
		LanguageRegistry.addName(lightBulb, "Lightbulb");
		LanguageRegistry.addName(phosphorus, "Phosphorus");
		LanguageRegistry.addName(uraniumOre, "Uranium");
		LanguageRegistry.addName(uraniumPellet, "Uranium Pellets");
		LanguageRegistry.addName(reactorCore, "Reactor Core");
		LanguageRegistry.addName(teaSeed, "Tea Seeds");
		LanguageRegistry.addName(teaLeaves, "Tea Leaves");
		LanguageRegistry.addName(copperWire, "Copper Wire");
		LanguageRegistry.addName(brassWatch, "Brass Pocket-Watch");
		LanguageRegistry.addName(canisterSteam, "Steam Canister");
		LanguageRegistry.addName(canisterGas, "Compressed Gas Canister");
		LanguageRegistry.addName(steamCanister, "Steam Canister");
		LanguageRegistry.addName(gasCanister, "Compressed Gas Canister");
        LanguageRegistry.addName(emptyCanister, "Empty Canister");
	}
}
