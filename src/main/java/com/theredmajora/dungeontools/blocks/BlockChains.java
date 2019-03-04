package com.theredmajora.dungeontools.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockChains extends BlockDungeon
{
	public String type;
	
	public BlockChains(String type)
	{
		super(Material.iron, "chain_" + type);
        this.setBlockUnbreakable();
		this.type = type;
	}
	
	public void unlockChains(int x, int y, int z, World world, EntityPlayer player, String type2)
	{
		world.setBlockToAir(x, y, z);
		
		if(world.getBlock(x + 1, y, z) instanceof BlockChains)
		{
			BlockChains block = (BlockChains) world.getBlock(x + 1, y, z);
			
			if(block.getType() == type2)
			{
				block.unlockChains(x + 1, y, z, world, player, type2);
			}
		}
		else if(world.getBlock(x + 1, y, z) instanceof BlockLock)
		{
			BlockLock block = (BlockLock) world.getBlock(x + 1, y, z);
			
			if(block.getType() == type2)
			{
				block.unlockLock(x + 1, y, z, world, player, type2);
			}
		}
		if(world.getBlock(x - 1, y, z) instanceof BlockChains)
		{
			BlockChains block = (BlockChains) world.getBlock(x - 1, y, z);
			
			if(block.getType() == type2)
			{
				block.unlockChains(x - 1, y, z, world, player, type2);
			}
		}
		else if(world.getBlock(x - 1, y, z) instanceof BlockLock)
		{
			BlockLock block = (BlockLock) world.getBlock(x - 1, y, z);
			
			if(block.getType() == type2)
			{
				block.unlockLock(x - 1, y, z, world, player, type2);
			}
		}
		if(world.getBlock(x, y + 1, z) instanceof BlockChains)
		{
			BlockChains block = (BlockChains) world.getBlock(x, y + 1, z);
			
			if(block.getType() == type2)
			{
				block.unlockChains(x, y + 1, z, world, player, type2);
			}
		}
		else if(world.getBlock(x, y + 1, z) instanceof BlockLock)
		{
			BlockLock block = (BlockLock) world.getBlock(x, y + 1, z);
			
			if(block.getType() == type2)
			{
				block.unlockLock(x, y + 1, z, world, player, type2);
			}
		}
		if(world.getBlock(x, y - 1, z) instanceof BlockChains)
		{
			BlockChains block = (BlockChains) world.getBlock(x, y - 1, z);
			
			if(block.getType() == type2)
			{
				block.unlockChains(x, y - 1, z, world, player, type2);
			}
		}
		else if(world.getBlock(x, y - 1, z) instanceof BlockLock)
		{
			BlockLock block = (BlockLock) world.getBlock(x, y - 1, z);
			
			if(block.getType() == type2)
			{
				block.unlockLock(x, y - 1, z, world, player, type2);
			}
		}
		if(world.getBlock(x, y, z + 1) instanceof BlockChains)
		{
			BlockChains block = (BlockChains) world.getBlock(x, y, z + 1);
			
			if(block.getType() == type2)
			{
				block.unlockChains(x, y, z + 1, world, player, type2);
			}
		}
		else if(world.getBlock(x, y, z + 1) instanceof BlockLock)
		{
			BlockLock block = (BlockLock) world.getBlock(x, y, z + 1);
			
			if(block.getType() == type2)
			{
				block.unlockLock(x, y, z + 1, world, player, type2);
			}
		}
		if(world.getBlock(x, y, z - 1) instanceof BlockChains)
		{
			BlockChains block = (BlockChains) world.getBlock(x, y, z - 1);
			
			if(block.getType() == type2)
			{
				block.unlockChains(x, y, z - 1, world, player, type2);
			}
		}
		else if(world.getBlock(x, y, z - 1) instanceof BlockLock)
		{
			BlockLock block = (BlockLock) world.getBlock(x, y, z - 1);
			
			if(block.getType() == type2)
			{
				block.unlockLock(x, y, z - 1, world, player, type2);
			}
		}
	}

	public String getType()
	{
		return type;
	}
}