package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockRedstoneChains extends BlockVanish
{
	public boolean doesVanish;
	
	public BlockRedstoneChains(boolean vanish)
	{
		super(Material.iron, vanish ? "vanish_chain_redstone" : "chain_redstone", !vanish);
        doesVanish = vanish;
	}

	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    { if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z)) world.setBlockMetadataWithNotify(x, y, z, 1, 3); }
    @Override
    public boolean getVanishFlag(Block block)
    {  return block instanceof BlockRedstoneChains && (DungeonConfig.connectedLocks ? true : ((BlockRedstoneChains)block).doesVanish == doesVanish); }
}