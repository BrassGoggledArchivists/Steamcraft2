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
 * File created @ [Feb 6, 2014, 6:57:06 PM]
 */
package common.steamcraft.mod.common.core.helper;

import cpw.mods.fml.common.Loader;

/**
 * The sole purpose of this class is to determine if other mods are loaded.
 * 
 * @author MrArcane111
 *
 */
public class SC2_CompatibilityChecker {
	public static boolean IC2Loaded = false;
	public static boolean RailcraftLoaded = false;
	public static boolean BasicComponentsLoaded = false;
	public static boolean BuildCraftLoaded = false;
	public static boolean TELoaded = false;

	public static boolean MetallurgyCoreLoaded = false;
	public static boolean MetallurgyBaseLoaded = false;

	public static void postinit() {
		if (Loader.isModLoaded("IC2")) 
			IC2Loaded = true;
		if (Loader.isModLoaded("Railcraft")) 
			RailcraftLoaded = true;
		if (Loader.isModLoaded("BasicComponents")) 
			BasicComponentsLoaded = true;
		if (Loader.isModLoaded("BuildCraft|Energy")) 
			BuildCraftLoaded = true;
		if (Loader.isModLoaded("ThermalExpansion")) 
			TELoaded = true;

		if (Loader.isModLoaded("Metallurgy3Core"))
		{
			MetallurgyCoreLoaded = true;

			if (Loader.isModLoaded("Metallurgy3Base")) 
				MetallurgyBaseLoaded = true;
		}
	}
	
	public static float UE_TO_IC2 = 3.2F; //there is something weird with UE ratios
}
