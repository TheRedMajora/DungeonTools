package com.theredmajora.dungeontools;

import com.theredmajora.dungeontools.client.ClientProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(modid = DungeonTools.ModID, version = DungeonTools.Version)
public class DungeonTools
{	
	@Instance(DungeonTools.ModID)
	public static DungeonTools instance;

    public static final String ModID = "dungeontools";
    public static final String Version = "2.0";

    public static CreativeTabs dungeonTab = new CreativeTabs("dungeonTab")
    {
		public Item getTabIconItem()
		{ return DungeonItems.lantern; }
	};

    @SidedProxy(clientSide="com.theredmajora.dungeontools.client.ClientProxy")
    public static ClientProxy proxy;
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	DungeonItems.init();
        proxy.init();
        
    	System.out.print("[DungeonTools v" + Version + "] Succesfully Loaded!");
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    { DungeonConfig.createConfig(event.getSuggestedConfigurationFile()); }
}
