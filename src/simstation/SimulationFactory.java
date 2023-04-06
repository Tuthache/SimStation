package simstation;

import mvc.*;

public class SimulationFactory implements AppFactory {
// renamed file to match the name in the RandomWalks sample

    @Override // OVERRIDE THIS IN EXTENSIONS! SEE RandomWalks.java FOR AN EXAMPLE
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model m) {
        return new SimulationView((Simulation)m);
    }

    @Override // OVERRIDE THIS IN EXTENSIONS! SEE RandomWalks.java FOR AN EXAMPLE
    public String getTitle() {
        return "SimStation";
    }

    @Override // OVERRIDE THIS IN EXTENSIONS!
    public String[] getHelp() {
        // TODO write help
        return new String[] {"SimStation help text line 1", "SimStation help text line 2"};
    }

    @Override // override this in extensions?
    public String about() {
        // TODO write about
        return "COPYRIGHT 2023 SimStation Kyle James, Austin Nguyen, Stanley Nguyen, CS151-02";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        switch (type) {
            case "Start" -> {
                return new StartCommand(model);
            }
            case "Suspend" -> {
                return new SuspendCommand(model);
            }
            case "Resume" -> {
                return new ResumeCommand(model);
            }
            case "Stop" -> {
                return new StopCommand(model);
            }
            case "Stats" -> {
                return new StatsCommand(model);
            }
            default -> {
                return null;
            }
        }
    }
}
