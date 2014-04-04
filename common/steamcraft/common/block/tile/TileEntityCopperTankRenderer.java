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
 * File created @ [3 Apr 2014, 09:25:27]
 */
package common.steamcraft.common.block.tile;

import java.awt.image.renderable.RenderContext;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import org.lwjgl.opengl.GL11;

/**
 * @author warlordjones
 *
 * 3 Apr 201409:25:27
 */
public class TileEntityCopperTankRenderer extends TileEntitySpecialRenderer {
	RenderBlocks renderBlocks = new RenderBlocks();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		bindTexture(TextureMap.locationBlocksTexture);
		TileEntityCopperTank tankTile = (TileEntityCopperTank)tileentity;
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

		IFluidTank internalTank = tankTile.getTank();

		FluidStack fluidStack = internalTank.getFluid();

		if (fluidStack != null && tankTile.getHeightForRender() > 0.05) {
			GL11.glDisable(GL11.GL_LIGHTING);

			final Fluid fluid = fluidStack.getFluid();

			final Icon texture = fluid.getStillIcon();
			final int color = fluid.getColor(fluidStack);
			bindTexture(getFluidSheet(fluid));
			Tessellator t = Tessellator.instance;
			//TODO: Make this thing actually render.
			t.addVertex(x, y, z);
			t.addVertex(x+1, y+1, z+1);
			t.addVertex(x+1, y+1, z);
			t.addVertex(x+1, y, z+1);
			t.addVertex(x, y+1, z+1);
			t.addVertex(x+1, y, z);
			t.addVertex(x, y+1, z);
			t.addVertex(x, y, z+1);
			t.draw();
			GL11.glEnable(GL11.GL_LIGHTING);
		}

		// may be disabled by other procedures
		GL11.glPopMatrix();

	}

	public static ResourceLocation getFluidSheet(FluidStack liquid) {
		if (liquid == null) return TextureMap.locationBlocksTexture;
		return getFluidSheet(liquid.getFluid());
	}

	/**
	 * @param liquid
	 */
	public static ResourceLocation getFluidSheet(Fluid liquid) {
		return TextureMap.locationBlocksTexture;
	}
}
