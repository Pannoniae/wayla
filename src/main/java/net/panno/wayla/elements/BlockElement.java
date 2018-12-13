package net.panno.wayla.elements;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.HitResult;
import net.minecraft.world.FluidRayTraceMode;

public class BlockElement extends Element {

    private MinecraftClient mc;

    public BlockElement() {
        mc = MinecraftClient.getInstance();
        addSubElement(new BlockRendererElement(this));
        addSubElement(new BlockNameElement(this));
        addSubElement(new BlockModNameElement(this));
    }

    @Override
    public Block getTarget() {
        HitResult result = mc.getCameraEntity().rayTrace(mc.interactionManager.getReachDistance(), 0.0F, FluidRayTraceMode.NONE);

        if (result != null && result.type != HitResult.Type.NONE) {

            if (result.type == HitResult.Type.BLOCK) {

                return mc.world.getBlockState(result.getBlockPos()).getBlock();
            }
        }
        return null;
    }
}
