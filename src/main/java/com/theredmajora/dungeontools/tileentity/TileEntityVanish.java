package com.theredmajora.dungeontools.tileentity;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.blocks.BlockVanish;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityVanish extends TileEntityBase implements ITickable
{
	private int existed;
	private boolean vanished;

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);

        compound.setInteger("existed", this.existed);
        compound.setBoolean("vanished", this.vanished);
        
		return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        this.existed = compound.getInteger("existed");
        this.vanished = compound.getBoolean("vanished");
    }
	
	@Override
	public void update()
	{
		if (!world.isRemote)
        {
			IBlockState state = world.getBlockState(pos);
			
    		if(state == ((BlockVanish) state.getBlock()).getVanishedState(state))
            {
            	existed++;
            	
            	if(existed > DungeonConfig.vanishTime && !vanished)
            	{
            		((BlockVanish) state.getBlock()).vanish(world, pos);
            		vanished = true;
            	}
            	if(existed > (DungeonConfig.reappearTime + DungeonConfig.vanishTime))
            	{
            		world.setBlockState(pos, state.withProperty(BlockVanish.VANISH, false));
            		vanished = false;
            	}
            }
        }
	}
}
