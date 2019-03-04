package com.theredmajora.dungeontools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = DungeonTools.ModID, version = DungeonTools.Version)
public class DungeonTools
{	
	@Instance(DungeonTools.ModID)
	public static DungeonTools instance;

    public static final String ModID = "dungeontools";
    public static final String Version = "1.0";

    public static CreativeTabs bwelluTab = new CreativeTabs("dungeonTab")
    {
		public Item getTabIconItem()
		{
			return DungeonItems.lantern;
		}
	};

    @SidedProxy(clientSide="com.theredmajora.dungeontools.ClientProxy", serverSide="com.theredmajora.dungeontools.CommonProxy")
    public static CommonProxy proxy;
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	DungeonItems.init();
        proxy.init();
		
    	System.out.print("[DungeonTools v" + Version + "] Succesfully Loaded!");
    }
}
