package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.items.ItemKey;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockLock extends BlockDungeon
{
	public String type;
	
	public BlockLock(String type)
	{
		super(Material.iron, "lock_" + type);
        this.setBlockUnbreakable();
		this.type = type;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var, float j, float k, float l)
    {	
    	if (!world.isRemote && player.getHeldItem() != null)
    	{
    		if(player.getHeldItem().getItem() instanceof ItemKey)
    		{
    			ItemKey key = (ItemKey) player.getHeldItem().getItem();
    			
    			if(key.type == getType())
    			{
    				player.getHeldItem().stackSize--;
    		        world.playSoundAtEntity(player, DungeonTools.ModID + ":" + "unlock.door", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    	    		unlockLock(x, y, z, world, player, type);
    			}
    			else
    			{
    		        world.playSoundAtEntity(player, DungeonTools.ModID + ":" + "unlock.fail", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    			}
    			
    			return true;
    		}
        }

    	return false;
    }
	
	public void unlockLock(int x, int y, int z, World world, EntityPlayer player, String type2)
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
}