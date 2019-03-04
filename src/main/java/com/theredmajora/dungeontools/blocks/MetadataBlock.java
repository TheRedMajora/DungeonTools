package com.theredmajora.dungeontools.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;

public class MetadataBlock extends ItemBlockWithMetadata
{
        public MetadataBlock(Block block)
        { 
        	super(block, block);
        }
}