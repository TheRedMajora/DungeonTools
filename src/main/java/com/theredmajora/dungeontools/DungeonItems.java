package com.theredmajora.dungeontools;

import com.theredmajora.dungeontools.blocks.BlockChains;
import com.theredmajora.dungeontools.blocks.BlockDungeon;
import com.theredmajora.dungeontools.blocks.BlockGroundItem;
import com.theredmajora.dungeontools.blocks.BlockHardWeb;
import com.theredmajora.dungeontools.blocks.BlockLock;
import com.theredmajora.dungeontools.blocks.BlockLockedDoor;
import com.theredmajora.dungeontools.blocks.BlockPush;
import com.theredmajora.dungeontools.blocks.BlockPushSwitch;
import com.theredmajora.dungeontools.blocks.BlockUnlitTorch;
import com.theredmajora.dungeontools.blocks.BlockVanishChains;
import com.theredmajora.dungeontools.blocks.BlockVanishLock;
import com.theredmajora.dungeontools.items.ItemDebug;
import com.theredmajora.dungeontools.items.ItemDungeon;
import com.theredmajora.dungeontools.items.ItemKey;
import com.theredmajora.dungeontools.items.ItemLantern;
import com.theredmajora.dungeontools.items.ItemLockedDoor;
import com.theredmajora.dungeontools.items.ItemNBTTransfer;
import com.theredmajora.dungeontools.items.ItemWrittenNote;

import net.minecraft.block.material.Material;

public class DungeonItems
{
	public static BlockDungeon lock_basic = new BlockLock("basic");
	public static BlockDungeon lock_vanish_basic = new BlockVanishLock("basic");
	public static BlockDungeon chain_basic = new BlockChains("basic");
	public static BlockDungeon chain_vanish_basic = new BlockVanishChains("basic");

	public static BlockDungeon lock_red = new BlockLock("red");
	public static BlockDungeon lock_vanish_red = new BlockVanishLock("red");
    public static BlockDungeon chain_red = new BlockChains("red");
    public static BlockDungeon chain_vanish_red = new BlockVanishChains("red");

	public static BlockDungeon lock_green = new BlockLock("green");
	public static BlockDungeon lock_vanish_green = new BlockVanishLock("green");
    public static BlockDungeon chain_green = new BlockChains("green");
    public static BlockDungeon chain_vanish_green = new BlockVanishChains("green");

	public static BlockDungeon lock_blue = new BlockLock("blue");
	public static BlockDungeon lock_vanish_blue = new BlockVanishLock("blue");
    public static BlockDungeon chain_blue = new BlockChains("blue");
    public static BlockDungeon chain_vanish_blue = new BlockVanishChains("blue");

	public static BlockDungeon lock_yellow = new BlockLock("yellow");
	public static BlockDungeon lock_vanish_yellow = new BlockVanishLock("yellow");
    public static BlockDungeon chain_yellow = new BlockChains("yellow");
    public static BlockDungeon chain_vanish_yellow = new BlockVanishChains("yellow");

	public static BlockDungeon lock_orange = new BlockLock("orange");
	public static BlockDungeon lock_vanish_orange = new BlockVanishLock("orange");
    public static BlockDungeon chain_orange = new BlockChains("orange");
    public static BlockDungeon chain_vanish_orange = new BlockVanishChains("orange");

	public static BlockDungeon lock_purple = new BlockLock("purple");
	public static BlockDungeon lock_vanish_purple = new BlockVanishLock("purple");
    public static BlockDungeon chain_purple = new BlockChains("purple");
    public static BlockDungeon chain_vanish_purple = new BlockVanishChains("purple");

	public static BlockDungeon lock_icy = new BlockLock("icy");
	public static BlockDungeon lock_vanish_icy = new BlockVanishLock("icy");
    public static BlockDungeon chain_icy = new BlockChains("icy");
    public static BlockDungeon chain_vanish_icy = new BlockVanishChains("icy");

	public static BlockDungeon lock_white = new BlockLock("white");
	public static BlockDungeon lock_vanish_white = new BlockVanishLock("white");
    public static BlockDungeon chain_white = new BlockChains("white");
    public static BlockDungeon chain_vanish_white = new BlockVanishChains("white");

	public static BlockDungeon lock_black = new BlockLock("black");
	public static BlockDungeon lock_vanish_black = new BlockVanishLock("black");
    public static BlockDungeon chain_black = new BlockChains("black");
    public static BlockDungeon chain_vanish_black = new BlockVanishChains("black");

