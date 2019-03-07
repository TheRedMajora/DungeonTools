package com.theredmajora.dungeontools.extra;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IUnlockable {public boolean unlock(World world, EntityPlayer player, int x, int y, int z); }
