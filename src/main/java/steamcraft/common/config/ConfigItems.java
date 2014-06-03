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
 * File created @ [Mar 14, 2014, 1:29:42 PM]
 */
package steamcraft.common.config;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import steamcraft.common.Steamcraft;
import steamcraft.common.items.BaseItem;
import steamcraft.common.items.ItemBrassGoggles;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.ItemChisel;
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemMechanical;
import steamcraft.common.items.ItemPipe;
import steamcraft.common.items.ItemRayGun;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemShrinkray;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.armor.ItemBrassArmor;
import steamcraft.common.items.armor.ItemNormalArmor;
import steamcraft.common.items.equipment.ItemCoreDrill;
import steamcraft.common.items.equipment.ItemDrill;
import steamcraft.common.items.equipment.ItemModAxe;
import steamcraft.common.items.equipment.ItemModHoe;
import steamcraft.common.items.equipment.ItemModPickaxe;
import steamcraft.common.items.equipment.ItemModSpade;
import steamcraft.common.items.equipment.ItemModSword;
import steamcraft.common.items.equipment.ItemSteamDrill;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;
import boilerplate.common.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigItems.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ConfigItems
{
	// Item Tools(sort of)
	/** The item mechanical. */
	public static Item itemMechanical;

	/** The item ingot. */
	public static Item itemResource, itemIngot;

	/** The item watch. */
	public static Item itemWatch;

	/** The item tea seed. */
	public static Item itemTeaSeed;

	/** The item chisel. */
	public static Item itemChisel;

	// Armor
	public static Item itemBrassHelm, itemBrassChest, itemBrassLegs, itemBrassBoots;
	/** The item brass goggles.
	public static Item itemBrassGoggles;

	/** The item aqualung.
	public static Item itemAqualung;

	/** The item leg braces.
	public static Item itemLegBraces;

	/** The item roller skates
	public static Item itemRollerSkates;

	/** The item steam jetpack. */
	public static Item itemSteamJetpack;

	/** The item helmet etherium. */
	public static Item itemHelmetEtherium;

	/** The item plate etherium. */
	public static Item itemPlateEtherium;

	/** The item legs etherium. */
	public static Item itemLegsEtherium;

	/** The item boots etherium. */
	public static Item itemBootsEtherium;

	/** The item helmet obsidian. */
	public static Item itemHelmetObsidian;

	/** The item plate obsidian. */
	public static Item itemPlateObsidian;

	/** The item legs obsidian. */
	public static Item itemLegsObsidian;

	/** The item boots obsidian. */
	public static Item itemBootsObsidian;

	// Canisters
	/** The item canister steam. */
	public static Item itemCanisterSteam;

	/** The item cold kettle. */
	public static Item itemColdKettle;

	/** The item hot kettle. */
	public static Item itemHotKettle;

	/** The item empty kettle. */
	public static Item itemEmptyKettle;

	/** The item empty teacup. */
	public static Item itemEmptyTeacup;

	/** The item full teacup. */
	public static Item itemFullTeacup;

	/** The item steam canister. */
	public static Item itemSteamCanister;

	/** The item canister gas. */
	public static Item itemCanisterGas;

	/** The item canister empty. */
	public static Item itemCanisterEmpty;

	/** The item musket round. */
	public static Item itemMusketRound;

	/** The item bucket steam. */
	public static Item itemBucketSteam;

	public static Item itemPipe;

	public static Item itemPoppySeed;

	public static Item itemRayGun, itemShrinkray;

	public static Item itemBrassGoggles;

	// Drills
		public static Item drillCore;
		public static Item drillBase;
		public static Item drillWood;
		public static Item drillStone;
		public static Item drillIron;
		public static Item drillDiamond;
		public static Item drillGold;
		public static Item drillSteam;
		public static Item drillEtherium;
		public static Item drillObsidian;

		// Steam
		public static Item pickaxeSteam;
		public static Item swordSteam;
		public static Item shovelSteam;
		public static Item axeSteam;
		public static Item hoeSteam;


		// Etherium
		public static Item pickaxeEtherium;
		public static Item swordEtherium;
		public static Item shovelEtherium;
		public static Item axeEtherium;
		public static Item hoeEtherium;

		// Obsidian
		public static Item pickaxeObsidian;
		public static Item swordObsidian;
		public static Item shovelObsidian;
		public static Item axeObsidian;
		public static Item hoeObsidian;

		// Other
		public static Item chisel;
		public static Item spanner;

	/**
	 * Inits the.
	 */
	public static void init()
	{
		MaterialHelper.initializeMaterials();
		initializeItems();
		registerItems();
	}
	/**
	 * Post init.
	 */
	public static void postInit()
	{
	}

	/**
	 * Initialize items.
	 */
	public static void initializeItems()
	{
		/*
		 *
		 * //Armor itemBrassGoggles = new ItemCustomArmor(Config.itemGogglesId,
		 * MaterialHelper.STEAM_ARMOR, 0, 0).setUnlocalizedName("itemGoggles");
		 * GameRegistry.registerItem(itemBrassGoggles, "ItemBrassGoggles",
		 * LibInfo.ID); itemAqualung = new
		 * ItemCustomArmor(Config.itemAqualungId, MaterialHelper.STEAM_ARMOR, 0,
		 * 1).setUnlocalizedName("itemAqualung");
		 * GameRegistry.registerItem(itemAqualung, "ItemAqualung", LibInfo.ID);
		 * itemLegBraces = new ItemCustomArmor(Config.itemLegBracesId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 2).setUnlocalizedName("itemLegBraces");
		 * GameRegistry.registerItem(itemLegBraces, "ItemLegBraces",
		 * LibInfo.ID); itemRollerSkates = new
		 * ItemCustomArmor(Config.itemRollerSkatesId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 3).setUnlocalizedName("itemRollerSkates");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemRollerSkates",
		 * LibInfo.ID); itemSteamJetpack = new
		 * ItemSteamJetpack(Config.itemSteamJetpackId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 1).setUnlocalizedName("itemSteamJetpack");
		 * GameRegistry.registerItem(itemSteamJetpack, "ItemSteamJetpack",
		 * LibInfo.ID);
		 *
		 *
		 *
		 * //More Items itemTeacupEmpty = new
		 * ItemTeacup(Config.itemTeacupEmptyId, 0, 0.0F,
		 * false).setUnlocalizedName("itemTeacupEmpty");
		 * GameRegistry.registerItem(itemTeacupEmpty, "ItemTeacupEmpty",
		 * LibInfo.ID); itemTeacupFull = new ItemTeacup(Config.itemTeacupFullId,
		 * 4, 0.9F, false).setUnlocalizedName("itemTeacupFull");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemTeacupFull",
		 * LibInfo.ID); itemKettleEmpty = new
		 * ItemKettle(Config.itemKettleEmptyId,
		 * 300).setUnlocalizedName("itemKettleEmpty");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemKettleEmpty",
		 * LibInfo.ID); itemKettleHot = new ItemKettle(Config.itemKettleHotId,
		 * 300).setUnlocalizedName("itemKettleHot");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemKettleHot",
		 * LibInfo.ID); itemKettleCold = new ItemKettle(Config.itemKettleColdId,
		 * 300).setUnlocalizedName("itemKettleCold");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemKettleCold",
		 * LibInfo.ID); itemGunParts = new
		 * ItemGunParts(Config.itemGunPartsId).setUnlocalizedName
		 * ("itemGunParts"); GameRegistry.registerItem(itemGunParts,
		 * "ItemGunParts", LibInfo.ID);
		 */

		//Armor
		itemBrassHelm = new ItemBrassArmor(ItemArmor.ArmorMaterial.IRON, 0, 0).setUnlocalizedName("itemBrassHelm");
		itemBrassChest = new ItemBrassArmor(ItemArmor.ArmorMaterial.IRON, 0, 0).setUnlocalizedName("itemBrassChest");
		itemBrassLegs = new ItemBrassArmor(ItemArmor.ArmorMaterial.IRON, 0, 0).setUnlocalizedName("itemBrassLegs");
		itemBrassBoots = new ItemBrassArmor(ItemArmor.ArmorMaterial.IRON, 0, 0).setUnlocalizedName("itemBrassBoots");
		// Canisters
		itemCanisterEmpty = new Item().setUnlocalizedName("itemCanisterEmpty");
		itemCanisterSteam = new ItemCanister(Config.itemCanisterSteamId,
				"steam").setUnlocalizedName("itemCanisterSteam");
		itemCanisterGas = new ItemCanister(Config.itemCanisterGasId,
				"compressedgas").setUnlocalizedName("itemCanisterGas");
		itemIngot = new ItemIngot();
		itemMechanical = new ItemMechanical().setUnlocalizedName("ItemMechanical");
		itemResource = new ItemResource().setUnlocalizedName("itemResource");
		itemWatch = new ItemWatch().setUnlocalizedName("itemWatch");
		itemTeaSeed = new ItemTeaSeed().setUnlocalizedName("itemTeaSeed");
		itemChisel = new ItemChisel().setUnlocalizedName("itemChisel");
		itemBrassGoggles = new ItemBrassGoggles(ItemArmor.ArmorMaterial.CHAIN, 0, 0);
		itemPipe = new ItemPipe();
		itemPoppySeed = new Item().setUnlocalizedName("itemPoppySeed").setCreativeTab(Steamcraft.tabSC2);
		itemRayGun = new ItemRayGun(LibInfo.PREFIX + "raygun");
		itemShrinkray = new ItemShrinkray(LibInfo.PREFIX + "shrinkray");
		drillCore = new ItemCoreDrill().setUnlocalizedName("itemDrillCore");
		drillBase = new BaseItem().setUnlocalizedName("itemDrillBase");
		// int id, int maxEnergy, int toolTier, int energyTier(mostly for IC2)
		drillWood = new ItemDrill(ToolMaterial.WOOD.getHarvestLevel()).setUnlocalizedName("itemDrillWood").setCreativeTab(Steamcraft.tabSC2);
		drillStone = new ItemDrill(ToolMaterial.STONE.getHarvestLevel()).setUnlocalizedName("itemDrillStone");
		drillIron = new ItemDrill(ToolMaterial.IRON.getHarvestLevel()).setUnlocalizedName("itemDrillIron");
		drillDiamond = new ItemDrill(ToolMaterial.EMERALD.getHarvestLevel()).setUnlocalizedName("itemDrillDiamond");
		drillGold = new ItemDrill(ToolMaterial.GOLD.getHarvestLevel()).setUnlocalizedName("itemDrillGold");
		drillSteam = new ItemSteamDrill().setUnlocalizedName("itemDrillSteam");
		drillEtherium = new ItemDrill(MaterialHelper.ETHERIUM_TOOL.getHarvestLevel()).setUnlocalizedName("itemDrillEtherium");
		drillObsidian = new ItemDrill(MaterialHelper.OBSIDIAN_TOOL.getHarvestLevel()).setUnlocalizedName("itemDrillObsidian");

		pickaxeSteam = new ItemModPickaxe(MaterialHelper.STEAM_TOOL).setUnlocalizedName("itemPickSteam");
		swordSteam = new ItemModSword(MaterialHelper.STEAM_TOOL).setUnlocalizedName("itemSwordSteam");
		shovelSteam = new ItemModSpade(MaterialHelper.STEAM_TOOL).setUnlocalizedName("itemShovelSteam");
		axeSteam = new ItemModAxe(MaterialHelper.STEAM_TOOL).setUnlocalizedName("itemAxeSteam");
		hoeSteam = new ItemModHoe(MaterialHelper.STEAM_TOOL).setUnlocalizedName("itemHoeSteam");

		pickaxeEtherium = new ItemModPickaxe(MaterialHelper.ETHERIUM_TOOL).setUnlocalizedName("itemPickEtherium");
		swordEtherium = new ItemModSword(MaterialHelper.ETHERIUM_TOOL).setUnlocalizedName("itemSwordEtherium");
		shovelEtherium = new ItemModSpade(MaterialHelper.ETHERIUM_TOOL).setUnlocalizedName("itemSpadeEtherium");
		axeEtherium = new ItemModAxe(MaterialHelper.ETHERIUM_TOOL).setUnlocalizedName("itemAxeEtherium");
		hoeEtherium = new ItemModHoe(MaterialHelper.ETHERIUM_TOOL).setUnlocalizedName("itemHoeEtherium");

		pickaxeObsidian = new ItemModPickaxe(MaterialHelper.OBSIDIAN_TOOL).setUnlocalizedName("itemPickObsidian");
		swordObsidian = new ItemModSword(MaterialHelper.OBSIDIAN_TOOL).setUnlocalizedName("itemSwordObsidian");
		shovelObsidian = new ItemModSpade(MaterialHelper.OBSIDIAN_TOOL).setUnlocalizedName("itemSpadeObsidian");
		axeObsidian = new ItemModAxe(MaterialHelper.OBSIDIAN_TOOL).setUnlocalizedName("itemAxeObsidian");
		hoeObsidian = new ItemModHoe(MaterialHelper.OBSIDIAN_TOOL).setUnlocalizedName("itemHoeObsidian");

		spanner = new /*Really?*/ItemChisel().setUnlocalizedName("spanner");

		itemHelmetEtherium = new ItemNormalArmor(MaterialHelper.ETHERIUM_ARMOR, 0, 0).setUnlocalizedName("itemHelmetEtherium");
		itemPlateEtherium = new ItemNormalArmor(MaterialHelper.ETHERIUM_ARMOR, 0, 1).setUnlocalizedName("itemPlateEtherium");
		itemLegsEtherium = new ItemNormalArmor(MaterialHelper.ETHERIUM_ARMOR, 0, 2).setUnlocalizedName("itemLegsEtherium");
		itemBootsEtherium = new ItemNormalArmor(MaterialHelper.ETHERIUM_ARMOR, 0, 3).setUnlocalizedName("itemBootsEtherium");

		itemHelmetObsidian = new ItemNormalArmor(MaterialHelper.OBSIDIAN_ARMOR, 0, 0).setUnlocalizedName("itemHelmetObsidian");
		itemPlateObsidian = new ItemNormalArmor(MaterialHelper.OBSIDIAN_ARMOR, 0, 1).setUnlocalizedName("itemPlateObsidian");
		itemLegsObsidian = new ItemNormalArmor(MaterialHelper.OBSIDIAN_ARMOR, 0, 2).setUnlocalizedName("itemLegsObsidian");
		itemBootsObsidian = new ItemNormalArmor(MaterialHelper.OBSIDIAN_ARMOR, 0, 3).setUnlocalizedName("itemBootsObsidian");
	}
	private static void registerItems()
	{
		GameRegistry.registerItem(itemCanisterEmpty, "ItemCanisterEmpty", LibInfo.ID);
		GameRegistry.registerItem(itemCanisterSteam, "ItemCanisterSteam", LibInfo.ID);
		GameRegistry.registerItem(itemCanisterGas, "ItemCanisterGas", LibInfo.ID);
		GameRegistry.registerItem(itemMechanical, "ItemMechanical", LibInfo.ID);
		GameRegistry.registerItem(itemResource, "ItemResource", LibInfo.ID);
		GameRegistry.registerItem(itemWatch, "ItemWatch", LibInfo.ID);
		GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", LibInfo.ID);
		GameRegistry.registerItem(itemChisel, "ItemChisel", LibInfo.ID);
		GameRegistry.registerItem(itemPipe, "ItemPipe", LibInfo.ID);
		GameRegistry.registerItem(itemPoppySeed, "ItemPoppySeed", LibInfo.ID);
		GameRegistry.registerItem(itemRayGun, "ItemRayGun", LibInfo.ID);
		GameRegistry.registerItem(itemShrinkray, "ItemShrinkray", LibInfo.ID);
		GameRegistry.registerItem(itemBrassGoggles, "ItemBrassGoggles", LibInfo.ID);
		GameRegistry.registerItem(itemIngot, "ItemIngot", LibInfo.ID);
		GameRegistry.registerItem(drillCore, "ItemDrillCore", LibInfo.ID);
		GameRegistry.registerItem(drillBase, "ItemDrillBase", LibInfo.ID);
		GameRegistry.registerItem(drillWood, "ItemDrillWood", LibInfo.ID);
		GameRegistry.registerItem(drillStone, "ItemDrillStone", LibInfo.ID);
		GameRegistry.registerItem(drillIron, "ItemDrillIron", LibInfo.ID);
		GameRegistry.registerItem(drillDiamond, "ItemDrillDiamond", LibInfo.ID);
		GameRegistry.registerItem(drillGold, "ItemDrillGold", LibInfo.ID);
		GameRegistry.registerItem(drillSteam, "ItemDrillSteam", LibInfo.ID);
		GameRegistry.registerItem(drillEtherium, "ItemDrillEtherium", LibInfo.ID);
		GameRegistry.registerItem(drillObsidian, "ItemDrillObsidian", LibInfo.ID);
		RegistryHelper.registerToolSet(axeEtherium, hoeEtherium, pickaxeEtherium, shovelEtherium, swordEtherium, "Etherium", LibInfo.ID);
		RegistryHelper.registerToolSet(axeObsidian, hoeObsidian, pickaxeObsidian, shovelObsidian, swordObsidian, "Obsidian", LibInfo.ID);
		RegistryHelper.registerArmorSet(itemBrassHelm, itemBrassChest, itemBrassLegs, itemBrassBoots, "Brass", LibInfo.ID);
		RegistryHelper.registerArmorSet(itemHelmetObsidian, itemPlateObsidian, itemLegsObsidian, itemBootsObsidian, "Obsidian", LibInfo.ID);
		RegistryHelper.registerArmorSet(itemHelmetEtherium, itemPlateEtherium, itemLegsEtherium, itemBootsEtherium, "Etherium", LibInfo.ID);
	}
}
