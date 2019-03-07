package com.theredmajora.dungeontools.extra;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeLantern implements IRecipe {

	int oilCount;
	
	int lanternDamage;
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	/**@Override
	public boolean matches(InventoryCrafting crafter, World world) {
		oilCount = 0;
		ItemStack lantern = null;
		ItemStack stack;
		
		for (int i = 0; i < crafter.getSizeInventory(); ++i) {
			stack = crafter.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() == DungeonItems.lantern_oil) {
					oilCount += 1;
				} else if(stack.getItem() == DungeonItems.lantern) {
					if(lantern != null || !stack.isItemDamaged()) {
						return false;
					}
					lantern = stack;
				}
			}
		}
		
		if(lantern != null)
		{
			lanternDamage = lantern.getItemDamage();
			return true;
		}
		
		return false;
	}*/
	
	@Override
	public boolean matches(InventoryCrafting crafter, World world) {
		oilCount = 0;
		ItemStack lantern = null;
		ItemStack stack;

		for (int i = 0; i < crafter.getSizeInventory(); i++) {
			 stack = crafter.getStackInSlot(i);
			if(stack != null)
			{
				if(stack.getItem() == DungeonItems.lantern_oil)
				{
					oilCount += 1;
				}
				else if(stack.getItem() == DungeonItems.lantern)
				{
					if(lantern != null || !stack.isItemDamaged())
					{ return false; }
					lantern = stack;
				}
			}
		}

		if(lantern != null) return true;
		return false;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(InventoryCrafting crafter)
	{
		ItemStack is = null;

		for (int a = 0; a < crafter.getSizeInventory(); a++) {
			is = crafter.getStackInSlot(a);
			if (is != null && is.getItem() == DungeonItems.lantern)
				break;
		}

		if (is == null)
			return null;
		is = is.copy();
		is.setItemDamage(Math.max(0, is.getItemDamage() - (DungeonConfig.oilEfficiency * oilCount)));
		return is;
	}

	public int getRecipeSize() {
		return 9;
	}

	public ItemStack getRecipeOutput() {
		return null;
	}
}