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
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import steamcraft.common.tiles.TileBattery;

/**
 * @author warlordjones
 * 
 */
public class ModelBattery extends ModelBase
{
	// fields
	ModelRenderer base;
	ModelRenderer upright;
	ModelRenderer top;
	ModelRenderer jar;
	ModelRenderer lid;
	ModelRenderer wire;
	ModelRenderer jar2;
	ModelRenderer lid2;
	ModelRenderer wire2;
	ModelRenderer jar3;
	ModelRenderer lid3;
	ModelRenderer wire3;
	ModelRenderer jar4;
	ModelRenderer lid6;
	ModelRenderer wire6;
	ModelRenderer jar6;
	ModelRenderer lid4;
	ModelRenderer wire4;
	ModelRenderer jar5;
	ModelRenderer lid5;
	ModelRenderer wire5;

	public ModelBattery()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.base = new ModelRenderer(this, 0, 0);
		this.base.addBox(0F, 0F, 0F, 2, 1, 16);
		this.base.setRotationPoint(-2.5F, 16F, -12F);
		this.base.setTextureSize(64, 64);
		this.base.mirror = true;
		this.setRotation(this.base, 0F, 0F, 0F);
		this.upright = new ModelRenderer(this, 36, 0);
		this.upright.addBox(0F, 0F, 0F, 2, 5, 2);
		this.upright.setRotationPoint(-2.5F, 11F, -5F);
		this.upright.setTextureSize(64, 64);
		this.upright.mirror = true;
		this.setRotation(this.upright, 0F, 0F, 0F);
		this.top = new ModelRenderer(this, 0, 17);
		this.top.addBox(0F, 0F, 0F, 2, 1, 16);
		this.top.setRotationPoint(-2.5F, 10F, -12F);
		this.top.setTextureSize(64, 64);
		this.top.mirror = true;
		this.setRotation(this.top, 0F, 0F, 0F);
		this.jar = new ModelRenderer(this, 36, 7);
		this.jar.addBox(0F, 0F, 0F, 3, 4, 3);
		this.jar.setRotationPoint(-5.5F, 13F, -10F);
		this.jar.setTextureSize(64, 64);
		this.jar.mirror = true;
		this.setRotation(this.jar, 0F, 0F, 0F);
		this.lid = new ModelRenderer(this, 36, 15);
		this.lid.addBox(0F, 0F, 0F, 2, 1, 2);
		this.lid.setRotationPoint(-5F, 12F, -9.5F);
		this.lid.setTextureSize(64, 64);
		this.lid.mirror = true;
		this.setRotation(this.lid, 0F, 0F, 0F);
		this.wire = new ModelRenderer(this, 36, 19);
		this.wire.addBox(0F, 0F, 0F, 2, 1, 1);
		this.wire.setRotationPoint(-4.3F, 11F, -9F);
		this.wire.setTextureSize(64, 64);
		this.wire.mirror = true;
		this.setRotation(this.wire, 0F, 0F, 0F);
		this.jar2 = new ModelRenderer(this, 36, 7);
		this.jar2.addBox(0F, 0F, 0F, 3, 4, 3);
		this.jar2.setRotationPoint(-5.5F, 13F, -5.5F);
		this.jar2.setTextureSize(64, 64);
		this.jar2.mirror = true;
		this.setRotation(this.jar2, 0F, 0F, 0F);
		this.lid2 = new ModelRenderer(this, 36, 15);
		this.lid2.addBox(0F, 0F, 0F, 2, 1, 2);
		this.lid2.setRotationPoint(-5F, 12F, -5F);
		this.lid2.setTextureSize(64, 64);
		this.lid2.mirror = true;
		this.setRotation(this.lid2, 0F, 0F, 0F);
		this.wire2 = new ModelRenderer(this, 36, 19);
		this.wire2.addBox(0F, 0F, 0F, 2, 1, 1);
		this.wire2.setRotationPoint(-4.5F, 11F, -4.5F);
		this.wire2.setTextureSize(64, 64);
		this.wire2.mirror = true;
		this.setRotation(this.wire2, 0F, 0F, 0F);
		this.jar3 = new ModelRenderer(this, 36, 7);
		this.jar3.addBox(0F, 0F, 0F, 3, 4, 3);
		this.jar3.setRotationPoint(-5.5F, 13F, -1F);
		this.jar3.setTextureSize(64, 64);
		this.jar3.mirror = true;
		this.setRotation(this.jar3, 0F, 0F, 0F);
		this.lid3 = new ModelRenderer(this, 36, 15);
		this.lid3.addBox(0F, 0F, 0F, 2, 1, 2);
		this.lid3.setRotationPoint(-5F, 12F, -0.5F);
		this.lid3.setTextureSize(64, 64);
		this.lid3.mirror = true;
		this.setRotation(this.lid3, 0F, 0F, 0F);
		this.wire3 = new ModelRenderer(this, 36, 19);
		this.wire3.addBox(0F, 0F, 0F, 2, 1, 1);
		this.wire3.setRotationPoint(-4F, 11F, 0F);
		this.wire3.setTextureSize(64, 64);
		this.wire3.mirror = true;
		this.setRotation(this.wire3, 0F, 0F, 0F);
		this.jar4 = new ModelRenderer(this, 36, 7);
		this.jar4.addBox(0F, 0F, 0F, 3, 4, 3);
		this.jar4.setRotationPoint(-0.5F, 13F, -10F);
		this.jar4.setTextureSize(64, 64);
		this.jar4.mirror = true;
		this.setRotation(this.jar4, 0F, 0F, 0F);
		this.lid6 = new ModelRenderer(this, 36, 15);
		this.lid6.addBox(0F, 0F, 0F, 2, 1, 2);
		this.lid6.setRotationPoint(0F, 12F, -0.5F);
		this.lid6.setTextureSize(64, 64);
		this.lid6.mirror = true;
		this.setRotation(this.lid6, 0F, 0F, 0F);
		this.wire6 = new ModelRenderer(this, 36, 19);
		this.wire6.addBox(0F, 0F, 0F, 2, 1, 1);
		this.wire6.setRotationPoint(-0.5F, 11F, 0F);
		this.wire6.setTextureSize(64, 64);
		this.wire6.mirror = true;
		this.setRotation(this.wire6, 0F, 0F, 0F);
		this.jar6 = new ModelRenderer(this, 36, 7);
		this.jar6.addBox(0F, 0F, 0F, 3, 4, 3);
		this.jar6.setRotationPoint(-0.5F, 13F, -1F);
		this.jar6.setTextureSize(64, 64);
		this.jar6.mirror = true;
		this.setRotation(this.jar6, 0F, 0F, 0F);
		this.lid4 = new ModelRenderer(this, 36, 15);
		this.lid4.addBox(0F, 0F, 0F, 2, 1, 2);
		this.lid4.setRotationPoint(0F, 12F, -9.5F);
		this.lid4.setTextureSize(64, 64);
		this.lid4.mirror = true;
		this.setRotation(this.lid4, 0F, 0F, 0F);
		this.wire4 = new ModelRenderer(this, 36, 19);
		this.wire4.addBox(0F, 0F, 0F, 2, 1, 1);
		this.wire4.setRotationPoint(-0.5F, 11F, -9F);
		this.wire4.setTextureSize(64, 64);
		this.wire4.mirror = true;
		this.setRotation(this.wire4, 0F, 0F, 0F);
		this.jar5 = new ModelRenderer(this, 36, 7);
		this.jar5.addBox(0F, 0F, 0F, 3, 4, 3);
		this.jar5.setRotationPoint(-0.5F, 13F, -5.5F);
		this.jar5.setTextureSize(64, 64);
		this.jar5.mirror = true;
		this.setRotation(this.jar5, 0F, 0F, 0F);
		this.lid5 = new ModelRenderer(this, 36, 15);
		this.lid5.addBox(0F, 0F, 0F, 2, 1, 2);
		this.lid5.setRotationPoint(0F, 12F, -5F);
		this.lid5.setTextureSize(64, 64);
		this.lid5.mirror = true;
		this.setRotation(this.lid5, 0F, 0F, 0F);
		this.wire5 = new ModelRenderer(this, 36, 19);
		this.wire5.addBox(0F, 0F, 0F, 2, 1, 1);
		this.wire5.setRotationPoint(-0.5F, 11F, -4.5F);
		this.wire5.setTextureSize(64, 64);
		this.wire5.mirror = true;
		this.setRotation(this.wire5, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.base.render(f5);
		this.upright.render(f5);
		this.top.render(f5);

		TileBattery te = (TileBattery) tile;
		ModelRenderer[] jars = { this.jar, this.jar2, this.jar3, this.jar4, this.jar5, this.jar6 };
		ModelRenderer[] lids = { this.lid, this.lid2, this.lid3, this.lid4, this.lid5, this.lid6 };
		ModelRenderer[] wires = { this.wire, this.wire2, this.wire3, this.wire4, this.wire5, this.wire6 };
		if(tile != null)
			for(int i = 0; i < jars.length; i++)
			{
				if(te.inventory[i] != null)
				{
					jars[i].render(f5);
					lids[i].render(f5);
					wires[i].render(f5);
				}
				if(te.inventory == null)
				{
					jars[i].render(f5);
					lids[i].render(f5);
					wires[i].render(f5);
				}
			}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
