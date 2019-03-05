package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPushSwitch extends Block
{
	boolean heavy;
    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
    
	public BlockPushSwitch(boolean isHeavy)
	{
		super(Material.ROCK);
		String name = isHeavy ? "push_switch_heavy" : "push_switch";
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(DungeonTools.dungeonTab);
		this.setBlockUnbreakable();
		heavy = isHeavy;
	}

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    { updateActivation(world, pos); }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    { updateActivation(world, pos); }
    
    public void updateActivation(World world, BlockPos pos)
    {
    	BlockPos[] posList = {pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()};
    	boolean activated = false;
    	
    	for(BlockPos pos2 : posList)
    	{
    		if((world.getBlockState(pos2).getBlock() instanceof BlockPush ? DungeonConfig.pushSwitchType ? ((BlockPush) world.getBlockState(pos2).getBlock()).heavy == heavy  : true : false)) activated = true;
    	}

		world.setBlockState(pos, getDefaultState().withProperty(ACTIVATED, activated));
    }

	public IBlockState getActivatedState(IBlockState state) {
		return state.withProperty(ACTIVATED, true); }
	@Override
    public boolean canProvidePower(IBlockState state)
    { return true; }
	@Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    { return blockState.getValue(ACTIVATED) ? 15 : 0; }
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	return this.getDefaultState().withProperty(ACTIVATED, meta == 1); }
    @Override
    public int getMetaFromState(IBlockState state) {
    	return state.getValue(ACTIVATED) ? 1 : 0; }
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ACTIVATED}); }
}
