package simstation;

import mvc.*;
import java.util.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    // OVERRIDE THIS IN StatsCommand EXTENSIONS! and use super() to get the list if using original functionality
    protected String[] getStatsMessage() {
        // get specific stats from model
        Simulation simulation = (Simulation)model;
        // create string for inform
        return new String[]{"#agents = " + simulation.getAgentCount(),
                            "clock = " + simulation.getClock()};
    }

    public void execute() throws Exception {
        // send message
        Utilities.inform(getStatsMessage());
    }
}
