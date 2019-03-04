package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.tileentity.TileEntityHardWeb;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHardWeb extends BlockContainer
{
	IIcon webIcon;
	IIcon fireIcon;
	
    public BlockHardWeb()
    {
		super(Material.web);
		this.setBlockName("hard_web");	 
		this.setBlockTextureName(DungeonTools.ModID + ":" + "hard_web");
		this.setCreativeTab(DungeonTools.bwelluTab);
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityHardWeb();
	}
    
    public void fireTick(World world, int x, int y, int z)
    {
		if(world.getBlock(x + 1, y, z) instanceof BlockHardWeb && world.getBlockMetadata(x + 1, y, z) == 0)
		{
			world.setBlockMetadataWithNotify(x + 1, y, z, 1, 3);
		}
		if(world.getBlock(x - 1, y, z) instanceof BlockHardWeb && world.getBlockMetadata(x - 1, y, z) == 0)
		{
			world.setBlockMetadataWithNotify(x - 1, y, z, 1, 3);
		}
		if(world.getBlock(x, y + 1, z) instanceof BlockHardWeb && world.getBlockMetadata(x, y + 1, z) == 0)
		{
			world.setBlockMetadataWithNotify(x, y + 1, z, 1, 3);
		}
		if(world.getBlock(x, y - 1, z) instanceof BlockHardWeb && world.getBlockMetadata(x, y - 1, z) == 0)
		{
			world.setBlockMetadataWithNotify(x, y - 1, z, 1, 3);
		}
		if(world.getBlock(x, y, z + 1) instanceof BlockHardWeb && world.getBlockMetadata(x, y, z + 1) == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z + 1, 1, 3);
		}
		if(world.getBlock(x, y, z - 1) instanceof BlockHardWeb && world.getBlockMetadata(x, y, z - 1) == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z - 1, 1, 3);
		}

		world.setBlockToAir(x, y, z);
    }
    
    @Override
	public void registerBlockIcons(IIconRegister reg)
	{
		this.webIcon = reg.registerIcon(DungeonTools.ModID + ":" + "hard_web");
		this.fireIcon = reg.registerIcon(DungeonTools.ModID + ":" + "hard_web_fire");
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
    	if(meta == 0) return webIcon;
    	return fireIcon;
	}
}