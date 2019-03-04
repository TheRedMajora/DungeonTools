package com.theredmajora.dungeontools.tileentity;

import com.theredmajora.dungeontools.blocks.BlockHardWeb;

public class TileEntityHardWeb extends TileEntityBase
{
	private int existed;
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
        {
            if (blockMetadata != 0)
            {
            	existed++;
            	
            	if(existed > 4)
            	{
        			BlockHardWeb bhw = (BlockHardWeb)worldObj.getBlock(xCoord, yCoord, zCoord);
        			bhw.fireTick(worldObj, xCoord, yCoord, zCoord);
                    worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.fizz", 0.3F, 0.5F);
            	}
            }
        }
	}
}
