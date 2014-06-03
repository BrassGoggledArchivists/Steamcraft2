package steamcraft.common.items.equipment;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.items.BaseItem;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModTool extends BaseItem
{
	protected static Block[] blocksEffectiveAgainst;
	public float efficiencyOnProperMaterial = 4.0F;
	public float damageVsEntity;
	protected ToolMaterial toolMaterial;

	@SuppressWarnings("all")
	protected ItemModTool(float damage, ToolMaterial toolMat, Block[] blockArray)
	{
		super();
		setCreativeTab(Steamcraft.tabSC2);
		this.toolMaterial = toolMat;
		ItemModTool.blocksEffectiveAgainst = blockArray;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
		this.efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();

		/*
		 * This makes the damage of steam tools negative
		 *
		if(this.toolMaterial == SC2_Material.STEAM_TOOL)
		{
			damageVsEntity = damage - (int) Math.round(this.getMaxDamage() * 5 / 320);
		}
		 */

		this.damageVsEntity = damage + toolMat.getDamageVsEntity();
	}
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
            itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
    }

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		for(int i = 0; i < blocksEffectiveAgainst.length; i++)
		{
			if(blocksEffectiveAgainst[i] == block)
			{
				if(this.toolMaterial == MaterialHelper.STEAM_TOOL)
				{
					return (efficiencyOnProperMaterial - (((float) stack.getItemDamage()) * 11 / 320));
				}

				return efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		itemstack.damageItem(2, living2);
		return true;
	}

	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}

	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
	{
		Item item = stack2.getItem();
		return this.toolMaterial.func_150995_f() == item ? true : super.getIsRepairable(stack1, stack2);
	}

	@SuppressWarnings("all")
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));
		return multimap;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase living)
	{
		if(toolMaterial == MaterialHelper.STEAM_TOOL)
		{
			System.out.println(efficiencyOnProperMaterial - (((float) stack.getItemDamage()) * 11 / 320));
			//int itemDamage = stack.getItemDamage();
		}

		stack.damageItem(1, living);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(toolMaterial==MaterialHelper.STEAM_TOOL)
		{
			//if(!ClientHelper.isShiftKeyDown())
			//{
			//	list.add(ClientHelper.shiftForInfo);
				return;
			//}

			//list.add("\u00A77"+ (this.getMaxDamage() - itemStack.getItemDamage()) + "/" + this.getMaxDamage() + " steam");
		}
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(toolMaterial==MaterialHelper.STEAM_TOOL)
			{
				if(player.inventory.hasItem(ConfigItems.itemCanisterSteam))
				{
					int i = 0;

					while(stack.getItemDamage() != 0 && i < 36)
					{
						if(player.inventory.mainInventory[i]!=null && player.inventory.mainInventory[i] == new ItemStack(ConfigItems.itemCanisterSteam))
						{
							while(player.inventory.mainInventory[i].getItemDamage() < ConfigItems.itemCanisterSteam.getMaxDamage() && stack.getItemDamage() > 0)
							{
								player.inventory.mainInventory[i].damageItem(1, player);
								stack.setItemDamage(stack.getItemDamage() - 1);
							}
						}

						i++;
					}
				}
			}
		}

		return stack;
	}
}
