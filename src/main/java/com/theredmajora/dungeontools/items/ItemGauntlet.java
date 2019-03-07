package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.blocks.BlockPush;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemGauntlet extends Item
{
	public ItemGauntlet()
	{
		super();
		this.setUnlocalizedName("gauntlet");
		this.setTextureName(DungeonTools.ModID + ":" + "gauntlet");
		this.setCreativeTab(DungeonTools.dungeonTab);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player)
	{ return world.getBlock(x, y, z) instanceof BlockPush; }
	
}
