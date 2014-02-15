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

import common.steamcraft.mod.client.core.helper.SC2_ClientHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author MrArcane111 & EntireCraft
 *
 */
public class ItemBrassWatch extends ItemSC2
{
	public ItemBrassWatch(int id)
	{
		super(id);
		this.maxStackSize = 1;
		this.setUnlocalizedName("clock");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		if(!SC2_ClientHelper.isShiftKeyDown())
		{
			list.add(SC2_ClientHelper.shiftForInfo);
			return;
		}
		
		// I thought there was some shortcut using the backslash to carry to the next line...oh well
		list.add("On right-click, this item"); 
		list.add("will display the MC time");
		list.add("and the real-world time");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			long mcTime = world.getTotalWorldTime();
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // hours and minutes, subtract twelve, ya pussy!
	    	// System.out.println(sdf.format(cal.getTime()));
	    	String message = "MC Time: " + mcTime;
	    	player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
            message = "Real-World Time: " + sdf.format(cal.getTime());
            player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
		}
		
		return stack;
	}
}
