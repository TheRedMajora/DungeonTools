package com.theredmajora.dungeontools.extra;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IUnlockable {public boolean unlock(World world, BlockPos pos, IBlockState state); }
