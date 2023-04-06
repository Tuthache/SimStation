package simstation;

import mineField.Heading;
import mineField.MoveCommand;
import mvc.*;

public class SimStationFactory implements AppFactory {
    // TODO add SimStationFactory's variables and methods, remove this
    public Model makeModel() {
        return new Simulation();
    }

    public View makeView(Model m) {
        return new SimulationView(m);
    }

    public String getTitle() {
        // TODO pass title through from implementations of SimStation
    }

    public String[] getHelp() {
        // TODO write help
    }

    public String about() {
        // TODO write about
    }

    public String[] getEditCommands() {
        return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        // TODO pass model through constructors when needed?
        switch (type) {
            case "Start": {
                return new StartCommand();
            }
            case "Suspend": {
                return new SuspendCommand();
            }
            case "Resume": {
                return new ResumeCommand();
            }
            case "Stop": {
                return new StopCommand();
            }
            case "Stats": {
                return new StatsCommand();
            }
            default: {
                return null;
            }
        }
    }
}
