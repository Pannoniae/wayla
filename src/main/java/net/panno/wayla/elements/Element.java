package net.panno.wayla.elements;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

import static net.panno.wayla.util.Config.innerMargin;

public abstract class Element<T> implements Comparable<Element> {

    ArrayList<SubElement> subElements = new ArrayList<>();

    // for example, a block, fluid, etc., which is commonly used throughout the subelements
    public abstract T getTarget();

    public void draw(int x, int y, Layout layout) {

        if (isVisible()) {

            for (SubElement e : getSubElements()) {
                y += innerMargin;
                if (layout == Layout.LEFT_ALIGNED) {
                    e.draw(x, y);
                }
                else if (layout == Layout.CENTER_ALIGNED) {
                    e.draw(MinecraftClient.getInstance().window.getScaledWidth() / 2 - (e.getWidth() / 2), y);
                }
                y += e.getHeight();

            }
        }
    }

    public boolean isVisible() {
        return getTarget() != null;
    }

    public int getPriority() {
        if (!isVisible()) {
            return -1;
        }
        return 0;
    }

    @Override
    public int compareTo(Element element) {
        return this.getPriority() - element.getPriority();
    }

    public void addSubElement(SubElement element) {
        subElements.add(element);
    };

    public ArrayList<SubElement> getSubElements() {
        return subElements;
    };

    public int getMaxWidth() {

        if (!isVisible()) {
            return 0;
        }

        int width = 0;
        for (SubElement subElement : subElements) {
            if (subElement.getWidth() > width) {
                width = subElement.getWidth();
            }
        }
        return width;
    }

    public int getHeight() {

        if (!isVisible()) {
            return 0;
        }

        int height = 0;
        for (SubElement subElement : subElements) {

            height += subElement.getHeight();
        }
        height += innerMargin;
        return height;
    }

}
