package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockGroundItem extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon blockIcon2;
    
	public BlockGroundItem()
	{
		super(Material.glass);
		this.setBlockName("ground_item_block");
        this.setBlockTextureName(DungeonTools.ModID + ":ground_item_block");
		this.setCreativeTab(DungeonTools.bwelluTab);
		this.setBlockUnbreakable();
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.15F, 0.9F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityGroundItem();
	}

    @Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
    	TileEntityGroundItem te = (TileEntityGroundItem) world.getTileEntity(x, y, z);
    	te.onCreated();
    	
    	world.setBlockMetadataWithNotify(x, y, z, 2, 3);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k){
    	return null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
    	if(metadata > 0)
    		return blockIcon;
        return this.blockIcon2; 
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister){
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
        this.blockIcon2 = par1IconRegister.registerIcon(getTextureName() + "_visible");
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float j, float k, float l)
    {
		ItemStack stack = player.getCurrentEquippedItem();
		
		TileEntityGroundItem te = (TileEntityGroundItem) world.getTileEntity(x, y, z);

		if(player.capabilities.isCreativeMode)
		{
			if(stack != null)
	    	{
	    		if(stack.getItem() == Items.clay_ball)
	    		{
	    			te.addRotation();
	    		}
	    		else if(stack.getItem() instanceof ItemBlock)
	    		{
	    			if(!world.isRemote) player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("tile.ground_item_block.blocks")));
	    		}
	    		else
	    		{
	    			te.setItemStack(stack);
	    		}
	    	}
	    	else
	    	{
	    		if(!world.isRemote) player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("tile.ground_item_block.items")));
	    	}
		}
		else
		{
			EntityItem item = new EntityItem(world, x, y + 1, z, te.getItemStack());
			if(!world.isRemote) world.spawnEntityInWorld(item);
			world.setBlockToAir(x, y, z);
		}
    	
    	return true;
    }
    
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
