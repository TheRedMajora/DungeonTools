package com.theredmajora.dungeontools.tileentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public class TileEntityPushBlock extends TileEntityBase
{
	BlockPos returnPos = new BlockPos(0, 0, 0);
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);

		compound.setTag("returnCoords", NBTUtil.createPosTag(getReturnPos()));
        
		return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        this.returnPos = NBTUtil.getPosFromTag(compound.getCompoundTag("returnCoords"));
    }

    public BlockPos getReturnPos()
    { return returnPos; }

    public void setReturnPos(BlockPos pos)
    {
    	returnPos = pos;
    }

    public boolean isValidPos(EntityPlayer player)
    {
    	return getReturnPos().getX() == getPos().getX() && getReturnPos().getY() == getPos().getY() && getReturnPos().getZ() == getPos().getZ() ? false : (returnPos.getX() == 0 && returnPos.getY() == 0 && returnPos.getZ() == 0 ? false : true); 
    }
}
