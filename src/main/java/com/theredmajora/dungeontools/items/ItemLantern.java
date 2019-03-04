package com.theredmajora.dungeontools.items;

import javax.annotation.Nullable;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.blocks.BlockHardWeb;
import com.theredmajora.dungeontools.blocks.BlockUnlitTorch;
import com.theredmajora.dungeontools.blocks.BlockVanish;

import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLantern extends ItemDungeon
{	
    public ItemLantern()
    {
    	super("lantern");
        this.maxStackSize = 1;
        this.setMaxDamage(DungeonConfig.lanternDamage + 1);

        this.addPropertyOverride(new ResourceLocation("broken"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return stack.getItemDamage() == stack.getMaxDamage() - 1 ? 1.0F : 0.0F;
            }
        });
    }
    
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
    	ItemStack stack = player.getHeldItem(hand);
    	
    	if(stack.getItemDamage() != stack.getMaxDamage() - 1)
    	{
    		if(world.getBlockState(pos).getBlock()  instanceof BlockUnlitTorch)
        	{
				world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            	world.setBlockState(pos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, world.getBlockState(pos).getValue(BlockUnlitTorch.FACING)));
                stack.setItemDamage(stack.getItemDamage() + 1);
                return EnumActionResult.SUCCESS;
        	}
        	else if(world.getBlockState(pos).getBlock() instanceof BlockHardWeb && world.getBlockState(pos).getValue(BlockVanish.VANISH).equals(false))
        	{
				world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        		world.setBlockState(pos, ((BlockVanish) world.getBlockState(pos).getBlock()).getVanishedState(world.getBlockState(pos)));
                stack.setItemDamage(Math.min(stack.getMaxDamage() - 1, stack.getItemDamage() + 3));
                return EnumActionResult.SUCCESS;
        	}

            return EnumActionResult.PASS;
    	}


		world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        return EnumActionResult.PASS;
    }

	@Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this, 1));
            items.add(new ItemStack(this, 1, this.getMaxDamage(new ItemStack(this)) - 1));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) 
    {
		if(stack.getItemDamage() == stack.getMaxDamage() - 1) return this.getUnlocalizedName() + "_empty";
		return this.getUnlocalizedName() + "_filled";
    }
}