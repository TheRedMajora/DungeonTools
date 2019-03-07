package com.theredmajora.dungeontools.blocks;

import java.util.Random;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.EntityFallingPushBlock;
import com.theredmajora.dungeontools.tileentity.TileEntityPushBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPush extends Block
{
	boolean heavy;
    
	public BlockPush(boolean isHeavy)
	{
		super(Material.ROCK);
		String name = isHeavy ? "push_block_heavy" : "push_block";
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
		
		heavy = isHeavy;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    { return Items.AIR; }
	
	@Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		if(worldIn.getTileEntity(pos) != null && ((TileEntityPushBlock) worldIn.getTileEntity(pos)).hasPosition()) spawnAsEntity(worldIn, pos, this.getItem(worldIn, pos, state));
		
        super.breakBlock(worldIn, pos, state);
    }
	
	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this), 1);
        NBTTagCompound nbttagcompound = ((TileEntityPushBlock)worldIn.getTileEntity(pos)).writeToNBT(new NBTTagCompound());
        if (!nbttagcompound.hasNoTags())
        { itemstack.setTagInfo("BlockEntityTag", nbttagcompound); }

        return itemstack;
    }
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		TileEntityPushBlock te = (TileEntityPushBlock) world.getTileEntity(pos);
		if(placer.isSneaking())
		{
			te.setReturnPos(pos);
			if(!world.isRemote) placer.sendMessage(new TextComponentTranslation("tile.push_block.location"));
		}
	}

	@Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
    {
    	if(heavy && !player.getHeldItemMainhand().getItem().equals(DungeonItems.gauntlet)) return;
		TileEntityPushBlock te = (TileEntityPushBlock) world.getTileEntity(pos);
		if(te.isValidPos()) updatePushBlock(world, pos, te.getReturnPos());
    	
        world.scheduleUpdate(pos, this, 2);
    }
	
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(heavy && !player.getHeldItem(hand).getItem().equals(DungeonItems.gauntlet)) return false;
    	
    	TileEntityPushBlock te = (TileEntityPushBlock) world.getTileEntity(pos);
		
    	if(player.isSneaking())
    	{
    		if(player.capabilities.isCreativeMode)
			{
    			te.setReturnPos(pos);
				if(!world.isRemote) player.sendMessage(new TextComponentTranslation("tile.push_block.location"));
			}
    		else if(DungeonConfig.pullPushBlock)
    		{
        		switch(facing)
            	{
        			case EAST:
        				updatePushBlock(world, pos, pos.east());
        				break;
        			case NORTH:
        				updatePushBlock(world, pos, pos.north());
        				break;
        			case SOUTH:
        				updatePushBlock(world, pos, pos.south());
        				break;
        			case WEST:
        				updatePushBlock(world, pos, pos.west());
        				break;
        			default:
        				if(te.isValidPos()) updatePushBlock(world, pos, te.getReturnPos());
        				break;
            	}
    		}
    	}
    	else
    	{
        	switch(facing)
        	{
    			case EAST:
    				updatePushBlock(world, pos, pos.west());
    				break;
    			case NORTH:
    				updatePushBlock(world, pos, pos.south());
    				break;
    			case SOUTH:
    				updatePushBlock(world, pos, pos.north());
    				break;
    			case WEST:
    				updatePushBlock(world, pos, pos.east());
    				break;
    			default:
    				break;
        	}
    	}
    	
        world.scheduleUpdate(pos, this, 2);
    	return true;
    }
    
    public void updatePushBlock(World world, BlockPos pos, BlockPos newPos)
    {
    	if(world.getBlockState(newPos).getBlock() instanceof BlockPush && (heavy ? true : !((BlockPush)world.getBlockState(newPos).getBlock()).heavy))
    	{	
	    	for(BlockPos pos2 : new BlockPos[]{newPos.north(), newPos.south(), newPos.east(), newPos.west()})
	    	{
	    		if(world.isAirBlock(pos2))
	    		{
	    			world.setBlockState(pos2, world.getBlockState(newPos));
	    			((TileEntityPushBlock) world.getTileEntity(pos2)).setReturnPos(((TileEntityPushBlock) world.getTileEntity(newPos)).getReturnPos());
	    			world.setBlockState(newPos, world.getBlockState(pos));
	    			((TileEntityPushBlock) world.getTileEntity(newPos)).setReturnPos(((TileEntityPushBlock) world.getTileEntity(pos)).getReturnPos());
	    			world.removeTileEntity(pos);
	    			world.setBlockToAir(pos);
	    			break;
	    		}
	    	}
		}
    	else if(world.isAirBlock(newPos))
		{
			world.setBlockState(newPos, world.getBlockState(pos));
			((TileEntityPushBlock) world.getTileEntity(newPos)).setReturnPos(((TileEntityPushBlock) world.getTileEntity(pos)).getReturnPos());
			world.removeTileEntity(pos);
			world.setBlockToAir(pos);
		}
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, 2);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        worldIn.scheduleUpdate(pos, this, 2);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
        }
    }

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
        {
            if (worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFallingPushBlock entityfallingblock = new EntityFallingPushBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, heavy);
                    
                    worldIn.spawnEntity(entityfallingblock);
                }
            }
            else
            {
                IBlockState state = worldIn.getBlockState(pos);
    			worldIn.removeTileEntity(pos);
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
                {
                    ;
                }

                if (blockpos.getY() > 0)
                {
                    worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
                }
            }
        }
    }

    public static boolean canFallThrough(IBlockState state)
    {
        Block block = state.getBlock();
        Material material = state.getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
    }
    
    public void onEndFalling(World world, BlockPos pos, Block block, IBlockState state)
    {
    	
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(16) == 0)
        {
            BlockPos blockpos = pos.down();

            if (canFallThrough(worldIn.getBlockState(blockpos)))
            {
                double d0 = (double)((float)pos.getX() + rand.nextFloat());
                double d1 = (double)pos.getY() - 0.05D;
                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
                worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(stateIn));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        return -16777216;
    }

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPushBlock();
	}
	
	@Override
    public boolean hasTileEntity(IBlockState state)
    { return true; }
}
