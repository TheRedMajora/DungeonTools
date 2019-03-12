/**package com.theredmajora.dungeontools;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class DungeonConfig
{
	public static Configuration config;
	
	public static int reappearTime = 100;
	public static int vanishTime = 0;
	public static boolean connectedLocks = false;
	public static boolean greatSkeletonKey = false;
	public static int lanternDamage = 40;
	public static int oilEfficiency = 8;
	public static boolean pushSwitchType = true;
	public static boolean pullPushBlock = true;
	public static boolean lockedDoorOpen = true;
	
	public static boolean basicKeyBreak = true;
	public static boolean redKeyBreak = true;
	public static boolean blueKeyBreak = true;
	public static boolean greenKeyBreak = true;
	public static boolean yellowKeyBreak = true;
	public static boolean orangeKeyBreak = true;
	public static boolean icyKeyBreak = true;
	public static boolean purpleKeyBreak = true;
	public static boolean blackKeyBreak = true;
	public static boolean whiteKeyBreak = true;
	public static boolean greatKeyBreak = true;
	public static boolean skeletonKeyBreak = true;
	
	public static void createConfig(File configFile)
	{
		config = new Configuration(configFile);
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
	        Property lanternDamageProp = config.get(Configuration.CATEGORY_GENERAL, "I:Lantern Oil Capacity", "40", "The amount of oil a lantern can store. [range: 1 ~ 2560, default: 40]");
	        Property oilEfficiencyProp = config.get(Configuration.CATEGORY_GENERAL, "I:Oil Efficiency", "8", "How much one oil drop fills the lantern. [range: 1 ~ 2560, default: 8]");
	        Property pushSwitchTypeProp = config.get(Configuration.CATEGORY_GENERAL, "B:Push Switch Type-Sensitive", "true", "If the Push Switch needs a Push Block of the same type. [default: true]");
	        Property pullPushBlockProp = config.get(Configuration.CATEGORY_GENERAL, "B:Push Block Pulling", "true", "If the Push Block can be pulled towards a sneaking player. [default: true]");
	        Property lockedDoorOpenProp = config.get(Configuration.CATEGORY_GENERAL, "B:Locked Doors Open", "true", "If locked doors open when unlocked. (Will be true for Iron Door variants too) [default: true]");
	        
	        reappearTime = reappearTimeProp.getInt(100);
	        vanishTime = vanishTimeProp.getInt(0);
	        connectedLocks = connectedLocksProp.getBoolean(false);
	        greatSkeletonKey = greatSkeletonKeyProp.getBoolean(false);
	        lanternDamage = lanternDamageProp.getInt(40);
	        oilEfficiency = oilEfficiencyProp.getInt(8);
	        pushSwitchType = pushSwitchTypeProp.getBoolean(true);
	        pullPushBlock = pullPushBlockProp.getBoolean(true);
	        lockedDoorOpen = lockedDoorOpenProp.getBoolean(true);
	        
	        keyBreakBehavior();
	    }
	    catch (Exception e) {} 
	    finally { if (config.hasChanged()) config.save(); }
	}

	static void keyBreakBehavior()
	{
		Property basicKeyBreakProp = config.get("key break", "B: Basic key break", "true", "Do basic keys break. [default: true]");
		Property redKeyBreakProp = config.get("key break", "B: Red key break", "true", "Do red keys break. [default: true]");
		Property blueKeyBreakProp = config.get("key break", "B: Blue key break", "true", "Do blue keys break. [default: true]");
		Property greenKeyBreakProp = config.get("key break", "B: Green key break", "true", "Do green keys break. [default: true]");
		Property yellowKeyBreakProp = config.get("key break", "B: Yellow key break", "true", "Do yellow keys break. [default: true]");
		Property orangeKeyBreakProp = config.get("key break", "B: Orange key break", "true", "Do orange keys break. [default: true]");
		Property icyKeyBreakProp = config.get("key break", "B: Icy key break", "true", "Do icy keys break. [default: true]");
		Property purpleKeyBreakProp = config.get("key break", "B: Purple key break", "true", "Do purple keys break. [default: true]");
		Property blackKeyBreakProp = config.get("key break", "B: Black key break", "true", "Do black keys break. [default: true]");
		Property whiteKeyBreakProp = config.get("key break", "B: White key break", "true", "Do white keys break. [default: true]");
		Property greatKeyBreakProp = config.get("key break", "B: Great key break", "true", "Do great keys break. [default: true]");
		Property skeletonKeyBreakProp = config.get("key break", "B: Skeleton key break", "true", "Do skeleton keys break. [default: true]");

		basicKeyBreak = basicKeyBreakProp.getBoolean(true);
		redKeyBreak = redKeyBreakProp.getBoolean(true);
		blueKeyBreak = blueKeyBreakProp.getBoolean(true);
		greenKeyBreak = greenKeyBreakProp.getBoolean(true);
		yellowKeyBreak = yellowKeyBreakProp.getBoolean(true);
		orangeKeyBreak = orangeKeyBreakProp.getBoolean(true);
		icyKeyBreak = icyKeyBreakProp.getBoolean(true);
		purpleKeyBreak = purpleKeyBreakProp.getBoolean(true);
		blackKeyBreak = blackKeyBreakProp.getBoolean(true);
		whiteKeyBreak = whiteKeyBreakProp.getBoolean(true);
		greatKeyBreak = greatKeyBreakProp.getBoolean(true);
		skeletonKeyBreak = skeletonKeyBreakProp.getBoolean(true);
	}
}*/
