package net.panno.wayla.elements.block;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

public class BlockNameElement extends SubElement<Block> {

    BlockNameElement(Element parent) {
        super(parent);
    }

    public String getBlockName() {
        Block block = parent.getTarget();
        return block.getTextComponent().getText();
    }

    @Override
    public void draw(int x, int y) {
        MinecraftClient mc = MinecraftClient.getInstance();
        String name = getBlockName();

        mc.fontRenderer.drawWithShadow(name, x, y, 0xFFFF5555);
    }

    @Override
    public int getWidth() {
        String name = getBlockName();
        return MinecraftClient.getInstance().fontRenderer.getStringWidth(name);
    }

    @Override
    public int getHeight() {
        return MinecraftClient.getInstance().fontRenderer.FONT_HEIGHT;
    }
}