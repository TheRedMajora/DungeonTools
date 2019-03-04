package com.theredmajora.dungeontools.client;

import com.theredmajora.dungeontools.extra.EntityFallingPushBlock;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy
{
	public void init()
	{ ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGroundItem.class, new TileEntityGroundItemRenderer()); }
	public void preInit()
	{ RenderingRegistry.registerEntityRenderingHandler(EntityFallingPushBlock.class, new RenderFallingPushBlockFactory()); }
	
	public void registerItemRenderer(Item item)
	{ registerItemRenderer(item, 0); }
	
	public void registerItemRenderer(Item item, int meta)
	{ ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory")); }
	
	public void openWrittenNote(EntityPlayer player, ItemStack item)
	{ Minecraft.getMinecraft().displayGuiScreen(new GuiScreenNote(player, item)); }
	
	public class RenderFallingPushBlockFactory implements IRenderFactory<EntityFallingBlock> {
		@Override
		public Render<? super EntityFallingBlock> createRenderFor(RenderManager manager) {
			{ return new RenderFallingBlock(manager); }
		}
	}
}
