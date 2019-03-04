package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockVanishLock extends BlockVanish implements IUnlockable, IColorType
{   
	public String type;
	
	public BlockVanishLock(String type)
	{
		super(Material.IRON, "vanish_lock_" + type, false);
        this.setBlockUnbreakable();
		this.type = type;
	}
	
	public String getType() { return this.type; }
	
    @Override
    public boolean getVanishFlag(IBlockState state)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? state.getBlock() instanceof BlockLock || state.getBlock() instanceof BlockChains : false;
        return (state.getBlock() instanceof BlockVanishLock || state.getBlock() instanceof BlockVanishChains || flag) && ((IColorType) state.getBlock()).getType().equals(this.getType());
    }

	@Override
	public boolean unlock(World world, EntityPlayer player, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, ((BlockVanish) state.getBlock()).getVanishedState(state));
		return true;
	}
}