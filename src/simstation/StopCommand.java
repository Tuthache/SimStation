package simstation;

import mvc.*;

public class StopCommand extends Command {
    public StopCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation simulation = (Simulation)model;
        simulation.stop();
    }
}
