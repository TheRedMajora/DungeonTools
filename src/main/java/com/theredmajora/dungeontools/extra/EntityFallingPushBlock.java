package com.theredmajora.dungeontools.extra;

import com.theredmajora.dungeontools.blocks.BlockPush;

import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;

public class EntityFallingPushBlock extends EntityFallingBlock
{
	public EntityFallingPushBlock(World worldIn) {
		super(worldIn);
	}

	public EntityFallingPushBlock(World worldIn, double d, double y, double e, boolean heavy)
	{
		super(worldIn, d, y, e, (new BlockPush(heavy)).getDefaultState());
	}
}