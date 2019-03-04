package com.theredmajora.dungeontools.items;

import java.util.List;

import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemLantern extends ItemDungeon
{
	private IIcon emptyIcon;
	private IIcon filledIcon;
	
    public ItemLantern()
    {
    	super("lantern");
        this.maxStackSize = 1;
        this.setMaxDamage(40);
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float dx, float dy, float dz)
    {
    	if(stack.getItemDamage() == 39) return false;
    	
    	if(world.getBlock(x, y, z) == DungeonItems.unlit_torch)
    	{
        	world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        	world.setBlock(x, y, z, Blocks.torch, world.getBlockMetadata(x, y, z), 3);
            stack.setItemDamage(stack.getItemDamage() + 1);
    		return true;
    	}
    	else if(world.getBlock(x, y, z) == DungeonItems.hard_web && world.getBlockMetadata(x, y, z) == 0)
    	{
        	world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		world.setBlockMetadataWithNotify(x, y, z, 1, 3);
            stack.setItemDamage(Math.min(39, stack.getItemDamage() + 3));
            return true;
    	}

        if(stack.getItemDamage() == 39) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.fizz", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
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
		if(meta == 39) return emptyIcon;
		return filledIcon;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1));
		list.add(new ItemStack(item, 1, 39));
	}

    @Override
    public String getUnlocalizedName(ItemStack stack) 
    {

		if(stack.getItemDamage() == 39) return this.getUnlocalizedName() + "_empty";
		return this.getUnlocalizedName() + "_filled";
    }
}