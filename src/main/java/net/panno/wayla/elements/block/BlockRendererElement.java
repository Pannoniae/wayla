package net.panno.wayla.elements.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

import static net.panno.wayla.util.RenderUtil.renderItemStack;

public class BlockRendererElement extends SubElement<Block> {

    BlockRendererElement(Element parent) {
        super(parent);
    }

    @Override
    public void draw(int x, int y) {
        Block block = parent.getTarget();
        ItemStack stack = block.getItem().getDefaultStack();

        renderItemStack(stack, x, y);
    }

    @Override
    public String getTag() {
        return "render";
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
