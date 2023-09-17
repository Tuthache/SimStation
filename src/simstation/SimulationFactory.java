package simstation;

import mvc.*;

public class SimulationFactory implements AppFactory {
// renamed file to match the name in the RandomWalks sample

    @Override // OVERRIDE THIS IN EXTENSIONS! SEE RandomWalkSimulation.java FOR AN EXAMPLE
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model m) {
        return new SimulationView((Simulation)m);
    }

    @Override // OVERRIDE THIS IN EXTENSIONS! SEE RandomWalkSimulation.java FOR AN EXAMPLE
    public String getTitle() {
        return "SimStation";
    }

    @Override // OVERRIDE THIS IN EXTENSIONS?
    public String[] getHelp() {
        return new String[] {
                "Start: Begins a new simulation",
                "Suspend: Suspends an active simulation",
                "Resume: Resumes a suspended simulation",
                "Stop: Ends a simulation, cannot resume after stopping",
                "Stats: Displays information from current simulation"};
    }

    @Override // override this in extensions?
    public String about() {
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
                return statsCommand(model);
            }
            default -> {
                return null;
            }
        }
    }

    // OVERRIDE THIS IN FACTORY TO CUSTOMIZE STATS MESSAGE
    protected StatsCommand statsCommand(Model m) {
        return new StatsCommand(m);
    }
}
