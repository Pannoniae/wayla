package net.panno.wayla.elements.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.TextFormat;
import net.minecraft.util.registry.Registry;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.SubElement;

public class EntityModNameElement extends SubElement<LivingEntity> {

    EntityModNameElement(Element<LivingEntity> parent) {
        super(parent);
    }

    public String getModName() {
        LivingEntity entity = parent.getTarget();
        return Registry.ENTITY_TYPE.getId(entity.getType()).getNamespace();
    }

    @Override
    public String getTag() {
        return "modName";
    }

    @Override
    public void draw(int x, int y) {
        String modName = getModName();
        MinecraftClient.getInstance().fontRenderer.drawWithShadow(TextFormat.ITALIC + modName, x, y, 0xFFFF5555);
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
