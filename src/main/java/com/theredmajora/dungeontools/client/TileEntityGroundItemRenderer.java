package com.theredmajora.dungeontools.client;

import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityGroundItemRenderer extends TileEntityRenderer<TileEntityGroundItem>
{
	@Override
	public void render(TileEntityGroundItem te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		ItemStack stack = te.getItemStackForRender();
		
		if (stack != null && !stack.isEmpty())
		{
			GlStateManager.pushMatrix();
			GlStateManager.translated(x + 0.5D, y + 0.025D, z + 0.5D);
			GlStateManager.enableRescaleNormal();
			GlStateManager.scalef(0.5F, 0.5F, 0.5F);
			GlStateManager.rotatef(30.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(te.getRotation(), 0.0F, 0.0F, 1F);
			Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
		}
	}
}
