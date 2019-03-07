package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.blocks.BlockVanish;
import com.theredmajora.dungeontools.tileentity.TileEntityVanish;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDebug extends Item
{
	public ItemDebug()
	{
		super();
		this.setUnlocalizedName("debug");
		this.setTextureName(DungeonTools.ModID + ":" + "debug");
		this.setCreativeTab(DungeonTools.dungeonTab);
		this.setMaxStackSize(1);
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float dx, float dy, float dz)
    {
		if(world.getBlock(x, y, z) instanceof BlockVanish)
		{
			((TileEntityVanish)world.getTileEntity(x, y, z)).sendUpdate(player);
		}

    	return false;
    }
}
