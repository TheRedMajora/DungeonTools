package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDungeon extends Block
{
	public BlockDungeon(Material mat, String name)
	{
		super(mat);
		this.setBlockName(name);	 
		this.setBlockTextureName(DungeonTools.ModID + ":" + name);
		this.setCreativeTab(DungeonTools.bwelluTab);
	}

}
