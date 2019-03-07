package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.blocks.BlockPush;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ItemGauntlet extends Item
{	
    public ItemGauntlet()
    {
    	super();
		this.setUnlocalizedName("gauntlet");
		this.setRegistryName("gauntlet");
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.maxStackSize = 1;
    }
    
    @Override
    public boolean doesSneakBypassUse(ItemStack stack, net.minecraft.world.IBlockAccess world, BlockPos pos, EntityPlayer player)
    { return world.getBlockState(pos).getBlock() instanceof BlockPush; }
}