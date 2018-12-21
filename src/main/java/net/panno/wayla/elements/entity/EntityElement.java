package net.panno.wayla.elements.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.Layout;

public class EntityElement extends Element<LivingEntity> {

    public EntityElement() {
        addSubElement(new EntityRendererElement(this));
        addSubElement(new EntityNameElement(this));
        addSubElement(new EntityModNameElement(this));
    }

    public EntityElement(Layout layout) {
        this.layout = layout;
        addSubElement(new EntityRendererElement(this));
        addSubElement(new EntityNameElement(this));
        addSubElement(new EntityModNameElement(this));
    }

    @Override
    public int getPriority() {
        if (!isVisible()) {
            return -1;
        }
        return 2;
    }

    @Override
    public LivingEntity getTarget() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.field_1692 instanceof LivingEntity) {
            return (LivingEntity) mc.field_1692;
        }
        /*HitResult result = mc.getCameraEntity().rayTrace(mc.interactionManager.getReachDistance(), 0.0F, FluidRayTraceMode.NONE);
        System.out.println(result.toString());

        if (result != null && result.type != HitResult.Type.NONE) {

            if (result.type == HitResult.Type.ENTITY) {
                if (result.entity instanceof LivingEntity) {
                    return (LivingEntity)result.entity;
                }
            }
        }
        */
        return null;
    }
}
