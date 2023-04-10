package simstation;

import mvc.*;

public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation simulation = (Simulation)model;
        if (!simulation.isRunning()) {
            Utilities.error("The simulation has not started, please start it first");
        } else if (!simulation.isSuspended()) {
            simulation.suspend();
        } else {
            Utilities.error("The simulation is already suspended!");
        }
    }
}
