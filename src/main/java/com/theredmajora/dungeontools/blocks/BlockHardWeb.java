package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockHardWeb extends BlockVanish
{
    public BlockHardWeb()
    {
		super(Material.web, "hard_web", true);
    }
    
    @Override
	public void registerBlockIcons(IIconRegister reg)
	{
    	super.registerBlockIcons(reg);
		this.vanishIcon = reg.registerIcon(DungeonTools.ModID + ":" + "hard_web_fire");
	}
}