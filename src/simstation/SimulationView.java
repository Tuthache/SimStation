package simstation;

import mvc.*;

import java.util.*;
import java.awt.*;

public class SimulationView extends View {

    // TODO static values
    private final static int AGENT_SIZE = 10;
    private final static Color AGENT_COLOR = Color.WHITE;
    private final static Color BACKGROUND_COLOR = Color.GRAY;

    public SimulationView(Model model) {
        super(model);
        this.setBackground(BACKGROUND_COLOR);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        // default drawing: draw all agents
        Simulation simulation = (Simulation)model;
        // get agent iterator
        Iterator<Agent> it = simulation.agentIterator();

        // draw background

        // draw a circle for every agent centered on the agent's x and y
        // TODO write this, not doing it yet because Agent isn't written
    }
}
