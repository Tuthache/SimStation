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
    public double getPercentInfected(){
        Iterator<Agent> people = this.agentIterator();
        double numInfected = 0;
        while(people.hasNext()){
            Person p = (Person)people.next();
            if (p.getInfected()){
                numInfected++;
            }
        }
        return (numInfected / POPULATION) * 100;
    }
    public static void main(String[] args){
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}
