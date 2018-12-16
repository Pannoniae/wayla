package net.panno.wayla.elements.fluid;

import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.HitResult;
import net.minecraft.world.FluidRayTraceMode;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.Layout;

public class FluidElement extends Element<FluidState> {

    public FluidElement(Layout layout) {
        this.layout = layout;
        addSubElement(new FluidRendererElement(this));
        addSubElement(new FluidNameElement(this));
        addSubElement(new FluidModNameElement(this));
    }

    public FluidElement() {
        addSubElement(new FluidRendererElement(this));
        addSubElement(new FluidNameElement(this));
        addSubElement(new FluidModNameElement(this));
    }

    @Override
    public int getPriority() {
        if (!isVisible()) {
            return -1;
        }
        return 1;
    }

    @Override
    public FluidState getTarget() {
        MinecraftClient mc = MinecraftClient.getInstance();
        HitResult result = mc.getCameraEntity().rayTrace(mc.interactionManager.getReachDistance(), 0.0F, FluidRayTraceMode.ALWAYS);

        if (result != null && result.type != HitResult.Type.NONE) {

            if (result.type == HitResult.Type.BLOCK) {
                return mc.world.getFluidState(result.getBlockPos());
            }
        }
        return null;
    }

    @Override
    public boolean isVisible() {
        return getTarget() != null && getTarget().getFluid() != null && getTarget().getFluid() != Fluids.EMPTY;
    }
}
