package simstation;

import mvc.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        // get specific stats from model
        Simulation simulation = (Simulation)model;
        // create string for inform
        String[] statsMessage = {"#agents = " + simulation.getAgentCount(),
                                 "clock = " + simulation.getClock()};
        // send message
        Utilities.inform(statsMessage);
    }
}
