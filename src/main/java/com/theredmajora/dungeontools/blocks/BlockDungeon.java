package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDungeon extends Block
{
	public BlockDungeon(Material mat, String name)
	{
		super(mat);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);//DungeonTools.MODID + ":" + name);
		this.setCreativeTab(DungeonTools.dungeonTab);
	}

    @SideOnly(Side.CLIENT)
	public void registerBlockModel() { DungeonTools.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0); }
	public Item createItemBlock() { return new ItemBlock(this).setRegistryName(getRegistryName()); }
}
