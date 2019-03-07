package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockVanishLock extends BlockVanish implements IUnlockable, IColorType
{
	public String type;
	
	public BlockVanishLock(String type)
	{
		super(Material.iron, "vanish_lock_" + type, false);
		this.type = type;
	}
	
	public String getType()
	{ return type; }

    @Override
    public boolean getVanishFlag(Block block)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? block instanceof BlockLock || block instanceof BlockChains : false;
        return (block instanceof BlockVanishLock || block instanceof BlockVanishChains || flag) && ((IColorType)block).getType().equals(this.getType());
    }

	@Override
	public boolean unlock(World world, EntityPlayer player, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		return true;
	}
}