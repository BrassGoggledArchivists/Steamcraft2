
package steamcraft.client.renderers.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import steamcraft.client.renderers.models.ModelVampireBat;
import steamcraft.common.entities.living.EntityVampireBat;
import steamcraft.common.lib.ModInfo;

public class RenderVampireBat extends RenderLiving
{
	private static final ResourceLocation batTextures = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/vampirebat.png");
	/**
	 * not actually sure this is size, is not used as of now, but the model
	 * would be recreated if the value changed and it seems a good match for a
	 * bats size
	 */
	private int renderedBatSize;

	public RenderVampireBat()
	{
		super(new ModelVampireBat(), 0.30F);
		this.renderedBatSize = ((ModelVampireBat) this.mainModel).getBatSize();
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void func_76986_a(T entity, double d, double d1, double d2, float f,
	 * float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	private void doRender(EntityVampireBat p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		int i = ((ModelVampireBat) this.mainModel).getBatSize();

		if (i != this.renderedBatSize)
		{
			this.renderedBatSize = i;
			this.mainModel = new ModelVampireBat();
		}

		super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	private ResourceLocation getEntityTexture(EntityVampireBat p_110775_1_)
	{
		return batTextures;
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before
	 * the model is rendered. Args: entityLiving, partialTickTime
	 */
	private void preRenderCallback(EntityVampireBat p_77041_1_, float p_77041_2_)
	{
		GL11.glScalef(0.35F, 0.35F, 0.35F);
	}

	/**
	 * Sets a simple glTranslate on a LivingEntity.
	 */
	private void renderLivingAt(EntityVampireBat p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_)
	{
		super.renderLivingAt(p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
	}

	private void rotateCorpse(EntityVampireBat p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		if (!p_77043_1_.getIsBatHanging())
		{
			GL11.glTranslatef(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
		}
		else
		{
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		}

		super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void func_76986_a(T entity, double d, double d1, double d2, float f,
	 * float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		this.doRender((EntityVampireBat) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before
	 * the model is rendered. Args: entityLiving, partialTickTime
	 */
	@Override
	protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
	{
		this.preRenderCallback((EntityVampireBat) p_77041_1_, p_77041_2_);
	}

	@Override
	protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		this.rotateCorpse((EntityVampireBat) p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
	}

	/**
	 * Sets a simple glTranslate on a LivingEntity.
	 */
	@Override
	protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_)
	{
		this.renderLivingAt((EntityVampireBat) p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void func_76986_a(T entity, double d, double d1, double d2, float f,
	 * float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		this.doRender((EntityVampireBat) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.getEntityTexture((EntityVampireBat) p_110775_1_);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void func_76986_a(T entity, double d, double d1, double d2, float f,
	 * float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		this.doRender((EntityVampireBat) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}
