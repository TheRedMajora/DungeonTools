package com.theredmajora.dungeontools;

import com.theredmajora.dungeontools.blocks.BlockChains;
import com.theredmajora.dungeontools.blocks.BlockGroundItem;
import com.theredmajora.dungeontools.blocks.BlockHardWeb;
import com.theredmajora.dungeontools.blocks.BlockLock;
import com.theredmajora.dungeontools.blocks.BlockLockedDoor;
import com.theredmajora.dungeontools.blocks.BlockPush;
import com.theredmajora.dungeontools.blocks.BlockPushSwitch;
import com.theredmajora.dungeontools.blocks.BlockRedstoneChains;
import com.theredmajora.dungeontools.blocks.BlockUnlitTorch;
import com.theredmajora.dungeontools.blocks.BlockVanishChains;
import com.theredmajora.dungeontools.blocks.BlockVanishLock;
import com.theredmajora.dungeontools.items.ItemGauntlet;
import com.theredmajora.dungeontools.items.ItemKey;
import com.theredmajora.dungeontools.items.ItemLantern;
import com.theredmajora.dungeontools.items.ItemLockedDoor;
import com.theredmajora.dungeontools.items.ItemNBTTransfer;
import com.theredmajora.dungeontools.items.ItemWrittenNote;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class DungeonItems
{
	public static Block lock_basic = new BlockLock("basic");
	public static Block lock_vanish_basic = new BlockVanishLock("basic");
	public static Block chain_basic = new BlockChains("basic");
	public static Block chain_vanish_basic = new BlockVanishChains("basic");
	
	public static Block lock_red = new BlockLock("red");
	public static Block lock_vanish_red = new BlockVanishLock("red");
    public static Block chain_red = new BlockChains("red");
    public static Block chain_vanish_red = new BlockVanishChains("red");

	public static Block lock_green = new BlockLock("green");
	public static Block lock_vanish_green = new BlockVanishLock("green");
    public static Block chain_green = new BlockChains("green");
    public static Block chain_vanish_green = new BlockVanishChains("green");

	public static Block lock_blue = new BlockLock("blue");
	public static Block lock_vanish_blue = new BlockVanishLock("blue");
    public static Block chain_blue = new BlockChains("blue");
    public static Block chain_vanish_blue = new BlockVanishChains("blue");

	public static Block lock_yellow = new BlockLock("yellow");
	public static Block lock_vanish_yellow = new BlockVanishLock("yellow");
    public static Block chain_yellow = new BlockChains("yellow");
    public static Block chain_vanish_yellow = new BlockVanishChains("yellow");

	public static Block lock_orange = new BlockLock("orange");
	public static Block lock_vanish_orange = new BlockVanishLock("orange");
    public static Block chain_orange = new BlockChains("orange");
    public static Block chain_vanish_orange = new BlockVanishChains("orange");

	public static Block lock_purple = new BlockLock("purple");
	public static Block lock_vanish_purple = new BlockVanishLock("purple");
    public static Block chain_purple = new BlockChains("purple");
    public static Block chain_vanish_purple = new BlockVanishChains("purple");

	public static Block lock_icy = new BlockLock("icy");
	public static Block lock_vanish_icy = new BlockVanishLock("icy");
    public static Block chain_icy = new BlockChains("icy");
    public static Block chain_vanish_icy = new BlockVanishChains("icy");

	public static Block lock_white = new BlockLock("white");
	public static Block lock_vanish_white = new BlockVanishLock("white");
    public static Block chain_white = new BlockChains("white");
    public static Block chain_vanish_white = new BlockVanishChains("white");

	public static Block lock_black = new BlockLock("black");
	public static Block lock_vanish_black = new BlockVanishLock("black");
    public static Block chain_black = new BlockChains("black");
    public static Block chain_vanish_black = new BlockVanishChains("black");

	public static Block lock_great = new BlockLock("great");
	public static Block lock_vanish_great = new BlockVanishLock("great");
    public static Block chain_great = new BlockChains("great");
    public static Block chain_vanish_great = new BlockVanishChains("great");

    public static Block chain_redstone = new BlockRedstoneChains(false);
    public static Block chain_vanish_redstone = new BlockRedstoneChains(true);
    
	public static Block hard_web = new BlockHardWeb();
	public static Block ground_item_block = new BlockGroundItem();
	public static Block unlit_torch = new BlockUnlitTorch();
    
	public static Block push_block = new BlockPush(false);
	public static Block push_block_heavy = new BlockPush(true);
	public static Block push_switch = new BlockPushSwitch(false);
	public static Block push_switch_heavy = new BlockPushSwitch(true);
	
	public static Item nbt_transfer = new ItemNBTTransfer();
	public static Item written_note = new ItemWrittenNote();
	
    public static Item key_basic = new ItemKey("basic");
    public static Item key_red = new ItemKey("red");
    public static Item key_green = new ItemKey("green");
    public static Item key_blue = new ItemKey("blue");
    public static Item key_yellow = new ItemKey("yellow");
    public static Item key_orange = new ItemKey("orange");
    public static Item key_purple = new ItemKey("purple");
    public static Item key_icy = new ItemKey("icy");
    public static Item key_white = new ItemKey("white");
    public static Item key_black = new ItemKey("black");
    public static Item key_great = new ItemKey("great");

    public static Item key_skeleton = new ItemKey("skeleton");
    
	public static Item lantern = new ItemLantern();
	public static Item lantern_oil = new Item(null).setRegistryName("lantern_oil");

	public static Item gauntlet = new ItemGauntlet();

	public static Block door_great = new BlockLockedDoor(Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 2000.0F), "great");
	public static Block door_locked_great = new BlockLockedDoor(Properties.from(door_great), "locked_great");
	public static Block door_locked_basic = new BlockLockedDoor(Properties.create(Material.WOOD).hardnessAndResistance(50.0F, 2000.0F), "locked_basic");
	public static Block door_locked_red = new BlockLockedDoor(Properties.from(door_locked_basic), "locked_red");
	public static Block door_locked_blue = new BlockLockedDoor(Properties.from(door_locked_basic), "locked_blue");
	public static Block door_locked_green = new BlockLockedDoor(Properties.from(door_locked_basic), "locked_green");
	public static Block door_locked_orange = new BlockLockedDoor(Properties.from(door_great), "locked_orange");
	public static Block door_locked_icy = new BlockLockedDoor(Properties.from(door_great), "locked_icy");
	public static Block door_locked_purple = new BlockLockedDoor(Properties.from(door_great), "locked_purple");
	public static Block door_locked_yellow = new BlockLockedDoor(Properties.from(door_great), "locked_yellow");
	public static Block door_locked_white = new BlockLockedDoor(Properties.from(door_great), "locked_white");
	public static Block door_locked_black = new BlockLockedDoor(Properties.from(door_great), "locked_black");
	public static Block door_locked_redstone = new BlockLockedDoor(Properties.from(door_locked_basic), "locked_redstone");
	
	public static Item door_great_item = new ItemLockedDoor("great", door_great);
	public static Item door_locked_basic_item = new ItemLockedDoor("locked_basic", door_locked_basic);
	public static Item door_locked_red_item = new ItemLockedDoor("locked_red", door_locked_red);
	public static Item door_locked_blue_item = new ItemLockedDoor("locked_blue", door_locked_blue);
	public static Item door_locked_green_item = new ItemLockedDoor("locked_green", door_locked_green);
	public static Item door_locked_orange_item = new ItemLockedDoor("locked_orange", door_locked_orange);
	public static Item door_locked_purple_item = new ItemLockedDoor("locked_purple", door_locked_purple);
	public static Item door_locked_yellow_item = new ItemLockedDoor("locked_yellow", door_locked_yellow);
	public static Item door_locked_icy_item = new ItemLockedDoor("locked_icy", door_locked_icy);
	public static Item door_locked_black_item = new ItemLockedDoor("locked_black", door_locked_black);
	public static Item door_locked_white_item = new ItemLockedDoor("locked_white", door_locked_white);
	public static Item door_locked_great_item = new ItemLockedDoor("locked_great", door_locked_great);
	public static Item door_locked_redstone_item = new ItemLockedDoor("locked_redstone", door_locked_redstone);
	

	/**public static BlockLockedChest chest_basic = new BlockLockedChest("basic");
	public static BlockLockedChest chest_red = new BlockLockedChest("red");
	public static BlockLockedChest chest_blue = new BlockLockedChest("blue");
	public static BlockLockedChest chest_green = new BlockLockedChest("green");
	public static BlockLockedChest chest_orange = new BlockLockedChest("orange");
	public static BlockLockedChest chest_icy = new BlockLockedChest("icy");
	public static BlockLockedChest chest_purple = new BlockLockedChest("purple");
	public static BlockLockedChest chest_yellow = new BlockLockedChest("yellow");
	public static BlockLockedChest chest_white = new BlockLockedChest("white");
	public static BlockLockedChest chest_black = new BlockLockedChest("black");
	public static BlockLockedChest chest_great = new BlockLockedChest("great");*/
	
	//public static Item debug = new ItemDebug();
}