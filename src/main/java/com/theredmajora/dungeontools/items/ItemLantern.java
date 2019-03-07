package com.theredmajora.dungeontools.items;

import java.util.List;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemLantern extends Item
{
	private IIcon emptyIcon;
	private IIcon filledIcon;
	
    public ItemLantern()
    {
    	super();
        this.maxStackSize = 1;
		this.setUnlocalizedName("lantern");
		this.setTextureName(DungeonTools.ModID + ":" + "lantern");
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.setMaxDamage(DungeonConfig.lanternDamage + 1);
    }

    @Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
    	if(stack.getItemDamage() != stack.getMaxDamage() - 1)
    	{
			entity.setFire(2);
			entity.attackEntityFrom(DamageSource.onFire, 1.5F);
			stack.setItemDamage(Math.min(stack.getMaxDamage() - 1, stack.getItemDamage() + 2));
    	}
    	
		return super.onLeftClickEntity(stack, player, entity);
	}
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float dx, float dy, float dz)
    {
    	if(stack.getItemDamage() == stack.getMaxDamage() - 1) return false;
    	
    	if(world.getBlock(x, y, z) == DungeonItems.unlit_torch)
    	{
        	world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        	world.setBlock(x, y, z, Blocks.torch, world.getBlockMetadata(x, y, z), 3);
            stack.setItemDamage(stack.getItemDamage() + 1);
    		return true;
    	}
    	else if(world.getBlock(x, y, z) == DungeonItems.hard_web && world.getBlockMetadata(x, y, z) > 0)
    	{
        	world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        	world.setBlock(x, y, z, DungeonItems.hard_web, 1, 3);
            stack.setItemDamage(Math.min(stack.getMaxDamage() - 1, stack.getItemDamage() + 3));
            return true;
    	}

        if(stack.getItemDamage() == stack.getMaxDamage() - 1) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.fizz", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        return false;
    }
    

	@Override
	public void registerIcons(IIconRegister reg)
	{
        this.emptyIcon = reg.registerIcon(DungeonTools.ModID + ":lantern_empty");
        this.filledIcon = reg.registerIcon(DungeonTools.ModID + ":lantern_filled");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if(meta == getMaxDamage() - 1) return emptyIcon;
		return filledIcon;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1));
		list.add(new ItemStack(item, 1, item.getMaxDamage() - 1));
	}

    @Override
    public String getUnlocalizedName(ItemStack stack) 
    {

		if(stack.getItemDamage() == stack.getMaxDamage() - 1) return this.getUnlocalizedName() + "_empty";
		return this.getUnlocalizedName() + "_filled";
    }
}