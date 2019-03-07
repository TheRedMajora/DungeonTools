package com.theredmajora.dungeontools.blocks;

import java.util.Random;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedDoor extends Block implements IUnlockable, IColorType
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool OPEN = BlockDoor.OPEN;
    public static final PropertyEnum<BlockDoor.EnumHingePosition> HINGE = BlockDoor.HINGE;
    public static final PropertyBool POWERED = BlockDoor.POWERED;
    public static final PropertyEnum<BlockDoor.EnumDoorHalf> HALF = BlockDoor.HALF;
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);

	public String type;
    
    public BlockLockedDoor(Material materialIn, String type)
    {
        super(materialIn);
        this.setCreativeTab(DungeonTools.dungeonTab);
        this.setUnlocalizedName("door_" + type);
        this.setRegistryName("door_" + type);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
        this.type = type;
    }
    
	public String getType()
	{
		return this.type.substring(this.type.lastIndexOf("_") + 1);
	}

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        boolean flag = !((Boolean)state.getValue(OPEN)).booleanValue();
        boolean flag1 = state.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT;

        switch (enumfacing)
        {
            case EAST:
            default:
                return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
            case SOUTH:
                return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
            case WEST:
                return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
            case NORTH:
                return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
        }
    }

    @Override
    public String getLocalizedName()
    {
        return I18n.format((this.getUnlocalizedName() + ".name").replaceAll("tile", "item"));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return isOpen(combineMetadata(worldIn, pos));
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    private int getCloseSound()
    {
        return 1011;
    }

    private int getOpenSound()
    {
        return 1005;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        switch(type)
        {
        	case "locked_basic":
        		return BlockPlanks.EnumType.OAK.getMapColor();
        	case "locked_redstone":
        		return BlockPlanks.EnumType.OAK.getMapColor();
        	case "locked_red":
        		return BlockPlanks.EnumType.BIRCH.getMapColor();
        	case "locked_blue":
        		return BlockPlanks.EnumType.ACACIA.getMapColor();
        	case "locked_green":
        		return BlockPlanks.EnumType.JUNGLE.getMapColor();
        	case "locked_orange":
        		return BlockPlanks.EnumType.SPRUCE.getMapColor();
        	case "locked_yellow":
        		return BlockPlanks.EnumType.SPRUCE.getMapColor();
        	case "locked_purple":
        		return BlockPlanks.EnumType.DARK_OAK.getMapColor();
        	case "locked_icy":
        		return BlockPlanks.EnumType.DARK_OAK.getMapColor();
        	case "locked_white":
        		return MapColor.IRON;
        	case "locked_black":
        		return MapColor.IRON;
        	default:
        		return MapColor.GOLD;
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!type.equals("great")) return false; 
        else
        {
            BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
            IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this) return false;
            else
            {
                state = iblockstate.cycleProperty(OPEN);
                worldIn.setBlockState(blockpos, state, 10);
                worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
                worldIn.playEvent(playerIn, ((Boolean)state.getValue(OPEN)).booleanValue() ? this.getOpenSound() : this.getCloseSound(), pos, 0);
                return true;
            }
        }
    }

    public void toggleDoor(World worldIn, BlockPos pos, boolean open)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            BlockPos blockpos = iblockstate.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
            IBlockState iblockstate1 = pos == blockpos ? iblockstate : worldIn.getBlockState(blockpos);

            if (iblockstate1.getBlock() == this && ((Boolean)iblockstate1.getValue(OPEN)).booleanValue() != open)
            {
                worldIn.setBlockState(blockpos, iblockstate1.withProperty(OPEN, Boolean.valueOf(open)), 10);
                worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
                worldIn.playEvent((EntityPlayer)null, open ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            }
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
            else if (blockIn != this)
            {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
        }
        else
        {
            boolean flag1 = false;
            BlockPos blockpos1 = pos.up();
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
                flag1 = true;
            }

            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn,  pos.down(), EnumFacing.UP))
            {
                worldIn.setBlockToAir(pos);
                flag1 = true;

                if (iblockstate1.getBlock() == this)
                {
                    worldIn.setBlockToAir(blockpos1);
                }
            }

            if (flag1)
            {
                if (!worldIn.isRemote)
                {
                    this.dropBlockAsItem(worldIn, pos, state, 0);
                }
            }
            else if(type.equals("great") || type.equals("locked_redstone"))
            {
            	boolean flag = (worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1));

                if (blockIn != this && (flag || blockIn.getDefaultState().canProvidePower()) && flag != ((Boolean)iblockstate1.getValue(POWERED)).booleanValue())
                {
                    worldIn.setBlockState(blockpos1, iblockstate1.withProperty(POWERED, Boolean.valueOf(flag)), 2);

                    if(type.equals("great") && flag != ((Boolean)state.getValue(OPEN)).booleanValue())
                    {
                        worldIn.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
                        worldIn.markBlockRangeForRenderUpdate(pos, pos);
                        worldIn.playEvent((EntityPlayer)null, flag ? this.getOpenSound() : this.getCloseSound(), pos, 0);
                    }
                    else
                    {
                        worldIn.scheduleUpdate(pos, this, 2);
                    }
                }
            }
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    { this.doUnlock(world, pos.up(), world.getBlockState(pos.up())); }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.getItem();
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        if (pos.getY() >= worldIn.getHeight() - 1)
        {
            return false;
        }
        else
        {
            IBlockState state = worldIn.getBlockState(pos.down());
            return (state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
        }
    }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.DESTROY;
    }

    public static int combineMetadata(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        int i = iblockstate.getBlock().getMetaFromState(iblockstate);
        boolean flag = isTop(i);
        IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
        int j = iblockstate1.getBlock().getMetaFromState(iblockstate1);
        int k = flag ? j : i;
        IBlockState iblockstate2 = worldIn.getBlockState(pos.up());
        int l = iblockstate2.getBlock().getMetaFromState(iblockstate2);
        int i1 = flag ? i : l;
        boolean flag1 = (i1 & 1) != 0;
        boolean flag2 = (i1 & 2) != 0;
        return removeHalfBit(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this.getItem());
    }

    private Item getItem()
    {
        switch(type)
        {
        	case "locked_basic":
        		return DungeonItems.door_locked_basic_item;
        	case "locked_red":
        		return DungeonItems.door_locked_red_item;
        	case "locked_blue":
        		return DungeonItems.door_locked_blue_item;
        	case "locked_green":
        		return DungeonItems.door_locked_green_item;
        	case "locked_orange":
        		return DungeonItems.door_locked_orange_item;
        	case "locked_yellow":
        		return DungeonItems.door_locked_yellow_item;
        	case "locked_purple":
        		return DungeonItems.door_locked_purple_item;
        	case "locked_icy":
        		return DungeonItems.door_locked_icy_item;
        	case "locked_white":
        		return DungeonItems.door_locked_white_item;
        	case "locked_black":
        		return DungeonItems.door_locked_black_item;
        	case "locked_great":
        		return DungeonItems.door_locked_great_item;
        	default:
        		return DungeonItems.door_great_item;
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockPos blockpos = pos.down();
        BlockPos blockpos1 = pos.up();

        if (player.capabilities.isCreativeMode && state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this)
        {
            worldIn.setBlockToAir(blockpos);
        }

        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this)
        {
            if (player.capabilities.isCreativeMode)
            {
                worldIn.setBlockToAir(pos);
            }

            worldIn.setBlockToAir(blockpos1);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.up());

            if (iblockstate.getBlock() == this)
            {
                state = state.withProperty(HINGE, iblockstate.getValue(HINGE)).withProperty(POWERED, iblockstate.getValue(POWERED));
            }
        }
        else
        {
            IBlockState iblockstate1 = worldIn.getBlockState(pos.down());

            if (iblockstate1.getBlock() == this)
            {
                state = state.withProperty(FACING, iblockstate1.getValue(FACING)).withProperty(OPEN, iblockstate1.getValue(OPEN));
            }
        }

        return state;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getValue(HALF) != BlockDoor.EnumDoorHalf.LOWER ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING))).cycleProperty(HINGE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(HINGE, (meta & 1) > 0 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf((meta & 2) > 0)) : this.getDefaultState().withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(FACING, EnumFacing.getHorizontal(meta & 3).rotateYCCW()).withProperty(OPEN, Boolean.valueOf((meta & 4) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            i = i | 8;

            if (state.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT)
            {
                i |= 1;
            }

            if (((Boolean)state.getValue(POWERED)).booleanValue())
            {
                i |= 2;
            }
        }
        else
        {
            i = i | ((EnumFacing)state.getValue(FACING)).rotateY().getHorizontalIndex();

            if (((Boolean)state.getValue(OPEN)).booleanValue())
            {
                i |= 4;
            }
        }

        return i;
    }

    protected static int removeHalfBit(int meta)
    {
        return meta & 7;
    }

    public static boolean isOpen(IBlockAccess worldIn, BlockPos pos)
    {
        return isOpen(combineMetadata(worldIn, pos));
    }

    public static EnumFacing getFacing(IBlockAccess worldIn, BlockPos pos)
    {
        return getFacing(combineMetadata(worldIn, pos));
    }

    public static EnumFacing getFacing(int combinedMeta)
    {
        return EnumFacing.getHorizontal(combinedMeta & 3).rotateYCCW();
    }

    protected static boolean isOpen(int combinedMeta)
    {
        return (combinedMeta & 4) != 0;
    }

    protected static boolean isTop(int meta)
    {
        return (meta & 8) != 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HALF, FACING, OPEN, HINGE, POWERED});
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    public Block setCreativeTab(CreativeTabs tab)
    { return this; }

	public Block getDoor()
	{
        switch(type)
        {
        	case "locked_basic":
        		return Blocks.OAK_DOOR;
        	case "locked_redstone":
        		return Blocks.OAK_DOOR;
        	case "locked_red":
        		return Blocks.BIRCH_DOOR;
        	case "locked_blue":
        		return Blocks.ACACIA_DOOR;
        	case "locked_green":
        		return Blocks.JUNGLE_DOOR;
        	case "locked_orange":
        		return Blocks.SPRUCE_DOOR;
        	case "locked_yellow":
        		return Blocks.SPRUCE_DOOR;
        	case "locked_purple":
        		return Blocks.DARK_OAK_DOOR;
        	case "locked_icy":
        		return Blocks.DARK_OAK_DOOR;
        	case "locked_white":
        		return Blocks.IRON_DOOR;
        	case "locked_black":
        		return Blocks.IRON_DOOR;
        	default:
        		return DungeonItems.door_great;
        }
	}

	@Override
	public boolean unlock(World world, BlockPos pos, IBlockState state)
	{
		if(!type.equals("locked_redstone")) return doUnlock(world, pos, state);
		else return false;
	}

	public boolean doUnlock(World world, BlockPos pos, IBlockState state)
	{
		if(state.getValue(HALF).equals(BlockDoor.EnumDoorHalf.UPPER))
		{
			BlockLockedDoor block = (BlockLockedDoor)state.getBlock();
			IBlockState newState = block.getDoor().getDefaultState()
					.withProperty(FACING, world.getBlockState(pos.down()).getValue(FACING))
					.withProperty(OPEN, DungeonConfig.lockedDoorOpen)
					.withProperty(HINGE, state.getValue(HINGE))
					.withProperty(POWERED, state.getValue(POWERED))
					.withProperty(HALF, BlockDoor.EnumDoorHalf.UPPER);
			world.setBlockState(pos, newState, 2);
            world.setBlockState(pos.down(), newState.cycleProperty(HALF), 2);
            world.markBlockRangeForRenderUpdate(pos.down(), pos);
            
    		return true;
		}
		
		return false;
	}
}