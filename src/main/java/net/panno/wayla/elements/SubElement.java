package net.panno.wayla.elements;

public abstract class SubElement<T> {

    public Element<T> parent;

    public SubElement(Element parent) {
        this.parent = parent;
    }

    public String getTag() {
        return "";
    }

    // whether the widget is always shown
    private boolean alwaysShown = false;

    // main method to override; this draws the element.
    abstract public void draw(int x, int y);

    // used for layout
    abstract public int getWidth();

    abstract public int getHeight();

}
