package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebug extends Item
{	
    public ItemDebug()
    {
    	super();
		this.setUnlocalizedName("debug");
		this.setRegistryName("debug");
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.maxStackSize = 1;
    }
    
	@SuppressWarnings("unused")
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
    	ItemStack stack = player.getHeldItem(hand);
    	IBlockState state = world.getBlockState(pos);

    	world.setBlockState(pos.up(), DungeonItems.push_block.getDefaultState(), 11);
    	world.setBlockState(pos.up().up(), DungeonItems.push_block_heavy.getDefaultState(), 11);
			
        return EnumActionResult.SUCCESS;
    }
}