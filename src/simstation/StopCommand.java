package simstation;

import mvc.*;

public class StopCommand extends Command {
    public StopCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation simulation = (Simulation)model;
        if (simulation.isRunning()) {
            if (!simulation.unsavedChanges || Utilities.confirm("Stop the current simulation? It cannot be resumed!")) {
                simulation.stop();
            }
        } else {
            Utilities.error("The simulation has not started, please start it first");
        }
    }
}
