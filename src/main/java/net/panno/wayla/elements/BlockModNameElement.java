package net.panno.wayla.elements;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.registry.Registry;

public class BlockModNameElement extends SubElement<Block> {

    BlockModNameElement(Element parent) {
        super(parent);
    }

    public String getModName() {
        Block block = parent.getTarget();
        return Registry.BLOCK.getId(block).getNamespace();
    }

    @Override
    public void draw(int x, int y) {
        String modName = getModName();
        MinecraftClient.getInstance().fontRenderer.drawWithShadow("§o"+modName, x, y, 0xFFFF5555);
    }

    @Override
    public int getWidth() {
        String modName = getModName();
        return MinecraftClient.getInstance().fontRenderer.getStringWidth(modName);
    }

    @Override
    public int getHeight() {
        return MinecraftClient.getInstance().fontRenderer.FONT_HEIGHT;
    }
}