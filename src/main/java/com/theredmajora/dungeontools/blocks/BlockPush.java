package com.theredmajora.dungeontools.blocks;

import java.util.Random;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPush extends Block
{
	boolean heavy;
    
	public BlockPush(boolean isHeavy)
	{
		super(Material.rock);
		String name = isHeavy ? "push_block_heavy" : "push_block";
		this.setBlockName(name);	 
		this.setBlockTextureName(DungeonTools.ModID + ":" + name);
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
		
		heavy = isHeavy;
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float j, float k, float l)
    {	
    	if(heavy && (player.getHeldItem() == null || !player.getHeldItem().getItem().equals(DungeonItems.gauntlet))) return false;
    	
    	if(player.isSneaking() && DungeonConfig.pullPushBlock)
    	{
    		switch(ForgeDirection.getOrientation(side))
        	{
    			case EAST:
    				updatePushBlock(world, x, y, z, x + 1, y, z);
    				break;
    			case NORTH:
    				updatePushBlock(world, x, y, z, x, y, z - 1);
    				break;
    			case SOUTH:
    				updatePushBlock(world, x, y, z, x, y, z + 1);
    				break;
    			case WEST:
    				updatePushBlock(world, x, y, z, x - 1, y, z);
    				break;
    			default:
    				break;
    		}
    	}
    	else
    	{
        	switch(ForgeDirection.getOrientation(side))
        	{
    			case EAST:
    				updatePushBlock(world, x, y, z, x - 1, y, z);
    				break;
    			case NORTH:
    				updatePushBlock(world, x, y, z, x, y, z + 1);
    				break;
    			case SOUTH:
    				updatePushBlock(world, x, y, z, x, y, z - 1);
    				break;
    			case WEST:
    				updatePushBlock(world, x, y, z, x + 1, y, z);
    				break;
    			default:
    				break;
        	}
    	}
    	
        world.scheduleBlockUpdate(x, y, z, this, 2);
    	return true;
    }
    
    public void updatePushBlock(World world, int x, int y, int z, int newX, int newY, int newZ)
    {
    	if(world.getBlock(newX, newY, newZ) instanceof BlockPush && (heavy ? true : !((BlockPush)world.getBlock(newX, newY, newZ)).heavy))
    	{
    		BlockPos newPos = new BlockPos(newX, newY, newZ);
	    	for(BlockPos pos2 : new BlockPos[]{newPos.north(), newPos.south(), newPos.east(), newPos.west()})
	    	{
	    		if(world.isAirBlock(pos2.getX(), pos2.getY(), pos2.getZ()))
	    		{
	    			world.setBlock(pos2.getX(), pos2.getY(), pos2.getZ(), world.getBlock(newX, newY, newZ));
	    			world.setBlock(newX, newY, newZ, world.getBlock(x, y, z));
	    	    	world.setBlockToAir(x, y, z);
	    			break;
	    		}
	    	}
		}
    	else if(world.isAirBlock(newX, newY, newZ))
		{
			world.setBlock(newX, newY, newZ, world.getBlock(x, y, z));
	    	world.setBlockToAir(x, y, z);
		}
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    { world.scheduleBlockUpdate(x, y, z, this, 2); }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    { world.scheduleBlockUpdate(x, y, z, this, 2); }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, x, y, z);
        }
    }

    private void checkFallable(World world, int x, int y, int z)
    {
        if (canFallThrough(world, x, y - 1, z) && y >= 0)
        {
            byte b0 = 32;

            if (world.checkChunksExist(x - b0, y - b0, z - b0, x + b0, y + b0, z + b0))
            {
                if (!world.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, x + 0.5F, y + 0.5F, z + 0.5F, this);
                    world.spawnEntityInWorld(entityfallingblock);
                }
            }
            else
            {
                world.setBlockToAir(x, y, z);

                while (canFallThrough(world, x, y - 1, z) && y > 0)
                {
                    --y;
                }

                if (y > 0)
                {
                    world.setBlock(x, y, z, this);
                }
            }
        }
    }
    
    public static boolean canFallThrough(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);

        if (block.isAir(world, x, y, z))
        {
            return true;
        }
        else if (block == Blocks.fire)
        {
            return true;
        }
        else
        {
            Material material = block.getMaterial();
            return material == Material.water ? true : material == Material.lava;
        }
    }
}
