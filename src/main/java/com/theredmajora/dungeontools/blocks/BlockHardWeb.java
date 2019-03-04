package com.theredmajora.dungeontools.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockHardWeb extends BlockVanish
{
   
    public BlockHardWeb()
    {
		super(Material.WEB, "hard_web", true);
		this.setBlockUnbreakable();
    }

    @Override
    public boolean getVanishFlag(IBlockState state)
    { 
        return state.getBlock() instanceof BlockHardWeb;
    }
}