package com.theredmajora.dungeontools.items;
import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonSounds;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemKey extends Item
{
	String type;
	boolean skeleton;
	
	public ItemKey(String type)
	{
		super();
		this.setUnlocalizedName("key_" + type);
		this.setRegistryName("key_" + type);
		this.setCreativeTab(DungeonTools.dungeonTab);
		if(type != "basic") this.setMaxStackSize(1);
		this.type = type;
		this.skeleton = type.equals("skeleton");
	}

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = world.getBlockState(pos);
		
		if(state.getBlock() instanceof IUnlockable)
    	{
			IUnlockable block = (IUnlockable) state.getBlock();
			boolean isType = ((IColorType) block).getType().equals(type);
			
			if(DungeonConfig.greatSkeletonKey ? skeleton || isType : (skeleton && !((IColorType) block).getType().equals("great")) || isType)
			{
				if(block.unlock(world, player, pos, state))
				{
					world.playSound(player, pos, DungeonSounds.lock, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
					
					
					int behavior = DungeonConfig.keyBehavior;
					if(behavior == 0 || (behavior == 1 && !skeleton) || (behavior == 2 && type.equals("basic"))) stack.shrink(1);
					
                	return EnumActionResult.SUCCESS;
				}
			}
			else world.playSound(player, pos, DungeonSounds.fail, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
    	}

    	return EnumActionResult.PASS;
    }
}
