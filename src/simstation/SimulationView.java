package simstation;

import mvc.*;

import java.util.*;
import java.awt.*;

public class SimulationView extends View {

    // TODO static values
    protected final static int AGENT_SIZE = 5;
    protected Color agentColor = Color.WHITE;
    protected Color backgroundColor = Color.GRAY;

    public SimulationView(Model model) {
        super(model);
        this.setBackground(backgroundColor);
    }

    protected void drawAgents(Graphics gc) {
        Simulation simulation = (Simulation)model;
        // get agent iterator
        Iterator<Agent> it = simulation.agentIterator();
        // get agent color
        gc.setColor(agentColor);
        // draw a circle for every agent centered on the agent's x and y
        int centerOffset = AGENT_SIZE / 2;
        while (it.hasNext()) {
            Agent c = it.next();
            // draw agent
            gc.fillOval(c.xc - centerOffset, c.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();

        // draw bounds for agents
        gc.setColor(Color.BLACK);
        gc.drawRect(0,0, World.VIEW_SIZE, World.VIEW_SIZE);
        // default drawing: draw all agents
        drawAgents(gc);

        gc.setColor(oldColor);
    }
}
