package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDungeon extends Item
{
	public ItemDungeon(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(DungeonTools.dungeonTab);
	}

    @SideOnly(Side.CLIENT)
	public void registerItemModel()
	{ DungeonTools.proxy.registerItemRenderer(this, 0); }
}
