package net.panno.wayla.elements.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

public class EntityNameElement extends SubElement<LivingEntity> {

    EntityNameElement(Element<LivingEntity> parent) {
        super(parent);
    }

    public String getEntityName() {
        LivingEntity entity = parent.getTarget();
        return entity.getDisplayName().getText();
    }

    @Override
    public String getTag() {
        return "name";
    }

    @Override
    public void draw(int x, int y) {
        MinecraftClient mc = MinecraftClient.getInstance();
        String name = getEntityName();

        mc.fontRenderer.drawWithShadow(name, x, y, 0xFFFF5555);
    }

    @Override
    public int getWidth() {
        String name = getEntityName();
        return MinecraftClient.getInstance().fontRenderer.getStringWidth(name);
    }

    @Override
    public int getHeight() {
        return MinecraftClient.getInstance().fontRenderer.fontHeight;
    }
}
