package net.panno.wayla.util;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.class_308;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;


public class RenderUtil {

    public static void renderItemStack(ItemStack stack, int x, int y) {
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SrcBlendFactor.SRC_ALPHA, GlStateManager.DstBlendFactor.ONE_MINUS_SRC_ALPHA);
            class_308.method_1453();

            ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
            itemRenderer.renderItemAndGlowInGui(stack, x, y);

            GlStateManager.disableLighting();
            GlStateManager.popMatrix();
        }
    }
    /* Borrowed from McJty */
    /**
     * Draw a beveled box. x2 and y2 are not included.
     */
    public static void drawBeveledBox(int x1, int y1, int x2, int y2, int topleftcolor, int botrightcolor, int fillcolor) {
        if (fillcolor != -1) {
            Gui.drawRect(x1+1, y1+1, x2-1, y2-1, fillcolor);
        }
        Gui.drawRect(x1, y1, x2-1, y1+1, topleftcolor);
        Gui.drawRect(x1, y1, x1+1, y2-1, topleftcolor);
        Gui.drawRect(x2-1, y1, x2, y2-1, botrightcolor);
        Gui.drawRect(x1, y2-1, x2, y2, botrightcolor);
    }

}
