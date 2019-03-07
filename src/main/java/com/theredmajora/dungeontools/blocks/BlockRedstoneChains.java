package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRedstoneChains extends BlockVanish
{
	public boolean doesVanish;
	
	public BlockRedstoneChains(boolean vanish)
	{
		super(Material.IRON, vanish ? "vanish_chain_redstone" : "chain_redstone", !vanish);
        doesVanish = vanish;
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    { if (!world.isRemote && world.isBlockPowered(pos)) world.setBlockState(pos, getVanishedState(getDefaultState())); }
    @Override
    public boolean getVanishFlag(IBlockState state)
    {  return (state.getBlock() instanceof BlockRedstoneChains) && (DungeonConfig.connectedLocks ? true : ((BlockRedstoneChains)state.getBlock()).doesVanish == doesVanish); }
}