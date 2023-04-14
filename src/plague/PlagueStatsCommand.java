package plague;

import simstation.*;
import mvc.*;

public class PlagueStatsCommand extends StatsCommand {
    public PlagueStatsCommand(Model m){
        super(m);
    }
    public String[] getStatsMessage(){
        PlagueSimulation plague = (PlagueSimulation) model;
        int infectedRatio = plague.getPercentInfected();
        return new String[] {
                "#agents = " + plague.POPULATION,
                "clock = " + plague.getClock(),
                "%infected = " + infectedRatio
        };
    }
}
