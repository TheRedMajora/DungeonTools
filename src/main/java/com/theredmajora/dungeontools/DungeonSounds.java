package com.theredmajora.dungeontools;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class DungeonSounds {
    public static final SoundEvent
    		lock = registerSound("unlock.door"),
    	    success = registerSound("success"),
            fail = registerSound("unlock.fail");

    public static SoundEvent registerSound(String name) {
        final ResourceLocation soundID = new ResourceLocation(DungeonTools.ID, name);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }
}