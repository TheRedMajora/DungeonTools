package com.theredmajora.dungeontools.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityGroundItem extends TileEntityBase
{
	private ItemStack item;
	private int rotationValue;
	
	@Override
    public void writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);

        compound.setInteger("rotation", rotationValue);
        
        NBTTagCompound stack2 = new NBTTagCompound();
        this.item.writeToNBT(stack2);
        compound.setTag("item", stack2);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        this.item = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("item"));
        this.rotationValue = compound.getInteger("rotation");
    }
    
    public Item getWantedItem()
    {
    	return Items.emerald;
    }
    
    public void onCreated()
	{
		item = new ItemStack(Items.slime_ball);
	}
    
    public void setItemStack(ItemStack stack)
    {
    	item = stack;
    }

    public ItemStack getItemStackForRender()
	{
		return item;
	}
    
	public ItemStack getItemStack()
	{
		return item.copy();
	}
	
	public void addRotation()
	{
		rotationValue += 1;
		if(rotationValue == 18) rotationValue = 0;
	}

	public int getRotation()
	{
		return ((rotationValue * 2) * 10) + 1;
	}
}
