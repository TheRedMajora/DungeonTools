package com.theredmajora.dungeontools.client;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import com.theredmajora.dungeontools.DungeonTools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

public class GuiScreenNote extends GuiScreen
{
    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(DungeonTools.ID + ":textures/gui/note.png");

    private final ItemStack book;
    
    private int bookTotalPages = 1;
    private int currPage;
    private NBTTagList bookPages;
    private List<ITextComponent> cachedComponents;
    private int cachedPage = -1;
    private GuiScreenNote.NextPageButton buttonNextPage;
    private GuiScreenNote.NextPageButton buttonPreviousPage;

    public GuiScreenNote(EntityPlayer player, ItemStack book)
    {
        this.book = book;

        if (book.hasTag())
        {
            NBTTagCompound nbttagcompound = book.getTag();
            this.bookPages = nbttagcompound.getList("pages", 8).copy();
            this.bookTotalPages = this.bookPages.size();

            if (this.bookTotalPages < 1)
            {
                this.bookPages.add(new NBTTagString("")); // Forge: fix MC-1685
                this.bookTotalPages = 1;
            }
        }
    }

    public void initGui()
    {
        this.buttons.clear();

        //this.addButton(new GuiButton(0, this.width / 2 - 100, 196, 200, 20, I18n.format("gui.done")));

        int i = (this.width - 192) / 2;
        this.buttonNextPage = (GuiScreenNote.NextPageButton) this.addButton(new GuiScreenNote.NextPageButton(1, i + 120, 156, true));
        this.buttonPreviousPage = (GuiScreenNote.NextPageButton) this.addButton(new GuiScreenNote.NextPageButton(2, i + 38, 156, false));
        this.updateButtons();
    }

    private void updateButtons()
    {
        this.buttonNextPage.visible = (this.currPage < this.bookTotalPages - 1);
        this.buttonPreviousPage.visible = this.currPage > 0;
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 0)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 1)
            {
                if (this.currPage < this.bookTotalPages - 1)
                {
                    ++this.currPage;
                }
            }
            else if (button.id == 2)
            {
                if (this.currPage > 0)
                {
                    --this.currPage;
                }
            }

            this.updateButtons();
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int i = (this.width - 192) / 2;
        this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);

        String s4 = I18n.format("book.pageIndicator", this.currPage + 1, this.bookTotalPages);
        String s5 = "";

        if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.size())
        {
            s5 = this.bookPages.getString(this.currPage);
        }

        if (this.cachedPage != this.currPage)
        {
            if (ItemWrittenBook.validBookTagContents(this.book.getTag()))
            {
                try
                {
                    ITextComponent itextcomponent = ITextComponent.Serializer.fromJson(s5);
                    this.cachedComponents = itextcomponent != null ? GuiUtilRenderComponents.splitText(itextcomponent, 116, this.fontRenderer, true, true) : null;
                }
                catch (JsonParseException var13)
                {
                    this.cachedComponents = null;
                }
            }
            else
            {
                TextComponentString textcomponentstring = new TextComponentString(TextFormatting.DARK_RED + "* Invalid book tag *");
                this.cachedComponents = Lists.newArrayList(textcomponentstring);
            }

            this.cachedPage = this.currPage;
        }

        int j1 = this.fontRenderer.getStringWidth(s4);
        this.fontRenderer.drawString(s4, i - j1 + 192 - 44, 18, 0);

        if (this.cachedComponents == null)
        {
            this.fontRenderer.drawSplitString(s5, i + 36, 34, 116, 0);
        }
        else
        {
            int k1 = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());

            for (int l1 = 0; l1 < k1; ++l1)
            {
                ITextComponent itextcomponent2 = this.cachedComponents.get(l1);
                this.fontRenderer.drawString(itextcomponent2.getUnformattedComponentText(), i + 36, 34 + l1 * this.fontRenderer.FONT_HEIGHT, 0);
            }

            ITextComponent itextcomponent1 = this.getClickedComponentAt(mouseX, mouseY);

            if (itextcomponent1 != null)
            {
                this.handleComponentHover(itextcomponent1, mouseX, mouseY);
            }
        }

        //super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        if (mouseButton == 0)
        {
            ITextComponent itextcomponent = this.getClickedComponentAt(mouseX, mouseY);

            if (itextcomponent != null && this.handleComponentClick(itextcomponent))
            {
                return;
            }
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Executes the click event specified by the given chat component
     */
    public boolean handleComponentClick(ITextComponent component)
    {
        ClickEvent clickevent = component.getStyle().getClickEvent();

        if (clickevent == null)
        {
            return false;
        }
        else if (clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE)
        {
            String s = clickevent.getValue();

            try
            {
                int i = Integer.parseInt(s) - 1;

                if (i >= 0 && i < this.bookTotalPages && i != this.currPage)
                {
                    this.currPage = i;
                    this.updateButtons();
                    return true;
                }
            }
            catch (Throwable var5)
            {
                ;
            }

            return false;
        }
        else
        {
            boolean flag = super.handleComponentClick(component);

            if (flag && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }

            return flag;
        }
    }

    @Nullable
    public ITextComponent getClickedComponentAt(int p_175385_1_, int p_175385_2_)
    {
        if (this.cachedComponents == null)
        {
            return null;
        }
        else
        {
            int i = p_175385_1_ - (this.width - 192) / 2 - 36;
            int j = p_175385_2_ - 2 - 16 - 16;

            if (i >= 0 && j >= 0)
            {
                int k = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());

                if (i <= 116 && j < this.mc.fontRenderer.FONT_HEIGHT * k + k)
                {
                    int l = j / this.mc.fontRenderer.FONT_HEIGHT;

                    if (l >= 0 && l < this.cachedComponents.size())
                    {
                        ITextComponent itextcomponent = this.cachedComponents.get(l);
                        int i1 = 0;

                        for (ITextComponent itextcomponent1 : itextcomponent)
                        {
                            if (itextcomponent1 instanceof TextComponentString)
                            {
                                i1 += this.mc.fontRenderer.getStringWidth(((TextComponentString)itextcomponent1).getText());

                                if (i1 > i)
                                {
                                    return itextcomponent1;
                                }
                            }
                        }
                    }

                    return null;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    static class NextPageButton extends GuiButton
        {
            private final boolean isForward;

            public NextPageButton(int buttonId, int x, int y, boolean isForwardIn)
            {
                super(buttonId, x, y, 23, 13, "");
                this.isForward = isForwardIn;
            }

            /**
             * Draws this button to the screen.
             */
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
            {
                if (this.visible)
                {
                    boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    mc.getTextureManager().bindTexture(GuiScreenNote.BOOK_GUI_TEXTURES);
                    int i = 0;
                    int j = 192;

                    if (flag)
                    {
                        i += 23;
                    }

                    if (!this.isForward)
                    {
                        j += 13;
                    }

                    this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);
                }
            }
        }
}