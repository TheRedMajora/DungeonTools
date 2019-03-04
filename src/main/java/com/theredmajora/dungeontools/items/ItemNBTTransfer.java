package com.theredmajora.dungeontools.items;

import java.util.List;

import com.theredmajora.dungeontools.DungeonSounds;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNBTTransfer extends ItemDungeon
{
    public ItemNBTTransfer()
    {
    	super("nbt_transfer");
        this.setMaxStackSize(1);
    }

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
    	InventoryPlayer inventory = player.inventory;
    	ItemStack stack1 = inventory.getStackInSlot(0);
    	ItemStack stack2 = inventory.getStackInSlot(8);
    	
    	if(stack1 != ItemStack.EMPTY && stack2 != ItemStack.EMPTY)
    	{
    		if(stack1.hasTagCompound())
    		{
				NBTTagCompound nbt = stack1.getTagCompound();

				stack2.setTagCompound(nbt);
		        world.playSound(player, pos, DungeonSounds.success, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		        return EnumActionResult.SUCCESS;
    		}
    	}
    	
        return EnumActionResult.PASS;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flag)
    {
		list.add(I18n.format(getUnlocalizedName() + ".desc"));
	}
}