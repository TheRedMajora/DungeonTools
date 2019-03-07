package com.theredmajora.dungeontools.blocks;

import java.util.Random;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.DungeonTools;
import com.theredmajora.dungeontools.extra.BlockPos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPushSwitch extends Block
{
	@SideOnly(Side.CLIENT)
	IIcon activatedIcon;
	
	boolean heavy;
    
	public BlockPushSwitch(boolean isHeavy)
	{
		super(Material.rock);
		String name = isHeavy ? "push_switch_heavy" : "push_switch";
		this.setBlockName(name);	 
		this.setBlockTextureName(DungeonTools.ModID + ":" + name);
		this.setCreativeTab(DungeonTools.dungeonTab);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
		heavy = isHeavy;
	}

	@Override
    public Item getItemDropped(int par1, Random rand, int par2)
    { return Item.getItemFromBlock(Blocks.iron_block); }
	
	@Override
    public void onBlockAdded(World world, int x, int y, int z)
    { updateActivation(world, x, y, z); }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    { updateActivation(world, x, y, z); }
    
    public void updateActivation(World world, int x, int y, int z)
    {
    	BlockPos pos = new BlockPos(x, y, z);
    	BlockPos[] posList = {pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()};
    	int meta = 0;
    	
    	for(BlockPos pos2 : posList)
    	{
    		Block block = world.getBlock(pos2.getX(), pos2.getY(), pos2.getZ());
    		if(block instanceof BlockPush ? DungeonConfig.pushSwitchType ? ((BlockPush)block).heavy == heavy  : true : false) meta = 1;
    	}

		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
    }

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(meta > 0) return activatedIcon;
    	return blockIcon;
	}
    @Override
	public void registerBlockIcons(IIconRegister reg)
	{
		this.activatedIcon = reg.registerIcon(DungeonTools.ModID + ":" + (heavy ? "push_switch_heavy_activated" : "push_switch_activated"));
    	super.registerBlockIcons(reg);
	}
    
	@Override
    public boolean canProvidePower()
    { return true; }
	@Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int meta)
    { return world.getBlockMetadata(x, y, z) == 0 ? 0 : 15; }
}
