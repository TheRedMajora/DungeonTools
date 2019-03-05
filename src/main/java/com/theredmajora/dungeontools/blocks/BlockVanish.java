package com.theredmajora.dungeontools.blocks;

import javax.annotation.Nullable;

import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.tileentity.TileEntityVanish;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVanish extends Block implements ITileEntityProvider
{
    public static final AxisAlignedBB VANISHED_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public static final PropertyBool VANISH = PropertyBool.create("vanish");
	private boolean setToAir;
	
	public BlockVanish(Material mat, String name, boolean setToAir)
	{
		super(mat);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(DungeonTools.dungeonTab);
		this.setToAir = setToAir;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityVanish();
	}
    
    public void vanish(World world, BlockPos pos)
    {
    	IBlockState state;
    	BlockPos[] posList = {pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()};
    	
    	for(BlockPos pos2 : posList)
    	{
    		state = world.getBlockState(pos2);
    		
    		if(state.getBlock() instanceof BlockVanish && !state.getValue(BlockVanish.VANISH) && getVanishFlag(state))
    		{
    			world.setBlockState(pos2, ((BlockVanish) state.getBlock()).getVanishedState(state));
    		}
    	}
    	
    	if(setToAir) world.setBlockToAir(pos);
    }
    
    public boolean getVanishFlag(IBlockState state)
    { return true; }
    
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
    	if(state.getValue(VANISH)) return VANISHED_AABB;
    	return FULL_BLOCK_AABB;
    }

	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
    	if(state.getValue(VANISH)) return NULL_AABB;
    	return FULL_BLOCK_AABB;
    }
    
	public IBlockState getVanishedState(IBlockState state) {
		return state.withProperty(VANISH, true); }
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	return this.getDefaultState().withProperty(VANISH, meta == 1); }
    @Override
    public int getMetaFromState(IBlockState state) {
    	return state.getValue(VANISH) ? 1 : 0; }
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VANISH}); }
}
