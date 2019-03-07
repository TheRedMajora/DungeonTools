package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLock extends BlockVanish implements IUnlockable, IColorType
{
	public String type;
	
	public BlockLock(String type)
	{
		super(Material.IRON, "lock_" + type, true);
		this.type = type;
	}
	
	public String getType() { return this.type; }

    @Override
    public boolean getVanishFlag(IBlockState state)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? state.getBlock() instanceof BlockVanishLock || state.getBlock() instanceof BlockVanishChains : false;
        return (state.getBlock() instanceof BlockLock || state.getBlock() instanceof BlockChains || flag) && ((IColorType) state.getBlock()).getType().equals(this.getType());
    }
	
	@Override
	public boolean unlock(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, ((BlockVanish) state.getBlock()).getVanishedState(state));
		return true;
	}
}