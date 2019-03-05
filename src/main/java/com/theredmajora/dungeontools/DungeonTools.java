package com.theredmajora.dungeontools;

import java.lang.reflect.Field;

import com.theredmajora.dungeontools.blocks.BlockChains;
import com.theredmajora.dungeontools.blocks.BlockLock;
import com.theredmajora.dungeontools.blocks.BlockLockedDoor;
import com.theredmajora.dungeontools.client.ClientProxy;
import com.theredmajora.dungeontools.extra.EntityFallingPushBlock;
import com.theredmajora.dungeontools.extra.RecipeLantern;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;
import com.theredmajora.dungeontools.tileentity.TileEntityPushBlock;
import com.theredmajora.dungeontools.tileentity.TileEntityVanish;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = DungeonTools.ID, version = DungeonTools.VERSION)
public class DungeonTools
{
	@Instance
	public static DungeonTools instance = new DungeonTools();
	
    public static final String ID = "dungeontools";
    public static final String VERSION = "1.0";
    
    public static CreativeTabs dungeonTab = new CreativeTabs("dungeonTab")
    {
    	@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(DungeonItems.lantern);
		}
	};

    @SidedProxy(clientSide="com.theredmajora.dungeontools.client.ClientProxy")
    public static ClientProxy proxy;
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	GameRegistry.registerTileEntity(TileEntityGroundItem.class, new ResourceLocation(ID, "tile_entity_ground_item_block"));
    	GameRegistry.registerTileEntity(TileEntityVanish.class, new ResourceLocation(ID, "tile_entity_vanish"));
    	GameRegistry.registerTileEntity(TileEntityPushBlock.class, new ResourceLocation(ID, "tile_entity_push_block"));
    	//GameRegistry.registerTileEntity(TileEntityLockedChest.class, new ResourceLocation(MODID + ":" + "tile_entity_locked_chest"));
    	proxy.init();
    	
    	System.out.print("[DungeonTools v" + VERSION + "] Succesfully Loaded!");
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    { proxy.preInit(); DungeonConfig.createConfig(event); }
    
    @EventBusSubscriber(modid = DungeonTools.ID)
    public static class RegistryHandler
    {
    	@SubscribeEvent
    	public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    	{
    		int ID = 0;
    		
    		EntityEntry[] entityList = { 
    				EntityEntryBuilder.create()
    			    .entity(EntityFallingPushBlock.class)
    			    .id(new ResourceLocation(DungeonTools.ID, "falling_push_block"), ID++)
    			    .name("falling_push_block")
    			    .tracker(160, 20, true)
    			    .build()
    			    
    		};
    		
    		for(EntityEntry entry : entityList)
    		{
    			event.getRegistry().register(entry);
    		}
    	}
    	
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event)
    	{	
    		try
    		{
    			for (Field f: DungeonItems.class.getFields())
    			{
    				if (Item.class.isAssignableFrom(f.getType()))
    				{
    					Item item = (Item) f.get(null);
    					if (item != null)
    					{
    						event.getRegistry().register(item);
    						DungeonTools.proxy.registerItemRenderer(item, 0);
    					}
    				}
    				
    				if (Block.class.isAssignableFrom(f.getType()))
    				{
    					Block block = (Block) f.get(null);
    					if (block != null)
    					{
    						event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    						DungeonTools.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0);
    						if(block instanceof BlockChains) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockChains.VANISH).build());
    						if(block instanceof BlockLock) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLock.VANISH).build());
    						if(block instanceof BlockLockedDoor) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLockedDoor.POWERED).build());
    					}
    				}
    				
    				/**if(BlockLockedChest.class.isAssignableFrom(f.getType()))
    				{
    					BlockLockedChest block = (BlockLockedChest) f.get(null);
    					if (block != null)
    					{
    						event.getRegistry().register(block.createItemBlock());
    						block.registerBlockModel();
    					}
    				}*/
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event)
    	{	
    		try
    		{
    			for (Field f: DungeonItems.class.getFields())
    			{
    				if (Block.class.isAssignableFrom(f.getType()))
    				{
    					Block block = (Block) f.get(null);
    					if (block != null)
    					{
    						event.getRegistry().register(block);
    					}
    				}
    				
    				/**if(BlockLockedChest.class.isAssignableFrom(f.getType()))
    				{
    					BlockLockedChest block = (BlockLockedChest) f.get(null);
    					if (block != null)
    					{
    						event.getRegistry().register(block);
    					}
    				}*/
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	@SubscribeEvent
    	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    	{
    		event.getRegistry().register(new RecipeLantern().setRegistryName(new ResourceLocation(DungeonTools.ID, "recipe_lantern")));
    	}
    	
    	@SubscribeEvent
        public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().registerAll(
            		DungeonSounds.lock,
            	    DungeonSounds.success,
                    DungeonSounds.fail
            );
        }
    }
}
