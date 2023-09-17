package simstation;

import mvc.*;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation simulation = (Simulation)model;
        if (simulation.isRunning()) {
            Utilities.error("The simulation is already started, please stop it first");
        } else {
            if (!simulation.unsavedChanges || Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                simulation.start();
            }
        }
    }
}
