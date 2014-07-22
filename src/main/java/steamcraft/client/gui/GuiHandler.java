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
package steamcraft.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.container.ContainerVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileBattery;
import steamcraft.common.tiles.TileBloomery;
import steamcraft.common.tiles.TileCharger;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.container.ContainerArmorEditor;
import steamcraft.common.tiles.container.ContainerBattery;
import steamcraft.common.tiles.container.ContainerBloomery;
import steamcraft.common.tiles.container.ContainerCharger;
import steamcraft.common.tiles.container.ContainerSteamBoiler;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author Surseanca
 *
 */
public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		final TileEntity tile = world.getTileEntity(x, y, z);

		switch (id)
		{
			case GuiIDs.GUI_ID_STEAM_BOILER:
				return new ContainerSteamBoiler(player.inventory, (TileSteamBoiler) tile);
			case GuiIDs.GUI_ID_VANITY:
				return new ContainerVanity(player, player.inventory, EntityPlayerExtended.get(player).inventory);
			case GuiIDs.GUI_ID_ARMOREDITOR:
				return new ContainerArmorEditor(player.inventory, (TileArmorEditor) tile);
			case GuiIDs.GUI_ID_BLOOMERY:
				return new ContainerBloomery(player.inventory, (TileBloomery) tile);
			case GuiIDs.GUI_ID_BATTERY:
				return new ContainerBattery(player.inventory, (TileBattery) tile);
			case GuiIDs.GUI_ID_CHARGER:
				return new ContainerCharger(player.inventory, (TileCharger) tile);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		final TileEntity tile = world.getTileEntity(x, y, z);

		switch (id)
		{
		case GuiIDs.GUI_ID_STEAM_BOILER:
			return new GuiSteamBoiler(player.inventory, (TileSteamBoiler) tile);
		case GuiIDs.GUI_ID_ARMOREDITOR:
			return new GuiArmorEditor(player.inventory, (TileArmorEditor) tile);
		case GuiIDs.GUI_ID_VANITY:
			return new GuiVanity(player, player.inventory, EntityPlayerExtended.get(player).inventory);
		case GuiIDs.GUI_ID_BLOOMERY:
			return new GuiBloomery(player.inventory, (TileBloomery)tile);
		case GuiIDs.GUI_ID_BATTERY:
			return new GuiBattery(player.inventory, (TileBattery)tile);
		case GuiIDs.GUI_ID_CHARGER:
			return new ContainerCharger(player.inventory, (TileCharger) tile);
		}

		return null;
	}
}
