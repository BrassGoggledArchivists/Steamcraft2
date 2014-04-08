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
 * File created @ [Jan 30, 2014, 5:33:43 PM]
 */
package common.steamcraft.common.core.handler;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
 * @author MrArcane111, EntireCraft & general3214
 *
 */
public final class ConfigHandler {
	private static Configuration config;

    public static final String CATEGORY_RENDER = "render";
    public static final String CATEGORY_GUI = "gui";
    public static final String CATEGORY_ENTITY = "entity";
    public static final String CATEGORY_OPTIONS = "general options";

    private static int nextBlockId = 2600;
    private static int nextItemId = 16000;
    private static int nextRenderId = 8;
    private static int nextGuiId = 5;
    private static int nextEntityId = 5;
    
    public static int etheriumChance;
    public static int uraniumChance;
    public static int brimstoneChance;
    public static int borniteChance;
    public static int phosphateChance;
    public static int aluminumChance;
    public static int copperChance;
    public static int tinChance;
    public static int zincChance;
    public static int netherEtheriumCrystalChance;
    public static int endEtheriumCrystalChance;
    public static int etheriumHeight;
    public static int uraniumHeight;
    public static int brimstoneHeight;
    public static int borniteHeight;
    public static int phosphateHeight;
    public static int aluminumHeight;
    public static int copperHeight;
    public static int tinHeight;
    public static int zincHeight;
    public static int netherEtheriumCrystalHeight;
    public static int endEtheriumCrystalHeight;

    public static boolean generationEnabled;
	public static boolean overworldGenerationEnabled;
	public static boolean netherGenerationEnabled;
	public static boolean endGenerationEnabled;
	public static boolean oreGenerationEnabled;
	public static boolean etheriumOreGenEnabled;
	public static boolean uraniumOreGenEnabled;
	public static boolean brimstoneOreGenEnabled;
	public static boolean borniteOreGenEnabled;
	public static boolean phosphateOreGenEnabled;
	public static boolean aluminumOreGenEnabled;
	public static boolean copperOreGenEnabled;
	public static boolean tinOreGenEnabled;
	public static boolean zincOreGenEnabled;
	public static boolean netherEtheriumCrystalGenEnabled;
	public static boolean endEtheriumCrystalGenEnabled;

