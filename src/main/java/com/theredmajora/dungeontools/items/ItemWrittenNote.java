package com.theredmajora.dungeontools.items;

import com.theredmajora.dungeontools.gui.GuiScreenNote;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemWrittenNote extends ItemDungeon
{
    public ItemWrittenNote()
    {
    	super("written_note");
        this.setMaxStackSize(1);
    }

    public static boolean validBookTagContents(NBTTagCompound nbt)
    {
        if (!nbt.hasKey("title", 8))
        {
            return false;
        }

        String s = nbt.getString("title");
        return s != null && s.length() <= 16 ? nbt.hasKey("author", 8) : false;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        if (stack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            String s = nbttagcompound.getString("title");

            if (!StringUtils.isNullOrEmpty(s))
            {
                return s;
            }
        }

        return super.getItemStackDisplayName(stack);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	if(world.isRemote) Minecraft.getMinecraft().displayGuiScreen(new GuiScreenNote(player));
    	
        return stack;
    }

    public boolean getShareTag()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}