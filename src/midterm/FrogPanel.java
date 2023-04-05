package midterm; // changed this line!

/* NOTE TO GRADER
* I put my midterm package inside a src package, hence the src.midterm
* mvc is also in the same src package, hence the src.mvc.*
* had to modify those two on my end from the prof's code
*
* that's all thanks --Kyle James
* */

import mvc.*; // changed this line!
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;


class Point implements Serializable {
    public Integer x, y;
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}

class Frog extends Model {
    public static Integer WORLD_SIZE = 250;

    private Point location;
    private List<Point> pads;

    public Frog() {
        super();
        this.location = new Point(WORLD_SIZE / 2, WORLD_SIZE / 2);
        this.pads = new ArrayList<Point>();
    }

    // Jumps to a random location within the world size, updating the list of lilypads and current location
    public void leap() {
        // save old location
        Point oldLocation = location;
        // add last location to list
        pads.add(location);
        // randomly set new location to something between 1 and WORLD_SIZE
        int newX = Utilities.rng.nextInt(WORLD_SIZE) + 1;
        int newY = Utilities.rng.nextInt(WORLD_SIZE) + 1;
        location = new Point(newX, newY);
        // push an update to listeners & changed
        changed();
        firePropertyChange("NewPosition", oldLocation, location);
    }

    // clears the pad list
    public void clear() {
        if (Utilities.confirm("Are you sure you want to clear all lilypads?")) {
            pads.clear();
            changed();
            firePropertyChange("ClearedPads", null, null);
        }
    }

    public List<Point> getPads() {
        return pads;
    }

    public Point getLocation() {
        return location;
    }
}


class FrogView extends View {

    public FrogView(Model model) {
        super(model);
        setSize(Frog.WORLD_SIZE, Frog.WORLD_SIZE);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        int lilypadDiameter = 10;
        int lilypadDif = lilypadDiameter / 2;
        Frog frog = (Frog)model;
        Color oldColor = gc.getColor();

        // draw list of lilypads
        List<Point> pads = frog.getPads();
        Point loc = frog.getLocation();
        gc.setColor(Color.GREEN);
        for (Point p : pads) {
            gc.fillOval(p.x - lilypadDif, p.y - lilypadDif, lilypadDiameter, lilypadDiameter);
        }
        // draw frog
        gc.setColor(Color.RED);
        gc.fillOval(loc.x - lilypadDif, loc.y - lilypadDif, lilypadDiameter, lilypadDiameter);

        // reset color
        gc.setColor(oldColor);
    }
}

class LeapCommand extends Command {

    public LeapCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Frog frog = (Frog)model;
        frog.leap();
    }
}

class ClearCommand extends Command {

    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Frog frog = (Frog)model;
        frog.clear();
    }
}

class FrogFactory implements AppFactory {

    public Model makeModel() {
        return new Frog();
    }

    public String[] getEditCommands() { return new String[] { "Leap", "Clear"}; }

    public Command makeEditCommand(Model model, String type, Object src) {
        switch (type) {
            case "Leap": {
                return new LeapCommand(model);
            }
            case "Clear": {
                return new ClearCommand(model);
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public View makeView(Model model) {
        return new FrogView(model);
    }

    public String getTitle() { return "Leap Frog"; }

    public String[] getHelp() {
        String[] cmmds = new String[2];
        cmmds[0] = "Leap: frog leaps to a random location";
        cmmds[1] = "Clear: frog.path = Nil";
        return cmmds;
    }

    public String about() {
        // return a string containing your name
        return "MIDTERM CS151-02 2023 by Kyle James";
    }
}

public class FrogPanel extends AppPanel {

    public FrogPanel(AppFactory factory) {
        super(factory);

        // FrogView created and added by AppPanel constructor

        this.setLayout(new GridLayout(1, 2));
        // controlPanel inherited from AppPanel
        controlPanel.setLayout(new GridLayout(2, 1));

        JPanel p = new JPanel();
        JButton b = new JButton("Leap");
        b.addActionListener(this);
        p.add(b);
        controlPanel.add(p);

        p = new JPanel();
        b = new JButton("Clear");
        b.addActionListener(this);
        p.add(b);
        controlPanel.add(p);
    }

    public static void main(String[] args) {
        AppPanel panel = new FrogPanel(new FrogFactory());
        panel.display();
    }
}
