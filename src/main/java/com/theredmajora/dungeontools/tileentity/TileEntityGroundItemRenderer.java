package com.theredmajora.dungeontools.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class TileEntityGroundItemRenderer extends TileEntitySpecialRenderer
{
	RenderManager renderManager = RenderManager.instance;
	
	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float f)
	{
		ItemStack stack = ((TileEntityGroundItem) te).getItemStackForRender();
		
		if (stack != null)
		{
			if(stack.getItem() != Items.slime_ball) renderItem(stack, dx, dy, dz, ((TileEntityGroundItem) te).getRotation());
		}
	}
	
	
	public void renderItem(ItemStack item, double dx, double dy, double dz, int rotation)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(dx + 0.5D, dy, dz + 0.5D);
		bindTexture(TextureMap.locationItemsTexture);
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = item.getIconIndex();
		if (icon != null)
		{
			float minU = icon.getMinU();
			float maxU = icon.getMaxU();
			float minV = icon.getMinV();
			float maxV = icon.getMaxV();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(rotation, 0.0F, 0.0F, 1F);
			GL11.glTranslatef(-0.5F, -0.5F, 0F);
			ItemRenderer.renderItemIn2D(tessellator, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
			GL11.glPopMatrix();
		}
	}
}
