package com.theredmajora.dungeontools.blocks;

import com.theredmajora.dungeontools.DungeonConfig;
import com.theredmajora.dungeontools.extra.IColorType;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;

public class BlockChains extends BlockVanish implements IColorType
{
	public String type;
	
	public BlockChains(String type)
	{
		super(Material.IRON, "chain_" + type, true);
        this.setBlockUnbreakable();
		this.type = type;
	}
	
	public String getType() { return type; }
	
    @Override
    public boolean getVanishFlag(IBlockState state)
    { 
    	boolean flag = DungeonConfig.connectedLocks ? state.getBlock() instanceof BlockVanishLock || state.getBlock() instanceof BlockVanishChains : false;
        return (state.getBlock() instanceof BlockLock || state.getBlock() instanceof BlockChains || flag) && ((IColorType) state.getBlock()).getType().equals(this.getType());
    }

    @Override
    public void registerBlockModel()
    {
	    ModelLoader.setCustomStateMapper(this, (new StateMap.Builder()).ignore(VANISH).build());
    	super.registerBlockModel();
    }
}