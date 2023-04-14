package plague;

import mvc.*;
import simstation.*;

import java.util.Iterator;

public class PlagueSimulation extends Simulation {
    public static final int POPULATION = 40;
    public void populate(){
        for (int i = 0; i < POPULATION; i++){
            addAgent(new Person());
        }
    }
    public int getPercentInfected(){
        Iterator<Agent> people = this.agentIterator();
        int numInfected = 0;
        while(people.hasNext()){
            Person p = (Person)people.next();
            if (p.getInfected()){
                numInfected++;
            }
        }
        return numInfected / POPULATION;
    }
    public static void main(String[] args){
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}
