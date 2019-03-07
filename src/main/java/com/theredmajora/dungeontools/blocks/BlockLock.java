package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockLock extends BlockVanish implements IUnlockable, IColorType
{
	public String type;
	
	public BlockLock(String type)
	{
		super(Material.iron, "lock_" + type, true);
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}

    @Override
    public boolean getVanishFlag(Block block)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? block instanceof BlockVanishLock || block instanceof BlockVanishChains : false;
        return (block instanceof BlockLock || block instanceof BlockChains || flag) && ((IColorType)block).getType().equals(this.getType());
    }
	
	@Override
	public boolean unlock(World world, EntityPlayer player, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		return true;
	}
}