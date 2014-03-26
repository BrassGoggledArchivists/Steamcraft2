package common.steamcraft.common.block;

import java.util.Random;

import common.steamcraft.common.core.proxy.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCryoIce extends BlockMod {

	public BlockCryoIce(int id, Material mat) {
		super(id, mat);
		slipperiness = 0.7F;
		blockHardness = 0.5F;
		blockResistance = 0.01F;
		stepSound = Block.soundSnowFootstep;
	}
    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity 
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) 
    {
    	this.applyDeepFreeze(par5Entity, par1World, par2, par3, par4);
    }
	public void applyDeepFreeze(Entity entity, World world, int x, int y, int z)
	{
	entity.extinguish();
	entity.setInvisible(false);
	if(entity instanceof EntityLivingBase)
	{
	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 3, 128, true));
	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 3,64, true));
	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 3, 2, true));
	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 3, 2, true));
	((EntityLivingBase) entity).setInWeb();
	((EntityLivingBase) entity).setJumping(true);
	((EntityLivingBase) entity).rotationYaw = 0;
	((EntityLivingBase) entity).rotationPitch = 0;
	if(entity instanceof EntityZombie)
	{
		//The if statement has to be nested like this, sorry.
		if(((EntityZombie)entity).isVillager())
		{
			((EntityZombie)entity).startConversion(10);
		}
	}
	if(entity instanceof EntityPlayer)
	{
		//Should be a custom 'freeze' damage source
		((EntityPlayer)entity).attackEntityFrom(DamageSource.generic, 100F);
		world.setBlockToAir(x,y,z);
		world.setBlockToAir(x,y+1,z);
		//Insert Creation of Cryonically Frozen Player statue here
	}
	}
	}
}
