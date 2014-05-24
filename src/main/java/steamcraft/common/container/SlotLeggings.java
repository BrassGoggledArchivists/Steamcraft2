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
 * File created @ [Apr 8, 2014, 1:01:02 PM]
 */
package steamcraft.common.container;

import boilerplate.steamcraft.api.vanity.ILeggings;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// TODO: Auto-generated Javadoc
/**
 * The Class SlotLeggings.
 *
 * @author warlordjones
 */
public class SlotLeggings extends Slot
{
	
	/**
	 * Instantiates a new slot leggings.
	 *
	 * @param inventory the inventory
	 * @param par2 the par2
	 * @param par3 the par3
	 * @param par4 the par4
	 */
	public SlotLeggings(final IInventory inventory, final int par2,
			final int par3, final int par4)
	{
		super(inventory, par2, par3, par4);
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for
	 * the armor slots (and now also not always true for our custom inventory
	 * slots)
	 *
	 * @param is the is
	 * @return true, if is item valid
	 */
	@Override
	public boolean isItemValid(final ItemStack is)
	{

		return is.getItem() instanceof ILeggings;
	}

	/**
	 * Returns the maximum stack size for a given slot (usually the same as
	 * getInventoryStackLimit(), but 1 in the case of armor slots).
	 *
	 * @return the slot stack limit
	 */
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
}
