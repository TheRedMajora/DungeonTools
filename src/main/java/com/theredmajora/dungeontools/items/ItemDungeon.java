package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.item.Item;

public class ItemDungeon extends Item
{
	public ItemDungeon(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setTextureName(DungeonTools.ModID + ":" + name);
		this.setCreativeTab(DungeonTools.bwelluTab);
	}
}
