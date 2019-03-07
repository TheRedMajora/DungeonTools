package com.theredmajora.dungeontools.blocks;

import java.util.Random;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonItems;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.IColorType;
import com.theredmajora.dungeontools.extra.IUnlockable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLockedDoor extends Block implements IUnlockable, IColorType
{
	public String type;
	public String lowerTextureName;
    @SideOnly(Side.CLIENT)
    private IIcon[] lowerIcon;
    @SideOnly(Side.CLIENT)
    private IIcon[] upperIcon;

    public BlockLockedDoor(Material mat, String type, String lowTexName)
    {
        super(mat);
		this.setBlockName(type);	 
		this.setBlockTextureName(DungeonTools.ModID + ":" + "door_" + type);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
        float f = 0.5F;
        float f1 = 1.0F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        this.lowerTextureName = lowTexName;
        this.type = type;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.upperIcon[0];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int meta)
    {
        if (meta != 1 && meta != 0)
        {
            int i1 = this.combineMeta(world, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;

            if (flag)
            {
                if (j1 == 0 && meta == 2)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && meta == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && meta == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && meta == 4)
                {
                    flag1 = !flag1;
                }
            }
            else
            {
                if (j1 == 0 && meta == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && meta == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && meta == 4)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && meta == 2)
                {
                    flag1 = !flag1;
                }

                if ((i1 & 16) != 0)
                {
                    flag1 = !flag1;
                }
            }

            return flag2 ? this.lowerIcon[flag1?1:0] : this.upperIcon[flag1?1:0];
        }
		return this.upperIcon[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.lowerIcon = new IIcon[2];
        this.upperIcon = new IIcon[2];
        this.lowerIcon[0] = reg.registerIcon(this.getTextureName() + "_upper");
        this.upperIcon[0] = reg.registerIcon(DungeonTools.ModID + ":door_" + lowerTextureName + "_lower");
        this.lowerIcon[1] = new IconFlipped(this.lowerIcon[0], true, false);
        this.upperIcon[1] = new IconFlipped(this.upperIcon[0], true, false);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z)
    {
        int l = this.combineMeta(world, x, y, z);
        return (l & 4) != 0;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int combinedMeta = this.combineMeta(world, x, y, z);
        
        float f = 0.1875F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        int j = combinedMeta & 3;
        boolean flag = (combinedMeta & 4) != 0;
        boolean flag1 = (combinedMeta & 16) != 0;

        if (j == 0)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
        }
        else if (j == 1)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            }
        }
        else if (j == 2)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
            }
            else
            {
                this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }
        else if (j == 3)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float j, float k, float l)
    {
    	if (!type.equals("great")) return false;
		int i1 = this.combineMeta(world, x, y, z);
		int j1 = i1 & 7;
		j1 ^= 4;

		if ((i1 & 8) == 0)
		{
		    world.setBlockMetadataWithNotify(x, y, z, j1, 2);
		    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
		}
		else
		{
		    world.setBlockMetadataWithNotify(x, y - 1, z, j1, 2);
		    world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
		}

		world.playAuxSFXAtEntity(player, 1003, x, y, z, 0);
		return true;
    }

    public void powerDoor(World world, int x, int y, int z, boolean isPowered)
    {
        int l = this.combineMeta(world, x, y, z);
        boolean flag1 = (l & 4) != 0;

        if (flag1 != isPowered)
        {
        	if(type.equals("locked_redstone"))
        	{
        		if((world.getBlockMetadata(x, y, z) & 8) != 0) this.doUnlock(world, x, y, z);
        		else this.doUnlock(world, x, y + 1, z);
        		
        		return;
        	}
        	
            int i1 = l & 7;
            i1 ^= 4;

            if ((l & 8) == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, i1, 2);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
            else
            {
                world.setBlockMetadataWithNotify(x, y - 1, z, i1, 2);
                world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
            }

            world.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block blockIn)
    {
        int l = world.getBlockMetadata(x, y, z);

        if ((l & 8) == 0)
        {
            boolean flag = false;

            if (world.getBlock(x, y + 1, z) != this)
            {
                world.setBlockToAir(x, y, z);
                flag = true;
            }

            if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
            {
                world.setBlockToAir(x, y, z);
                flag = true;

                if (world.getBlock(x, y + 1, z) == this)
                {
                    world.setBlockToAir(x, y + 1, z);
                }
            }

            if (flag)
            {
                if (!world.isRemote)
                {
                    this.dropBlockAsItem(world, x, y, z, l, 0);
                }
            }
            else if(type.equals("great") || type.equals("locked_redstone"))
            {
                boolean flag1 = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
                if ((flag1 || blockIn.canProvidePower()) && blockIn != this) this.powerDoor(world, x, y, z, flag1);
            }
        }
        else
        {
            if (world.getBlock(x, y - 1, z) != this)
            {
                world.setBlockToAir(x, y, z);
            }

            if (blockIn != this)
            {
                this.onNeighborBlockChange(world, x, y - 1, z, blockIn);
            }
        }
    }

    public Item getItemDropped(int meta, Random rand, int par1)
    {
        return (meta & 8) != 0 ? null : getItem();
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return y >= world.getHeight() - 1 ? false : World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
    }

    public int combineMeta(IBlockAccess world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        boolean flag = (l & 8) != 0;
        int i1;
        int j1;

        if (flag)
        {
            i1 = world.getBlockMetadata(x, y - 1, z);
            j1 = l;
        }
        else
        {
            i1 = l;
            j1 = world.getBlockMetadata(x, y + 1, z);
        }

        boolean flag1 = (j1 & 1) != 0;
        return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return getItem();
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && (meta & 8) != 0 && world.getBlock(x, y - 1, z) == this)
        { world.setBlockToAir(x, y - 1, z); }
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
	public String getType()
	{ return this.type.substring(this.type.lastIndexOf("_") + 1); }

	@Override
	public boolean unlock(World world, EntityPlayer player, int x, int y, int z) {
		if((!type.equals("locked_redstone")) && (world.getBlockMetadata(x, y, z) & 8) != 0)
		{
			this.doUnlock(world, x, y, z);
			return true;
		}
		
		return false;
	}

	public void doUnlock(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, getDoor(), world.getBlockMetadata(x, y, z), 2);
		world.setBlock(x, y - 1, z, getDoor(), world.getBlockMetadata(x, y - 1, z), 2);
	    world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
		
	    if(DungeonConfig.lockedDoorOpen)
	    {
			int i1 = this.combineMeta(world, x, y, z);
			int j1 = i1 & 7;
			j1 ^= 4;

			if ((i1 & 8) == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, j1, 2);
			    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
			else
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, j1, 2);
			    world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
			}
	    }
	}
	
	public Block getDoor()
	{
        switch(type)
        {
        	case "locked_basic":
        		return Blocks.wooden_door;
        	case "locked_redstone":
        		return Blocks.wooden_door;
        	case "locked_red":
        		return Blocks.wooden_door;
        	case "locked_blue":
        		return Blocks.wooden_door;
        	case "locked_green":
        		return Blocks.wooden_door;
        	case "locked_orange":
        		return Blocks.wooden_door;
        	case "locked_yellow":
        		return Blocks.wooden_door;
        	case "locked_purple":
        		return Blocks.wooden_door;
        	case "locked_icy":
        		return Blocks.wooden_door;
        	case "locked_white":
        		return Blocks.iron_door;
        	case "locked_black":
        		return Blocks.iron_door;
        	default:
        		return DungeonItems.door_great;
        }
	}
}