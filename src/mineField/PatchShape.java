package mineField;

import java.awt.*;

public class PatchShape {
    // TODO implement drawing the Patch associated
    private Patch patch;
    private int xc;
    private int yc;
    private int size;
    private Rectangle rect;

    public PatchShape(Patch patch, int xc, int yc, int size) {
        this.patch = patch;
        this.xc = xc;
        this.yc = yc;
        this.size = size;
        rect = new Rectangle(xc, yc, size, size);
    }

    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        gc.setColor(Color.WHITE);

        gc.setColor(oldColor);
    }
}
