package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.BlockPos;
import com.theredmajora.dungeontools.tileentity.TileEntityVanish;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVanish extends Block
{
    @SideOnly(Side.CLIENT)
	IIcon vanishIcon;
    
    private boolean setToAir;
	
	public BlockVanish(Material mat, String name, boolean setToAir)
	{
		super(mat);
		this.setBlockName(name);	 
		this.setBlockTextureName(DungeonTools.ModID + ":" + name);
		this.setCreativeTab(DungeonTools.dungeonTab);
		this.setToAir = setToAir;
		this.useNeighborBrightness = true;
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
	}

	@Override
    public boolean isOpaqueCube()
    { return false; }
	
	@Override
    public TileEntity createTileEntity(World world, int metadata)
    { return new TileEntityVanish(); }
	
	@Override
    public boolean hasTileEntity(int meta)
    { return true; }
    
    public void vanish(World world, int x, int y, int z)
    {
    	Block block;
    	BlockPos pos = new BlockPos(x, y, z);
    	BlockPos[] posList = {pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()};
    	
    	for(BlockPos pos2 : posList)
    	{
    		block = world.getBlock(pos2.getX(), pos2.getY(), pos2.getZ());
    		
    		if(block instanceof BlockVanish && world.getBlockMetadata(pos2.getX(), pos2.getY(), pos2.getZ()) == 0 && getVanishFlag(block))
    			world.setBlockMetadataWithNotify(pos2.getX(), pos2.getY(), pos2.getZ(), 1, 3);
    	}
    	
    	if(setToAir) world.setBlockToAir(x, y, z);
    }

    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
    {
    	return true;
    }
    
    public Block getBlock()
    { return this; }
    
    public boolean getVanishFlag(Block block)
    { return true; }

    @Override
	public void registerBlockIcons(IIconRegister reg)
	{
		this.vanishIcon = reg.registerIcon(DungeonTools.ModID + ":" + "vanished_block");
    	super.registerBlockIcons(reg);
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
    
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(meta > 0) return vanishIcon;
    	return blockIcon;
	}
	
	@Override
    public boolean canCollideCheck(int meta, boolean boatClick)
    {
		if(meta > 0) return false;
    	return true;
    }
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
		if(world.getBlockMetadata(x, y, z) != 0) return null;
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
}
