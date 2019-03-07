package com.theredmajora.dungeontools.tileentity;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.blocks.BlockVanish;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class TileEntityVanish extends TileEntityBase
{
	private int existed = 0;
	private boolean vanished = false;

	@Override
    public void writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);

        compound.setInteger("existed", this.existed);
        compound.setBoolean("vanished", this.vanished);
        compound.setInteger("metadata", this.blockMetadata);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        this.existed = compound.getInteger("existed");
        this.vanished = compound.getBoolean("vanished");
        this.blockMetadata = compound.getInteger("metadata");
    }
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{	
			if(blockMetadata != 0)
	        {
				BlockVanish vanish = (BlockVanish)worldObj.getBlock(xCoord, yCoord, zCoord);
				
	        	existed++;
	        	
	        	if(existed > (DungeonConfig.vanishTime + 1) && !vanished)
	        	{
	    			vanish.vanish(worldObj, xCoord, yCoord, zCoord);
	        		vanished = true;
	        	}
	        	if(existed > (DungeonConfig.reappearTime + DungeonConfig.vanishTime))
	        	{
	        		worldObj.setBlock(xCoord, yCoord, zCoord, vanish.getBlock(), 0, 3);
	        	}
	        }
		}
	}

	public void sendUpdate(EntityPlayer player)
	{
		player.addChatMessage(new ChatComponentText((worldObj.isRemote ? "Client: " : "Server: ") + "Metadata: " + blockMetadata + " | Vanished: " + vanished + " | Existed: " + existed));
	}
}
