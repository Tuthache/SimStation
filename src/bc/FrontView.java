package bc;

import mvc.*;
import java.awt.*;

public class FrontView extends View {

    public FrontView(Model brick) { super(brick); }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Brick brick = (Brick)model;
        Color oldColor = gc.getColor();
        double x = brick.getWidth();
        double y = brick.getHeight();
        gc.setColor(Color.BLACK);
        gc.drawString("Front View", 10, 10);
        gc.setColor(Color.RED);
        gc.fillRect(15, 15, (int)x, (int)y);

        gc.setColor(oldColor);
    }
}
