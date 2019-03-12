package com.theredmajora.dungeontools;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("dungeontools")
public class DungeonTools
{	
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String ID = "dungeontools";
    public static final String VERSION = "2.0";
    
	public static final ItemGroup DUNGEON = (new ItemGroup("dungeon") {
	      @OnlyIn(Dist.CLIENT)
	      public ItemStack createIcon() {
	         return new ItemStack(DungeonItems.lantern);
	      }
	   });

	   public DungeonTools() {
	        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modInit);
	        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);

	        MinecraftForge.EVENT_BUS.register(this);
	    }
	   
    private void modInit(final FMLCommonSetupEvent event)
    {
    	//GameRegistry.registerTileEntity(TileEntityGroundItem.class, new ResourceLocation(ID, "tile_entity_ground_item_block"));
    	//GameRegistry.registerTileEntity(TileEntityVanish.class, new ResourceLocation(ID, "tile_entity_vanish"));
    	//GameRegistry.registerTileEntity(TileEntityPushBlock.class, new ResourceLocation(ID, "tile_entity_push_block"));
    	//GameRegistry.registerTileEntity(TileEntityLockedChest.class, new ResourceLocation(MODID + ":" + "tile_entity_locked_chest"));
    	
    	LOGGER.info("[DungeonTools v" + VERSION + "] Succesfully Loaded!");
    	//DungeonConfig.createConfig(event.);
    }

    private void clientInit(final FMLClientSetupEvent event)
    { 
    }
    
    @EventBusSubscriber(modid = DungeonTools.ID)
    public static class RegistryHandler
    {
    	@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
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
    						//DungeonTools.proxy.registerItemRenderer(item, 0);
    					}
    				}
    				
    				if (Block.class.isAssignableFrom(f.getType()))
    				{
    					Block block = (Block) f.get(null);
    					if (block != null)
    					{
    						event.getRegistry().register(new ItemBlock(block, null).setRegistryName(block.getRegistryName()));
    						//DungeonTools.proxy.registerItemRenderer(Item.BLOCK_TO_ITEM.get(block), 0);
    						//if(block instanceof BlockChains) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockChains.VANISH).build());
    						//if(block instanceof BlockLock) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLock.VANISH).build());
    						//if(block instanceof BlockLockedDoor) ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLockedDoor.POWERED).build());
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
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
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
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	/**@SubscribeEvent
    	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    	{
    		event.getRegistry().register(new RecipeLantern().setRegistryName(new ResourceLocation(DungeonTools.ID, "recipe_lantern")));
    	}*/
    	
    	@SubscribeEvent
        public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().registerAll(
            		DungeonSounds.lock,
            	    DungeonSounds.success,
                    DungeonSounds.fail
            );
        }
    }
}
