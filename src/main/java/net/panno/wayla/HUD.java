package net.panno.wayla;

import net.minecraft.client.MinecraftClient;
import net.panno.wayla.elements.BlockElement;
import net.panno.wayla.elements.Element;
import net.panno.wayla.elements.Layout;
import net.panno.wayla.elements.SubElement;

import java.util.ArrayList;

import static net.panno.wayla.util.Config.*;
import static net.panno.wayla.util.RenderUtil.drawBeveledBox;

public class HUD {

    public HUD() {
        addElement(new BlockElement());
    }

    public ArrayList<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        elements.add(element);
    }


    public void draw() {
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
        int overlayBottomY = getTotalHeight() + marginY;

        drawBeveledBox(overlayTopX, overlayTopY, overlayBottomX, overlayBottomY,
                SOLID_COLOR, 0xFFFF5555, BACKGROUND_COLOR);

        int currentY = overlayTopY;

        for (Element element : elements) {
            int currentX = x - (element.getMaxWidth() / 2);
            element.draw(currentX, currentY, Layout.CENTER_ALIGNED);
            currentY += element.getHeight();

        }
    }

    private int getTotalHeight() {
        int height = 0;
        height += marginY;
        for (Element element :
                elements) {
            height += element.getHeight();
        }
        height += marginY;

        return height;
    }

    public int getWidth() {
        int width = 0;
        for (Element subElement :
                elements) {
            if(subElement.getMaxWidth() > width) {
                width = subElement.getMaxWidth();
            }
        }
        return width;
    }

}
