package net.panno.wayla.elements.fluid;

import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

import static net.panno.wayla.util.RenderUtil.renderItemStack;

public class FluidRendererElement extends SubElement<FluidState> {

    FluidRendererElement(Element parent) {
        super(parent);
    }

    @Override
    public String getTag() {
        return "render";
    }

    @Override
    public void draw(int x, int y) {
        FluidState fluidState = parent.getTarget();
        Item item = fluidState.getFluid().getBucketItem();
        ItemStack stack = item.getDefaultStack();

        renderItemStack(stack, x, y);
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }
}
