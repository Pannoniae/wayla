package net.panno.wayla.elements.entity;

import net.minecraft.entity.LivingEntity;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

import static net.panno.wayla.util.RenderUtil.renderEntity;

public class EntityRendererElement extends SubElement<LivingEntity> {

    EntityRendererElement(Element<LivingEntity> parent) {
        super(parent);
    }

    @Override
    public void draw(int x, int y) {
        LivingEntity entity = parent.getTarget();
        renderEntity(entity, x, y + getHeight());
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
