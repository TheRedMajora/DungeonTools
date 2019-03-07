package com.theredmajora.dungeontools.items;

import java.util.List;

import com.theredmajora.dungeontools.DungeonTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemNBTTransfer extends Item
{
    public ItemNBTTransfer()
    {
    	super();
		this.setUnlocalizedName("nbt_transfer");
		this.setTextureName(DungeonTools.ModID + ":" + "nbt_transfer");
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	InventoryPlayer inventory = player.inventory;
    	ItemStack stack1 = inventory.getStackInSlot(0);
    	ItemStack stack2 = inventory.getStackInSlot(8);
    	
    	if(stack1 != null && stack2 != null)
    	{
    		if(stack1.hasTagCompound())
    		{
				NBTTagCompound nbt = stack1.stackTagCompound;

				stack2.setTagCompound(nbt);
		        world.playSoundAtEntity(player, DungeonTools.ModID + ":" + "success", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    		}
    	}
    	
        return stack;
    }

    @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced)
	{
		list.add(StatCollector.translateToLocal(getUnlocalizedName() + ".desc.1"));
		list.add(StatCollector.translateToLocal(getUnlocalizedName() + ".desc.2"));
	}
}