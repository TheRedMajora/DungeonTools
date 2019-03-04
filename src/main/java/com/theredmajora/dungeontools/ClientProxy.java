package com.theredmajora.dungeontools;

import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;
import com.theredmajora.dungeontools.tileentity.TileEntityGroundItemRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	public void init()
	{
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGroundItem.class, new TileEntityGroundItemRenderer());
	}
}
