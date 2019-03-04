package com.theredmajora.dungeontools;

import com.theredmajora.dungeontools.blocks.BlockChains;
import com.theredmajora.dungeontools.blocks.BlockGroundItem;
import com.theredmajora.dungeontools.blocks.BlockHardWeb;
import com.theredmajora.dungeontools.blocks.BlockLock;
import com.theredmajora.dungeontools.blocks.BlockUnlitTorch;
import com.theredmajora.dungeontools.blocks.MetadataBlock;
import com.theredmajora.dungeontools.items.ItemDungeon;
import com.theredmajora.dungeontools.items.ItemKey;
import com.theredmajora.dungeontools.items.ItemLantern;
import com.theredmajora.dungeontools.items.ItemNBTTransfer;
import com.theredmajora.dungeontools.items.ItemWrittenNote;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;
import com.theredmajora.dungeontools.tileentity.TileEntityHardWeb;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class DungeonItems
{
	public static Block
		lock_basic, lock_red, lock_blue, lock_green, lock_yellow, lock_orange, lock_purple, lock_icy,
		lock_white, lock_black, lock_great, chain_basic, chain_red, chain_blue, chain_green, chain_yellow,
		chain_orange, chain_purple, chain_icy, chain_white, chain_black, chain_great, hard_web, unlit_torch,
		ground_item_block;
	
	public static Item
		key_basic, key_red, key_blue, key_green, key_yellow, key_orange, key_purple, key_icy, key_white,key_black,
		key_great, nbt_transfer, written_note, lantern, lantern_oil;

	public static void init()
	{
		GameRegistry.registerBlock(lock_basic = new BlockLock("basic"), "lock_basic");
		GameRegistry.registerBlock(chain_basic = new BlockChains("basic"), "chain_basic");

		GameRegistry.registerBlock(lock_red = new BlockLock("red"), "lock_red");
        GameRegistry.registerBlock(chain_red = new BlockChains("red"), "chain_red");

		GameRegistry.registerBlock(lock_green = new BlockLock("green"), "lock_green");
        GameRegistry.registerBlock(chain_green = new BlockChains("green"), "chain_green");

		GameRegistry.registerBlock(lock_blue = new BlockLock("blue"), "lock_blue");
        GameRegistry.registerBlock(chain_blue = new BlockChains("blue"), "chain_blue");

		GameRegistry.registerBlock(lock_yellow = new BlockLock("yellow"), "lock_yellow");
        GameRegistry.registerBlock(chain_yellow = new BlockChains("yellow"), "chain_yellow");

		GameRegistry.registerBlock(lock_orange = new BlockLock("orange"), "lock_orange");
        GameRegistry.registerBlock(chain_orange = new BlockChains("orange"), "chain_orange");

		GameRegistry.registerBlock(lock_purple = new BlockLock("purple"), "lock_purple");
        GameRegistry.registerBlock(chain_purple = new BlockChains("purple"), "chain_purple");

		GameRegistry.registerBlock(lock_icy = new BlockLock("icy"), "lock_icy");
        GameRegistry.registerBlock(chain_icy = new BlockChains("icy"), "chain_icy");

		GameRegistry.registerBlock(lock_white = new BlockLock("white"), "lock_white");
        GameRegistry.registerBlock(chain_white = new BlockChains("white"), "chain_white");

		GameRegistry.registerBlock(lock_black = new BlockLock("black"), "lock_black");
        GameRegistry.registerBlock(chain_black = new BlockChains("black"), "chain_black");

		GameRegistry.registerBlock(lock_great = new BlockLock("great"), "lock_great");
        GameRegistry.registerBlock(chain_great = new BlockChains("great"), "chain_great");

		GameRegistry.registerBlock(hard_web = new BlockHardWeb(), MetadataBlock.class, "hard_web");
        GameRegistry.registerTileEntity(TileEntityHardWeb.class, "tile_entity_hard_web");

		GameRegistry.registerBlock(ground_item_block = new BlockGroundItem(), "ground_item_block");
        GameRegistry.registerTileEntity(TileEntityGroundItem.class, "tile_entity_ground_item");

		GameRegistry.registerBlock(unlit_torch = new BlockUnlitTorch(), "unlit_torch");
        
    	GameRegistry.registerItem(nbt_transfer = new ItemNBTTransfer(), "nbt_transfer");
    	GameRegistry.registerItem(written_note = new ItemWrittenNote(), "written_note");
		
        GameRegistry.registerItem(key_basic = new ItemKey("basic"), "key_basic");
        GameRegistry.registerItem(key_red = new ItemKey("red"), "key_red");
        GameRegistry.registerItem(key_green = new ItemKey("green"), "key_green");
        GameRegistry.registerItem(key_blue = new ItemKey("blue"), "key_blue");
        GameRegistry.registerItem(key_yellow = new ItemKey("yellow"), "key_yellow");
        GameRegistry.registerItem(key_orange = new ItemKey("orange"), "key_orange");
        GameRegistry.registerItem(key_purple = new ItemKey("purple"), "key_purple");
        GameRegistry.registerItem(key_icy = new ItemKey("icy"), "key_icy");
        GameRegistry.registerItem(key_white = new ItemKey("white"), "key_white");
        GameRegistry.registerItem(key_black = new ItemKey("black"), "key_black");
        GameRegistry.registerItem(key_great = new ItemKey("great"), "key_great");
		
    	GameRegistry.registerItem(lantern = new ItemLantern(), "lantern");
    	GameRegistry.registerItem(lantern_oil = new ItemDungeon("lantern_oil"), "lantern_oil");
		
		GameRegistry.addRecipe(new RecipeLantern());
		RecipeSorter.register(DungeonTools.ModID + ":" + "Lantern", RecipeLantern.class, Category.SHAPELESS, "after:forge:shapedore");
	}
}