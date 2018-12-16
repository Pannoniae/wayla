package net.panno.wayla.elements.block;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.HitResult;
import net.minecraft.world.FluidRayTraceMode;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.Layout;

public class BlockElement extends Element<Block> {

    public BlockElement() {
        addSubElement(new BlockRendererElement(this));
        addSubElement(new BlockNameElement(this));
        addSubElement(new BlockModNameElement(this));
    }

    public BlockElement(Layout layout) {
        this.layout = layout;
        addSubElement(new BlockRendererElement(this));
        addSubElement(new BlockNameElement(this));
        addSubElement(new BlockModNameElement(this));
    }

    @Override
    public Block getTarget() {
        MinecraftClient mc = MinecraftClient.getInstance();
        HitResult result = mc.getCameraEntity().rayTrace(mc.interactionManager.getReachDistance(), 0.0F, FluidRayTraceMode.NONE);

        if (result != null && result.type != HitResult.Type.NONE) {

            if (result.type == HitResult.Type.BLOCK) {

                return mc.world.getBlockState(result.getBlockPos()).getBlock();
            }
        }
        return null;
    }
}
