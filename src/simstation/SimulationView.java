package simstation;

import mvc.*;

import java.util.*;
import java.awt.*;

public class SimulationView extends View {

    // TODO static values
    private final static int AGENT_SIZE = 5;
    private final static Color AGENT_COLOR = Color.WHITE;
    private final static Color BACKGROUND_COLOR = Color.GRAY;

    public SimulationView(Model model) {
        super(model);
        this.setBackground(BACKGROUND_COLOR);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();

        // draw bounds for agents
        gc.setColor(Color.BLACK);
        gc.drawRect(0,0, World.VIEW_SIZE, World.VIEW_SIZE);
        // default drawing: draw all agents
        Simulation simulation = (Simulation)model;
        // get agent iterator
        Iterator<Agent> it = simulation.agentIterator();
        // get agent color
        gc.setColor(AGENT_COLOR);
        // draw a circle for every agent centered on the agent's x and y
        int centerOffset = AGENT_SIZE / 2;
        while (it.hasNext()) {
            Agent c = it.next();
            // draw agent
            gc.fillOval(c.xc - centerOffset, c.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
        gc.setColor(oldColor);
    }
}
