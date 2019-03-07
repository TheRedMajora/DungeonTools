package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKey extends Item
{
	public String type;
	boolean skeleton;
	
	public ItemKey(String type)
	{
		super();
		this.setUnlocalizedName("key_" + type);
		this.setTextureName(DungeonTools.ModID + ":" + "key_" + type);
		this.setCreativeTab(DungeonTools.dungeonTab);
		if(type != "basic") this.setMaxStackSize(1);
		this.type = type;
		this.skeleton = type.equals("skeleton");
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float dx, float dy, float dz)
    {
		Block block = world.getBlock(x, y, z);
		
		if(block instanceof IUnlockable)
    	{
			boolean isType = ((IColorType) block).getType().equals(type);
			
			if(DungeonConfig.greatSkeletonKey ? skeleton || isType : (skeleton && !((IColorType) block).getType().equals("great")) || isType)
			{
				if(((IUnlockable) block).unlock(world, player, x, y, z))
				{
			        world.playSoundAtEntity(player, DungeonTools.ModID + ":" + "unlock.door", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			        if(getBreakFromConfig(type)) stack.stackSize--;
                	return true;
				}
			}
			else world.playSoundAtEntity(player, DungeonTools.ModID + ":" + "unlock.fail", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    	}

    	return false;
    }
	
	private boolean getBreakFromConfig(String type)
	{
        switch(type)
        {
        	case "basic":
        		return DungeonConfig.basicKeyBreak;
        	case "red":
        		return DungeonConfig.redKeyBreak;
        	case "blue":
        		return DungeonConfig.blueKeyBreak;
        	case "green":
        		return DungeonConfig.greenKeyBreak;
        	case "orange":
        		return DungeonConfig.orangeKeyBreak;
        	case "yellow":
        		return DungeonConfig.yellowKeyBreak;
        	case "purple":
        		return DungeonConfig.purpleKeyBreak;
        	case "icy":
        		return DungeonConfig.icyKeyBreak;
        	case "white":
        		return DungeonConfig.whiteKeyBreak;
        	case "black":
        		return DungeonConfig.blackKeyBreak;
        	case "great":
        		return DungeonConfig.greatKeyBreak;
        	default:
        		return DungeonConfig.skeletonKeyBreak;
        }
	}
}
