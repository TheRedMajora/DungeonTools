package com.theredmajora.dungeontools.extra;

import net.minecraft.nbt.NBTTagCompound;

public class BlockPos
{
	int x, y, z;
	public BlockPos(int xCoord, int yCoord, int zCoord) { x = xCoord; y = yCoord; z = zCoord; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return z; }
	public BlockPos north() { return new BlockPos(x, y, z - 1);}
	public BlockPos south() { return new BlockPos(x, y, z + 1); }
	public BlockPos east() { return new BlockPos(x + 1, y, z); }
	public BlockPos west() { return new BlockPos(x - 1, y, z); }
	public BlockPos up() { return new BlockPos(x, y + 1, z); }
	public BlockPos down() { return new BlockPos(x, y - 1, z); }
	
    public static BlockPos getPosFromTag(NBTTagCompound tag)
    {
        return new BlockPos(tag.getInteger("X"), tag.getInteger("Y"), tag.getInteger("Z"));
    }

    public static NBTTagCompound createPosTag(BlockPos pos)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setInteger("X", pos.getX());
        nbttagcompound.setInteger("Y", pos.getY());
        nbttagcompound.setInteger("Z", pos.getZ());
        return nbttagcompound;
    }
}
