package com.theredmajora.dungeontools.client;

import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityGroundItemRenderer extends TileEntitySpecialRenderer<TileEntityGroundItem>
{
	@Override
	public void render(TileEntityGroundItem te, double x, double y, double z, float pT, int dS, float a)
	{
		ItemStack stack = te.getItemStackForRender();
		
		if (stack != null && !stack.isEmpty())
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5D, y + 0.025D, z + 0.5D);
			GlStateManager.enableRescaleNormal();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(te.getRotation(), 0.0F, 0.0F, 1F);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
		}
	}
}
