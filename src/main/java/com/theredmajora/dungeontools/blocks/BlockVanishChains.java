package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVanishChains extends BlockVanish implements IColorType
{
	public String type;
	
	public BlockVanishChains(String type)
	{
		super(Material.iron, "vanish_chain_" + type, false);
		this.type = type;
	}
	
	public String getType()
	{ return type; }

    @Override
    public boolean getVanishFlag(Block block)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? block instanceof BlockLock || block instanceof BlockChains : false;
        return (block instanceof BlockVanishLock || block instanceof BlockVanishChains || flag) && ((IColorType)block).getType().equals(this.getType());
    }
}