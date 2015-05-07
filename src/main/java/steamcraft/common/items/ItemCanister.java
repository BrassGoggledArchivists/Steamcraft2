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
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal & warlordjones
 *
 */
public class ItemCanister extends BaseItem implements IFluidContainerItem
{
	private IIcon emptyIcon;
	private IIcon fullIcon;
	public static final int MAX_STEAM = 10000;
	public static final int MAX_STEAM_RATE = 20;

	public ItemCanister()
	{
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(MAX_STEAM / 100);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.emptyIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemCanisterEmpty");
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemCanisterHalf");
		this.fullIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemCanisterFull");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		if(damage == 0)
			return this.fullIcon;
		else if(damage == this.getMaxDamage())
			return this.emptyIcon;
		return this.itemIcon;
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(InitItems.itemCanisterSteam, 1, this.getMaxDamage()));
		l.add(this.getFilledCanister());
	}

	public ItemStack getFilledCanister()
	{
		ItemStack filled = new ItemStack(InitItems.itemCanisterSteam, 1, 0);

		this.fill(filled, new FluidStack(FluidRegistry.getFluid("steam"), MAX_STEAM), true);

		return filled;
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		FluidStack fluid = this.getFluid(stack);
		if((fluid != null) && (fluid.amount > 0))
		{
			String str = fluid.getFluid().getName();
			int amount = fluid.amount;

			list.add("Holding " + amount + "mB of " + str);
			list.add("(That's about " + (amount / 1000) + " buckets)");
		}
		else
			list.add("Empty");
	}

	@Override
	public FluidStack getFluid(ItemStack container)
	{
		if((container.stackTagCompound == null) || !container.stackTagCompound.hasKey("Fluid"))
			return null;
		return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
	}

	@Override
	public int getCapacity(ItemStack container)
	{
		return MAX_STEAM;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill)
	{
		if(resource == null)
			return 0;

		if(!doFill)
		{
			if((container.stackTagCompound == null) || !container.stackTagCompound.hasKey("Fluid"))
				return Math.min(MAX_STEAM, resource.amount);

			FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

			if(stack == null)
				return Math.min(MAX_STEAM, resource.amount);

			if(!stack.isFluidEqual(resource))
				return 0;

			return Math.min(MAX_STEAM - stack.amount, resource.amount);
		}

		if(container.stackTagCompound == null)
			container.stackTagCompound = new NBTTagCompound();

		if(!container.stackTagCompound.hasKey("Fluid"))
		{
			NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

			if(MAX_STEAM < resource.amount)
			{
				fluidTag.setInteger("Amount", MAX_STEAM);
				container.stackTagCompound.setTag("Fluid", fluidTag);
				return MAX_STEAM;
			}

			container.stackTagCompound.setTag("Fluid", fluidTag);
			return resource.amount;
		}

		NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
		FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

		if(!stack.isFluidEqual(resource))
			return 0;

		int filled = MAX_STEAM - stack.amount;
		if(resource.amount < filled)
		{
			stack.amount += resource.amount;
			filled = resource.amount;
		}
		else
			stack.amount = MAX_STEAM;

		container.stackTagCompound.setTag("Fluid", stack.writeToNBT(fluidTag));

		this.updateCanisterDamage(container);

		return filled;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
	{
		if((container.stackTagCompound == null) || !container.stackTagCompound.hasKey("Fluid"))
			return null;

		FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

		if(stack == null)
			return null;

		stack.amount = Math.min(stack.amount, maxDrain);

		if(doDrain)
		{
			if(maxDrain >= MAX_STEAM)
			{
				container.stackTagCompound.removeTag("Fluid");

				if(container.stackTagCompound.hasNoTags())
					container.stackTagCompound = null;

				return stack;
			}

			NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
			fluidTag.setInteger("Amount", fluidTag.getInteger("Amount") - stack.amount);
			container.stackTagCompound.setTag("Fluid", fluidTag);
		}

		this.updateCanisterDamage(container);

		return stack;
	}

	private void updateCanisterDamage(ItemStack canister)
	{
		FluidStack stack = FluidStack.loadFluidStackFromNBT(canister.stackTagCompound.getCompoundTag("Fluid"));

		canister.setItemDamage(canister.getMaxDamage() - (stack.amount / 100));
	}

	public int getFluidAmount(ItemStack stack)
	{
		FluidStack fluid = this.getFluid(stack);

		if(fluid == null)
			return 0;

		return fluid.amount;
	}
}
