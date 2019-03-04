package com.theredmajora.dungeontools;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class DungeonConfig
{
	public static Configuration config;
	
	public static int reappearTime = 100;
	public static int vanishTime = 0;
	public static boolean connectedLocks = false;
	public static boolean greatSkeletonKey = false;
	public static int keyBehavior = 0;
	public static int lanternDamage = 40;
	public static int oilEfficiency = 8;
	public static boolean pushSwitchType = true;
	public static boolean pullPushBlock = true;
	
	public static void createConfig(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}
	
	public static void syncConfig()
	{
	    try {
	        config.load();
	        Property reappearTimeProp = config.get(Configuration.CATEGORY_GENERAL, "I:Reappearance Time", "100", "The time (in ticks) before vanishing blocks reappear (after vanishing). [range: 20 ~ 400, default: 100]");
	        Property vanishTimeProp = config.get(Configuration.CATEGORY_GENERAL, "I:Vanishing Time", "0", "The time (in ticks) before vanishing blocks vanish. [range: 0 ~ 20, default: 0]");
	        Property connectedLocksProp = config.get(Configuration.CATEGORY_GENERAL, "B:Connected Locks", "false", "If unlocking a normal lock will affect vanishing blocks (Or reverse). [default: false]");
	        Property greatSkeletonKeyProp = config.get(Configuration.CATEGORY_GENERAL, "B:Great Skeleton Key", "false", "If the skeleton key can open great locks. [default: false]");
	        Property keyBehaviorProp = config.get(Configuration.CATEGORY_GENERAL, "I:Key Breaking Behavior", "0", "Behavior for keys after they're used. (0 = All keys break on use, 1 = All keys break on use except Skeleton, 2 = Only basic keys break, 3 = No keys break.) [range: 0 ~ 3, default: 0]");
	        Property lanternDamageProp = config.get(Configuration.CATEGORY_GENERAL, "I:Lantern Oil Capacity", "40", "The amount of oil a lantern can store. [range: 1 ~ 2560, default: 40]");
	        Property oilEfficiencyProp = config.get(Configuration.CATEGORY_GENERAL, "I:Oil Efficiency", "8", "How much one oil drop fills the lantern. [range: 1 ~ 2560, default: 8]");
	        Property pushSwitchTypeProp = config.get(Configuration.CATEGORY_GENERAL, "B:Push Switch Type-Sensitive", "true", "If the Push Switch needs a Push Block of the same type. [default: true]");
	        Property pullPushBlockProp = config.get(Configuration.CATEGORY_GENERAL, "B:Push Block Pulling", "true", "If the Push Block can be pulled towards a sneaking player. [default: true]");
	        
	        reappearTime = reappearTimeProp.getInt(100);
	        vanishTime = vanishTimeProp.getInt(0);
	        connectedLocks = connectedLocksProp.getBoolean(false);
	        greatSkeletonKey = greatSkeletonKeyProp.getBoolean(false);
	        keyBehavior = keyBehaviorProp.getInt(0);
	        lanternDamage = lanternDamageProp.getInt(40);
	        oilEfficiency = oilEfficiencyProp.getInt(8);
	        pushSwitchType = pushSwitchTypeProp.getBoolean(true);
	        pullPushBlock = pullPushBlockProp.getBoolean(true);
	    }
	    catch (Exception e) {} 
	    finally { if (config.hasChanged()) config.save(); }
	}
}
