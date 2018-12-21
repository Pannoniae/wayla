package net.panno.wayla.elements.fluid;

import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.registry.Registry;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

public class FluidModNameElement extends SubElement<FluidState> {

    FluidModNameElement(Element parent) {
        super(parent);
    }

    public String getModName() {
        FluidState fluidState = parent.getTarget();
        return Registry.FLUID.getId(fluidState.getFluid()).getNamespace();
    }

    @Override
    public String getTag() {
        return "modName";
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
        return MinecraftClient.getInstance().fontRenderer.fontHeight;
    }
}
