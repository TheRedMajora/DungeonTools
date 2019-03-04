package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.blocks.BlockLockedDoor;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemDebug extends ItemDungeon
{	
    public ItemDebug()
    {
    	super("debug");
        this.maxStackSize = 1;
    }
    
	@SuppressWarnings("unused")
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
    	ItemStack stack = player.getHeldItem(hand);
    	IBlockState state = world.getBlockState(pos);
    	
    	if(state.getBlock() instanceof BlockLockedDoor)
    	{
			BlockLockedDoor block = (BlockLockedDoor) state.getBlock();

			state.neighborChanged(world, pos, block, pos);
			
			if(!world.isRemote)
			{
				player.sendMessage(new TextComponentTranslation("Locked Door FACING: " + state.getValue(BlockLockedDoor.FACING)));
				player.sendMessage(new TextComponentTranslation("Locked Door HALF: " + state.getValue(BlockLockedDoor.HALF)));
				player.sendMessage(new TextComponentTranslation("Locked Door HINGE: " + state.getValue(BlockLockedDoor.HINGE)));
				player.sendMessage(new TextComponentTranslation("Locked Door OPEN: " + state.getValue(BlockLockedDoor.OPEN)));
				player.sendMessage(new TextComponentTranslation("Locked Door POWERED: " + state.getValue(BlockLockedDoor.POWERED)));
				
				player.sendMessage(new TextComponentTranslation("Door FACING: " + state.getValue(BlockDoor.FACING)));
				player.sendMessage(new TextComponentTranslation("Door HALF: " + state.getValue(BlockDoor.HALF)));
				player.sendMessage(new TextComponentTranslation("Door HINGE: " + state.getValue(BlockDoor.HINGE)));
				player.sendMessage(new TextComponentTranslation("Door OPEN: " + state.getValue(BlockDoor.OPEN)));
				player.sendMessage(new TextComponentTranslation("Door POWERED: " + state.getValue(BlockDoor.POWERED)));
			}
			
			
            return EnumActionResult.SUCCESS;
    	}
    	
        return EnumActionResult.PASS;
    }
}