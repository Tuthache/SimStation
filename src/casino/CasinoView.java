package casino;
import mvc.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


/*
class Dot extends JComponent {
    private Color color;
    public Dot(Color color) {
        super();
        this.color = color;
    }
    public Dot() { this(Color.GRAY); }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void paintComponent(Graphics gc) {
        Color oldColor = gc.getColor();
        gc.setColor(color);
        gc.fillOval(10,  10,  Die.dotSize, Die.dotSize);
        gc.setColor(oldColor);
    }
}

class Die extends JComponent {
    private int value = 1;
    public static int dieWidth = 110;
    public static int dieLength = 160;
    public static int dotSize = 15;
    private Dot[] dots = new Dot[6];
    public Die(int value) {
        this.value = value;
        this.setLayout(new GridLayout(2, 3));
        for(int i = 0; i < 6; i++) {
            dots[i] = new Dot();
            this.add(dots[i]);
        }
        setBorder(LineBorder.createBlackLineBorder());
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void paintComponent(Graphics gc) {

        for(int i = 0; i < 6; i++) {
            if (i < value) {
                dots[i].setColor(Color.RED);
            } else {
                dots[i].setColor(gc.getColor());
            }
            //dots[i].paintComponent(gc);
        }
    }
}


public class CasinoView extends View {
    private Die die1, die2;
    private int total;
    public CasinoView(Model model) {
        super(model);
        Casino casino = (Casino)model;
        int die1Value = casino.getDie1();
        int die2Value = casino.getDie2();
        die1 = new Die(die1Value);
        die2 = new Die(die2Value);
        setLayout(new GridLayout(1, 2));
        add(die1);
        add(die2);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Casino casino = (Casino)model;
        int die1Value = casino.getDie1();
        int die2Value = casino.getDie2();
        die1.setValue(die1Value);
        die2.setValue(die2Value);
        //die1.paintComponent(gc);
        //die2.paintComponent(gc);
    }

}
*/



class Dot extends JComponent {
    private Color color;
    public Dot(Color color) {
        super();
        this.color = color;
    }
    public Dot() { this(Color.GRAY); }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void paintComponent(Graphics gc) {
        //System.out.println("dot");
        Color oldColor = gc.getColor();
        gc.setColor(color);
        gc.fillOval(10,  10,  20, 20);
        gc.setColor(oldColor);
    }
}



class DieView extends JPanel {
    private int value = 1;
    public static int dieWidth = 110;
    public static int dieLength = 160;
    public static int dotSize = 15;
    private Dot[] dots = new Dot[6];
    public DieView(int value) {
        //super(casino);
        this.value = value;
        this.setLayout(new GridLayout(2, 3));
        for(int i = 0; i < 6; i++) {
            dots[i] = new Dot();
            JPanel p = new JPanel();

            add(dots[i]);
            //this.add(p);
        }
        setBorder(LineBorder.createBlackLineBorder());
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void paintComponent(Graphics gc) {
        //super.paintComponent(gc);
        //System.out.println("painting die");
        for(int i = 0; i < 6; i++) {
            if (i < value) {
                dots[i].setColor(Color.RED);
            } else {
                dots[i].setColor(gc.getColor());
            }
            //dots[i].paintComponent(gc);
        }
    }
}


public class CasinoView extends View {
    private DieView die1, die2;
    private int total;
    public CasinoView(Model model) {
        super(model);
        Casino casino = (Casino)model;
        int die1Value = casino.getDie1();
        int die2Value = casino.getDie2();
        die1 = new DieView(die1Value);
        die2 = new DieView(die2Value);
        die1.setValue((die1Value));
        die2.setValue((die2Value));
        setLayout(new GridLayout(1, 2));
        add(die1);
        add(die2);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Casino casino = (Casino)model;
        int die1Value = casino.getDie1();
        int die2Value = casino.getDie2();
        die1.setValue(die1Value);
        die2.setValue(die2Value);
        //die1.paintComponent(gc);
        //die2.paintComponent(gc);
    }

}
