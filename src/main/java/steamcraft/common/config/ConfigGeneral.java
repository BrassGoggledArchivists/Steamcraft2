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
package steamcraft.common.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.config.Configuration;
import steamcraft.common.lib.LoggerSteamcraft;

public class ConfigGeneral
{
	public static Configuration config;

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_CLIENT = "client";

	public static boolean partyPooper;
	public static boolean drawFluid;
	public static boolean drawInside;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();

			partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod :(", false).getBoolean(false);
			
			drawFluid = config.get(CATEGORY_CLIENT, "Render fluid inside blocks", true).getBoolean(true);
			drawInside = config.get(CATEGORY_CLIENT, "Render the insides of blocks", true).getBoolean(true);
		}
		catch (Exception e)
		{
			LoggerSteamcraft.log(Level.SEVERE, "Failed to load configuration file:" + e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}


}