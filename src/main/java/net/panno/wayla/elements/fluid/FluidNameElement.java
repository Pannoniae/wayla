package net.panno.wayla.elements.fluid;

import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

public class FluidNameElement extends SubElement<FluidState> {

    FluidNameElement(Element parent) {
        super(parent);
    }

    public String getBlockName() {
        FluidState fluidState = parent.getTarget();
        return fluidState.getBlockState().getBlock().getTextComponent().getText();
    }

    @Override
    public String getTag() {
        return "name";
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
