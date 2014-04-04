package common.steamcraft.client.core.handler;

import java.util.EnumSet;
import java.util.logging.Level;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.SC2;
import common.steamcraft.common.core.proxy.CommonProxy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	@SideOnly(Side.CLIENT)
	public class KeyHandlerMod extends KeyHandler
	{
	
	public static final String label = "Vanity Inventory Key";

	public KeyHandlerMod(KeyBinding[] keyBindings, boolean[] repeatings) {
	super(keyBindings, repeatings);
	}

	@Override
	public String getLabel() {
	return this.label;
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
	EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;

	if (kb == RegisterKeyBindings.keys[RegisterKeyBindings.VANITY_INV]) {
	// If the custom inventory screen is open, close it
	if (player.openContainer != null && player.openContainer instanceof common.steamcraft.common.inventory.ContainerVanity)
	player.closeScreen();

	// Otherwise, open the screen.
	else if (FMLClientHandler.instance().getClient().inGameHasFocus)
	{
		//FMLNetworkHandler.openGui(player, SC2.instance, GuiIDs.GUI_ID_VANITY, player.worldObj, player.serverPosX, player.serverPosY, player.serverPosZ);
		player.openGui(SC2.instance, GuiIDs.GUI_ID_VANITY, player.worldObj, player.serverPosX, player.serverPosY, player.serverPosZ);
		//((EntityClientPlayerMP) player).sendQueue.addToSendQueue(common.steamcraft.common.network.PacketOpenServerGui.getPacket(GuiIDs.GUI_ID_VANITY));
	}
	}}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
	// Don't need to do anything here!
	}

	@Override
	public EnumSet<TickType> ticks() {
	return EnumSet.of(TickType.CLIENT);
	}
	}