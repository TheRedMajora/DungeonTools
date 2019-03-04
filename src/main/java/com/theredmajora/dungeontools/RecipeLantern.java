package com.theredmajora.dungeontools;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeLantern implements IRecipe {
	
	int lanternDamage;
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting crafter, World world) {
		ItemStack lantern = null;
		ItemStack oil = null;
		for (int i = 0; i < crafter.getSizeInventory(); ++i) {
			ItemStack stack = crafter.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() == DungeonItems.lantern_oil) {
					if(oil != null)
						return false;
					oil = stack;
				} else if(stack.getItem() == DungeonItems.lantern) {
					if(lantern != null || !stack.isItemDamaged()) {
						return false;
					}
					lantern = stack;
				} else {
					return false;
				}
			}
		}
		
		if(oil != null && lantern != null)
		{
			lanternDamage = lantern.getItemDamage();
			
			return true;
		}
		
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
		is.setItemDamage(Math.max(0, is.getItemDamage() - 8));
		return is;
	}

	public int getRecipeSize() {
		return 9;
	}

	public ItemStack getRecipeOutput() {
		return null;
	}
}