package com.theredmajora.dungeontools.extra;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeLantern extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	
	int oilCount;

	@Override
	public boolean matches(InventoryCrafting crafter, World world) {
		oilCount = 0;
		ItemStack lantern = ItemStack.EMPTY;
		ItemStack stack;

		for (int i = 0; i < crafter.getSizeInventory(); i++) {
			 stack = crafter.getStackInSlot(i);
			if(!stack.isEmpty())
			{
				if(stack.getItem() == DungeonItems.lantern_oil)
				{
					oilCount += 1;
				}
				else if(stack.getItem() == DungeonItems.lantern)
				{
					if(!lantern.isEmpty() || !stack.isItemDamaged())
					{ return false; }
					lantern = stack;
				}
			}
		}

		if(!lantern.isEmpty()) return true;
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafter)
	{
		ItemStack is = null;

		for (int a = 0; a < crafter.getSizeInventory(); a++) {
			is = crafter.getStackInSlot(a);
			if (!is.isEmpty() && is.getItem() == DungeonItems.lantern)
				break;
		}

		if (is.isEmpty())
			return ItemStack.EMPTY;
		is = is.copy();
		is.setItemDamage(Math.max(0, is.getItemDamage() - (DungeonConfig.oilEfficiency * oilCount)));
		return is;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack>  remaining = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < remaining.size(); ++i) {
			ItemStack itemstack = inv.getStackInSlot(i);
			remaining.set(i, ForgeHooks.getContainerItem(itemstack));
		}

		return remaining;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(DungeonItems.lantern);
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= 9;
	}
	
	@Override
	public boolean isDynamic() {
		return true;
	}
}