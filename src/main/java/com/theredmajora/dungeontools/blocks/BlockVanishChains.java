package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockVanishChains extends BlockVanish implements IColorType
{   
	public String type;
	
	public BlockVanishChains(String type)
	{
		super(Material.IRON, "vanish_chain_" + type, false);
		this.type = type;
	}
	
	public String getType() { return this.type; }
	
    @Override
    public boolean getVanishFlag(IBlockState state)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? state.getBlock() instanceof BlockLock || state.getBlock() instanceof BlockChains : false;
        return (state.getBlock() instanceof BlockVanishLock || state.getBlock() instanceof BlockVanishChains || flag) && ((IColorType) state.getBlock()).getType().equals(this.getType());
    }
}