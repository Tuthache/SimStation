package plague;

import simstation.*;
import mvc.*;
public class PlagueFactory extends SimulationFactory{
    public Model makeModel(){
        return new PlagueSimulation();
    }
    public String getTitle(){
        return "Plague";
    }
    protected StatsCommand statsCommand(Model m){
        return new PlagueStatsCommand(m);
    }
}
