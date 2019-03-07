package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockChains extends BlockVanish implements IColorType
{
	public String type;
	
	public BlockChains(String type)
	{
		super(Material.iron, "chain_" + type, true);
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}

    @Override
    public boolean getVanishFlag(Block block)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? block instanceof BlockVanishLock || block instanceof BlockVanishChains : false;
        return (block instanceof BlockLock || block instanceof BlockChains || flag) && ((IColorType)block).getType().equals(this.getType());
    }
}