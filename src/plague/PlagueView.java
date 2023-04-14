package plague;

import simstation.*;
import mvc.*;
import java.awt.*;
import java.util.Iterator;

public class PlagueView extends SimulationView{
    private static final Color COLOR_INFECTED = Color.RED;
    private static final Color COLOR_SAFE = Color.GREEN;
    public PlagueView(Model m){
        super(m);
    }
    @Override
    protected void drawAgents(Graphics gc){
        PlagueSimulation plague = (PlagueSimulation) model;
        Iterator<Agent> people = plague.agentIterator();

        int centerOffset = AGENT_SIZE / 2;
        gc.setColor(agentColor);
        while(people.hasNext()){
            Person p = (Person) people.next();
            boolean checkInfected = p.getInfected();
            if (checkInfected){
                gc.setColor(COLOR_INFECTED);
            } else {
                gc.setColor(COLOR_SAFE);
            }
            gc.fillOval(p.xc - centerOffset, p.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
    }
}
