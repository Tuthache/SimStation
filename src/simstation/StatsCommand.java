package simstation;

import mvc.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    // OVERRIDE THIS IN StatsCommand EXTENSIONS!
    private String[] getStatsMessage() {
        // get specific stats from model
        Simulation simulation = (Simulation)model;
        // create string for inform
        String[] msg = {
                "#agents = " + simulation.getAgentCount(),
                "clock = " + simulation.getClock()};
        return msg;
    }

    public void execute() throws Exception {
        // send message
        Utilities.inform(getStatsMessage());
    }
}
