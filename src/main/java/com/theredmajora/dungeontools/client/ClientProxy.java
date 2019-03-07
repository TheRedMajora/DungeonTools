package com.theredmajora.dungeontools.client;

import com.theredmajora.dungeontools.tileentity.TileEntityGroundItem;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy
{
	public void init()
	{ ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGroundItem.class, new TileEntityGroundItemRenderer()); }
}
