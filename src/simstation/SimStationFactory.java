package simstation;

import mvc.*;

public class SimStationFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model m) {
        return new SimulationView((Simulation)m);
    }

    @Override
    public String getTitle() {
        // TODO pass through title from implementations?
        return "SimStation test";
    }

    @Override
    public String[] getHelp() {
        // TODO write help
        return new String[] {"sample", "sample"};
    }

    @Override
    public String about() {
        // TODO write about
        return "";
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