    public ConfigHandler(File file) {
        config = new Configuration(file);
        config.addCustomCategoryComment(Configuration.CATEGORY_BLOCK, "Set Block IDs to 0 to disable");
        etheriumChance = config.get(CATEGORY_OPTIONS, "Chance of Etherium Ore Generation. (Higher value = more ore. 0 disables.)", 1).getInt();
        uraniumChance = config.get(CATEGORY_OPTIONS, "Chance of Uranium Ore Generation", 3).getInt();
        brimstoneChance = config.get(CATEGORY_OPTIONS, "Chance of Brimstone Ore Generation", 10).getInt();
        borniteChance = config.get(CATEGORY_OPTIONS, "Chance of Bornite Ore Generation", 20).getInt();
        phosphateChance = config.get(CATEGORY_OPTIONS, "Chance of Phosphate Ore Generation", 3).getInt();
        aluminumChance = config.get(CATEGORY_OPTIONS, "Chance of Aluminum Ore Generation", 10).getInt();
        copperChance = config.get(CATEGORY_OPTIONS, "Chance of Copper Ore Generation", 10).getInt();
        tinChance = config.get(CATEGORY_OPTIONS, "Chance of Tin Ore Generation", 10).getInt();
        zincChance = config.get(CATEGORY_OPTIONS, "Chance of Zinc Ore Generation", 10).getInt();
        netherEtheriumCrystalChance = config.get(CATEGORY_OPTIONS, "Chance of Etheruim Crystal Generation in the Nether", 3).getInt();
        endEtheriumCrystalChance = config.get(CATEGORY_OPTIONS, "Chance of Etheruim Crystal Generation in the End", 3).getInt();
        etheriumHeight = config.get(CATEGORY_OPTIONS, "Height of Etherium Ore Generation. Ore will generate below this Y level in world", 12).getInt();
        uraniumHeight = config.get(CATEGORY_OPTIONS, "Height of Uranium Ore Generation", 24).getInt();
        brimstoneHeight = config.get(CATEGORY_OPTIONS, "Height of Brimstone Ore Generation", 64).getInt();
        borniteHeight = config.get(CATEGORY_OPTIONS, "Height of Bornite Ore Generation", 48).getInt();
        phosphateHeight = config.get(CATEGORY_OPTIONS, "Height of Phosphate Ore Generation", 36).getInt();
        aluminumHeight = config.get(CATEGORY_OPTIONS, "Height of Aluminum Ore Generation",36).getInt();
        copperHeight = config.get(CATEGORY_OPTIONS, "Height of Copper Ore Generation", 36).getInt();
        tinHeight = config.get(CATEGORY_OPTIONS, "Height of Tin Ore Generation", 36).getInt();
        zincHeight = config.get(CATEGORY_OPTIONS, "Height of Zinc Ore Generation", 36).getInt();
        netherEtheriumCrystalHeight = config.get(CATEGORY_OPTIONS, "Height of Etherium Crystal Generation in the Nether", 36).getInt();
        endEtheriumCrystalHeight = config.get(CATEGORY_OPTIONS, "Height of Etherium Crystal Generation in the End", 36).getInt();
        generationEnabled = config.get(CATEGORY_OPTIONS, "Will disable ALL SC2 World Gen completly", true).getBoolean(true);
        overworldGenerationEnabled = config.get(CATEGORY_OPTIONS, "Will disable all SC2 World Gen in the Overworld", true).getBoolean(true);
        netherGenerationEnabled = config.get(CATEGORY_OPTIONS, "Will disable all SC2 World Gen in the Nether", true).getBoolean(true);
        endGenerationEnabled = config.get(CATEGORY_OPTIONS, "Will disable all SC2 World Gen in the End", true).getBoolean(true);
        oreGenerationEnabled = config.get(CATEGORY_OPTIONS, "Will disable all SC2 Ore Gen", true).getBoolean(true);
        etheriumOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Etherium Ore Generation", true).getBoolean(true);
        uraniumOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Uranium Ore Generation", true).getBoolean(true);
        brimstoneOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Brimstone Ore Generation", true).getBoolean(true);
        borniteOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Bornite Ore Generation", true).getBoolean(true);
        phosphateOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Phosphate Ore Generation", true).getBoolean(true);
        aluminumOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Aluminum Ore Generation", true).getBoolean(true);
        copperOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Copper Ore Generation", true).getBoolean(true);
        tinOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Tin Ore Generation", true).getBoolean(true);
        zincOreGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Zinc Ore Generation", true).getBoolean(true);
        netherEtheriumCrystalGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Etherium Crystal Generation in the Nether", true).getBoolean(true);
        endEtheriumCrystalGenEnabled = config.get(CATEGORY_OPTIONS, "Will disable Etherium Crystal Generation in the End", true).getBoolean(true);
        
    }

	public void loadConfig() {
		config.load();
	}

    public void saveConfig() {
        config.save();
    }

    public int loadItem(String label) {
    	return loadItem(label, nextItemId++);
    }

    public int loadItem(String label, int defaultID) {
        return config.getItem("id_item." + label, defaultID).getInt(defaultID) - 256;
    }

    public int loadBlock(String label) {
        return loadBlock(label, nextBlockId++);
    }

    public int loadBlock(String label, int defaultID) {
        return config.getBlock("id_tile." + label, defaultID).getInt(defaultID);
    }

    public int loadRender(String label) {
        return loadRender(label, nextRenderId++);
    }

    public int loadRender(String label, int defaultID) {
        return config.get(CATEGORY_RENDER, "id_render." + label, defaultID).getInt(defaultID);
    }

    public int loadGui(String label) {
        return loadGui(label, nextGuiId++);
    }

    public int loadGui(String label, int defaultID) {
        return config.get(CATEGORY_GUI, "id_gui." + label, defaultID).getInt(defaultID);
    }

    public int loadEntity(String label) {
        return loadEntity(label, nextEntityId++);
    }

    public int loadEntity(String label, int defaultID) {
        return config.get(CATEGORY_ENTITY, "id_entity." + label, defaultID).getInt(defaultID);
    }
}