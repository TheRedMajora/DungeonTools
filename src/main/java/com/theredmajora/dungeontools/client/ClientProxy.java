package com.theredmajora.dungeontools.client;

import com.theredmajora.dungeontools.ServerProxy;
import com.theredmajora.dungeontools.blocks.BlockChains;
import com.theredmajora.dungeontools.blocks.BlockLock;
import com.theredmajora.dungeontools.blocks.BlockLockedDoor;
import com.theredmajora.dungeontools.extra.EntityFallingPushBlock;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
	public void init()
	{ ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGroundItem.class, new TileEntityGroundItemRenderer()); }
	public void preInit()
	{ RenderingRegistry.registerEntityRenderingHandler(EntityFallingPushBlock.class, new RenderFallingPushBlockFactory()); }
	
	public void registerItemRenderer(Item item)
	{ registerItemRenderer(item, 0); }
	
	public void registerItemRenderer(Item item, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		Block block = Block.getBlockFromItem(item);
		if(block instanceof BlockChains) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockChains.VANISH).build());
		if(block instanceof BlockLock) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLock.VANISH).build());
		if(block instanceof BlockLockedDoor) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLockedDoor.POWERED).build());
	}
	
	public void openWrittenNote(EntityPlayer player, ItemStack item)
	{ Minecraft.getMinecraft().displayGuiScreen(new GuiScreenNote(player, item)); }
	
	public class RenderFallingPushBlockFactory implements IRenderFactory<EntityFallingPushBlock> {
		@Override
		public Render<? super EntityFallingPushBlock> createRenderFor(RenderManager manager) {
			{ return new RenderFallingPushBlock(manager); }
		}
	}
}
