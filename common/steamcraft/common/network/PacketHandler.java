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
 * File created @ [Feb 15, 2014, 1:57:22 PM]
 */
package common.steamcraft.common.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import common.steamcraft.common.SC2;
import common.steamcraft.common.inventory.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author MrArcane111
 *
 */
public class PacketHandler implements IPacketHandler {
	/** */
	public static final String SC2_CHANNEL = "SC2_Channel";
	
	/** */
	public static final int PACKET_UPDATE = 10;
	
	/** */
	public static final int PACKET_REQUEST_UPDATE = 20;
	
	/** */
	public static final int PACKET_CLIENT_UPDATE = 30;
	
	/** */
	public static final int PACKET_GUI_INFO = 40;
	
	public static final int EXTENDED_PROPERTIES = 50, OPEN_SERVER_GUI = 60;
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		handleExtendedProperties(packet, player);
		try {
			int packetType = dataStream.read();
			//Switch Statement is much cleaner than if else :-)
			switch(packetType)
			{
			
			case 10:
				int x = dataStream.readInt();
				int y = dataStream.readInt();
				int z = dataStream.readInt();
				
				World world = ((EntityPlayer) player).worldObj;
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if (te instanceof NetworkTile) {
					NetworkTile netTE = (NetworkTile)te;
						netTE.readPacket(dataStream);
				}
				break;
			case 20:
				int x1 = dataStream.readInt();
				int y1 = dataStream.readInt();
				int z1 = dataStream.readInt();
				
				World world1 = ((EntityPlayer) player).worldObj;
				TileEntity te1 = world1.getBlockTileEntity(x1, y1, z1);
				if (te1 instanceof NetworkTile) {
					NetworkTile netTE = (NetworkTile)te1;
						netTE.sendPacket();
				}
				break;
			case 30:
				int x2 = dataStream.readInt();
				int y2 = dataStream.readInt();
				int z2 = dataStream.readInt();
				
				World world2 = ((EntityPlayer) player).worldObj;
				TileEntity te2 = world2.getBlockTileEntity(x2, y2, z2);
				if (te2 instanceof NetworkTile) {
					NetworkTile netTE = (NetworkTile)te2;
						netTE.readPacketFromClient(dataStream);
				}
				break;
			case 40:
				int windowID = dataStream.readByte();
				int barID = dataStream.readShort();
				int content = dataStream.readInt();
				
				Container container = Minecraft.getMinecraft().thePlayer.openContainer;
				
				if ((container != null) && (container.windowId == windowID)) {
					container.updateProgressBar(barID, content);
				}
				break;
			case 50:
				//handleExtendedProperties(packet, player, dataStream); break;
			case 60:
				handleOpenServerGui(packet, (EntityPlayer) player, dataStream); break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void handleOpenServerGui(Packet250CustomPayload packet, EntityPlayer player, DataInputStream inputStream)
	{
	int guiID;
	try {
	guiID = inputStream.readInt();
	} catch (IOException e) {
	e.printStackTrace();
	return;
	}
	player.openGui(SC2.instance, guiID, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}
	private void handleExtendedProperties(Packet250CustomPayload packet, Player player)
	{
	DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
	ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) player);

	try {
		inputStream.read();
	} catch (IOException e) {
	e.printStackTrace();
	return;
	}
	}

}
