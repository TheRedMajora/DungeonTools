package com.theredmajora.dungeontools.blocks;

import javax.annotation.Nullable;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGroundItem extends Block
{
	public BlockGroundItem()
	{
		super(Material.GLASS);
		this.setCreativeTab(DungeonTools.dungeonTab);
		this.setUnlocalizedName("ground_item_block");
		this.setRegistryName("ground_item_block");
		this.setBlockUnbreakable();
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityGroundItem();
	}
	
	@Override
    public boolean hasTileEntity(IBlockState state)
    { return true; }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return new AxisAlignedBB(0.1F, 0.0F, 0.1F, 0.9F, 0.15F, 0.9F);
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack stack = player.getHeldItem(hand);
		TileEntityGroundItem te = (TileEntityGroundItem) world.getTileEntity(pos);

		if(player.capabilities.isCreativeMode)
		{
			if(!stack.equals(ItemStack.EMPTY))
	    	{
	    		if(stack.getItem().equals(Items.CLAY_BALL))
	    		{
	    			te.addRotation();
	    		}
	    		else if(stack.getItem() instanceof ItemBlock)
	    		{
	    			if(!world.isRemote) player.sendMessage(new TextComponentTranslation("tile.ground_item_block.blocks"));
	    		}
	    		else
	    		{
	    			te.setItemStack(stack);
	    		}
	    	}
	    	else
	    	{
	    		if(!world.isRemote) player.sendMessage(new TextComponentTranslation("tile.ground_item_block.items"));
	    	}
		}
		else
		{
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), te.getItemStack());
			if(!world.isRemote) world.spawnEntity(item);
			world.setBlockToAir(pos);
		}
		
    	return true;
    }
}
