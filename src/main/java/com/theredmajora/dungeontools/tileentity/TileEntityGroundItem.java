package com.theredmajora.dungeontools.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityGroundItem extends TileEntityBase
{
	private ItemStack item = ItemStack.EMPTY;
	private int rotationValue;
	
	@Override
    public NBTTagCompound write(NBTTagCompound compound)
	{
        super.write(compound);

        compound.setInt("rotation", rotationValue);
        
        NBTTagCompound stack2 = new NBTTagCompound();
        this.item.write(stack2);
        compound.setTag("item", stack2);
        
		return compound;
    }

    @Override
    public void read(NBTTagCompound compound)
    {
        super.read(compound);;
        
        this.item = ItemStack.read(compound.getCompound("item"));
        this.rotationValue = compound.getInt("rotation");
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
