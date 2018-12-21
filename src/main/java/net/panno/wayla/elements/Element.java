package net.panno.wayla.elements;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.HashMap;

import static net.panno.wayla.util.Config.innerMargin;

public abstract class Element<T> implements Comparable<Element> {

    public Layout layout;
    private ArrayList<SubElement> subElements = new ArrayList<>();
    private HashMap<String, SubElement> tagMap = new HashMap<>();


    // for example, a block, fluid, etc., which is commonly used throughout the subelements
    public abstract T getTarget();

    public Element(Layout layout) {
        this.layout = layout;
    }

    public Element() {
        this.layout = Layout.CENTER_ALIGNED;
    }

    public void draw(int x, int y) {

        if (isVisible()) {

            if (layout == Layout.SPECIAL) {
                SubElement render = getElementWithTag("render");
                render.draw(x, y + (getHeight() / 2) - (render.getHeight() / 2));
                SubElement name = getElementWithTag("name");
                name.draw(x + render.getWidth() + innerMargin, y);
                SubElement modName = getElementWithTag("modName");
                modName.draw(x + render.getWidth() + innerMargin, y + innerMargin + name.getHeight());
                return;
            }

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

    public SubElement getElementWithTag(String tag) {
        if (!tagMap.containsKey(tag)) {
            for (SubElement se : subElements) {
                if (se.getTag().equals(tag)) {
                    tagMap.put(tag, se);
                    return se;
                }
            }
        } else {
            return tagMap.get(tag);
        }
        return null; //something got fucked up aka crash the program
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
    }

    public ArrayList<SubElement> getSubElements() {
        return subElements;
    }

    public int getMaxWidth() {

        if (!isVisible()) {
            return 0;
        }

        if (layout == Layout.SPECIAL) {
            return getElementWithTag("render").getWidth() +
                    innerMargin +
                    Math.max(
                            getElementWithTag("modName").getWidth(),
                            getElementWithTag("name").getWidth()
                    );
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

        if (layout == Layout.SPECIAL) {
            return getElementWithTag("modName").getHeight() + innerMargin + getElementWithTag("name").getHeight();
        }

        int height = 0;
        for (SubElement subElement : subElements) {

            height += subElement.getHeight();
        }
        return height;
    }

}
