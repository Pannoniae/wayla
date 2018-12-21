package net.panno.wayla;

import net.minecraft.client.MinecraftClient;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.Layout;
import net.panno.wayla.elements.block.BlockElement;
import net.panno.wayla.elements.fluid.FluidElement;

import java.util.ArrayList;
import java.util.Collections;

import static net.panno.wayla.util.Config.*;
import static net.panno.wayla.util.RenderUtil.drawBeveledBox;

public class HUD {

    public HUD() {
        addElement(new BlockElement(Layout.SPECIAL));
        addElement(new FluidElement(Layout.SPECIAL));
        //addElement(new EntityElement(Layout.SPECIAL));
    }

    public ArrayList<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        elements.add(element);
    }


    public void draw() {
        // apart from 2 margins no element is there
        if (getTotalHeight() == 0) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();

        // more precise X
        float x_ = mc.window.getScaledWidth() / 2.0F;
        int x = (int)x_;

        int overlayTopX = x - (getWidth() / 2) - marginX;
        int overlayTopY = marginY;

        int overlayBottomX = x + (getWidth() / 2) + marginX;
        int overlayBottomY = overlayTopY + marginY + getTotalHeight() + marginY;

        drawBeveledBox(overlayTopX, overlayTopY, overlayBottomX, overlayBottomY,
                SOLID_COLOR, 0xFFFF5555, BACKGROUND_COLOR);

        int currentY = overlayTopY + marginY;

        if (drawOnlyOne) {
            Element element = getMostImportantElement();
            int currentX = x - (element.getMaxWidth() / 2);
            element.draw(currentX, currentY);
            return;
        }

        for (Element element : elements) {
            int currentX = x - (element.getMaxWidth() / 2);
            element.draw(currentX, currentY);
            currentY += element.getHeight();

        }
    }

    private int getTotalHeight() {
        if (drawOnlyOne) {
            return getMostImportantElement().getHeight();
        }

        int height = 0;
        for (Element element : elements) {
            height += element.getHeight();
        }

        return height;
    }

    private Element getMostImportantElement() {
        return Collections.max(elements);
    }

    public int getWidth() {
        if (drawOnlyOne) {
            return getMostImportantElement().getMaxWidth();
        }

        int width = 0;
        for (Element element : elements) {

            if (element.getMaxWidth() > width) {
                width = element.getMaxWidth();
            }
        }
        return width;
    }

}