	public static BlockDungeon lock_great = new BlockLock("great");
	public static BlockDungeon lock_vanish_great = new BlockVanishLock("great");
    public static BlockDungeon chain_great = new BlockChains("great");
    public static BlockDungeon chain_vanish_great = new BlockVanishChains("great");

	public static BlockDungeon hard_web = new BlockHardWeb();
	public static BlockDungeon ground_item_block = new BlockGroundItem();
	public static BlockDungeon unlit_torch = new BlockUnlitTorch();
    
	public static BlockDungeon push_block = new BlockPush(false);
	public static BlockDungeon push_block_heavy = new BlockPush(true);
	public static BlockDungeon push_switch = new BlockPushSwitch(false);
	public static BlockDungeon push_switch_heavy = new BlockPushSwitch(true);
	
	public static ItemDungeon nbt_transfer = new ItemNBTTransfer();
	public static ItemDungeon written_note = new ItemWrittenNote();
	
    public static ItemDungeon key_basic = new ItemKey("basic");
    public static ItemDungeon key_red = new ItemKey("red");
    public static ItemDungeon key_green = new ItemKey("green");
    public static ItemDungeon key_blue = new ItemKey("blue");
    public static ItemDungeon key_yellow = new ItemKey("yellow");
    public static ItemDungeon key_orange = new ItemKey("orange");
    public static ItemDungeon key_purple = new ItemKey("purple");
    public static ItemDungeon key_icy = new ItemKey("icy");
    public static ItemDungeon key_white = new ItemKey("white");
    public static ItemDungeon key_black = new ItemKey("black");
    public static ItemDungeon key_great = new ItemKey("great");

    public static ItemDungeon key_skeleton = new ItemKey("skeleton");
    
	public static ItemDungeon lantern = new ItemLantern();
	public static ItemDungeon lantern_oil = new ItemDungeon("lantern_oil");
	
	public static BlockDungeon door_great = new BlockLockedDoor(Material.IRON, "great");
	public static BlockDungeon door_locked_great = new BlockLockedDoor(Material.ROCK, "locked_great");
	public static BlockDungeon door_locked_basic = new BlockLockedDoor(Material.WOOD, "locked_basic");
	public static BlockDungeon door_locked_red = new BlockLockedDoor(Material.WOOD, "locked_red");
	public static BlockDungeon door_locked_blue = new BlockLockedDoor(Material.WOOD, "locked_blue");
	public static BlockDungeon door_locked_green = new BlockLockedDoor(Material.WOOD, "locked_green");
	public static BlockDungeon door_locked_orange = new BlockLockedDoor(Material.ROCK, "locked_orange");
	public static BlockDungeon door_locked_icy = new BlockLockedDoor(Material.ROCK, "locked_icy");
	public static BlockDungeon door_locked_purple = new BlockLockedDoor(Material.ROCK, "locked_purple");
	public static BlockDungeon door_locked_yellow = new BlockLockedDoor(Material.ROCK, "locked_yellow");
	public static BlockDungeon door_locked_white = new BlockLockedDoor(Material.ROCK, "locked_white");
	public static BlockDungeon door_locked_black = new BlockLockedDoor(Material.ROCK, "locked_black");
	
	public static ItemDungeon door_great_item = new ItemLockedDoor("great", door_great);
	public static ItemDungeon door_locked_basic_item = new ItemLockedDoor("locked_basic", door_locked_basic);
	public static ItemDungeon door_locked_red_item = new ItemLockedDoor("locked_red", door_locked_red);
	public static ItemDungeon door_locked_blue_item = new ItemLockedDoor("locked_blue", door_locked_blue);
	public static ItemDungeon door_locked_green_item = new ItemLockedDoor("locked_green", door_locked_green);
	public static ItemDungeon door_locked_orange_item = new ItemLockedDoor("locked_orange", door_locked_orange);
	public static ItemDungeon door_locked_purple_item = new ItemLockedDoor("locked_purple", door_locked_purple);
	public static ItemDungeon door_locked_yellow_item = new ItemLockedDoor("locked_yellow", door_locked_yellow);
	public static ItemDungeon door_locked_icy_item = new ItemLockedDoor("locked_icy", door_locked_icy);
	public static ItemDungeon door_locked_black_item = new ItemLockedDoor("locked_black", door_locked_black);
	public static ItemDungeon door_locked_white_item = new ItemLockedDoor("locked_white", door_locked_white);
	public static ItemDungeon door_locked_great_item = new ItemLockedDoor("locked_great", door_locked_great);
	

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
	
	public static ItemDungeon debug = new ItemDebug();
}