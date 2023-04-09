package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {
    private List<Agent> agents;

    // added professor's code below
    private Timer timer;
    private int clock;

    public Simulation() {
        agents = new LinkedList<Agent>();
        clock = 0;
    }

    // agent iterator for view drawing
    public Iterator<Agent> agentIterator() {
        return agents.iterator();
    }

    public int getAgentCount() {
        return agents.size();
    }

    public void addAgent(Agent a) { agents.add(a); }

    public void start()
    {
        clock = 0;
        startTimer();
        for(Agent a : agents) { a.start(); }
        changed();
    }

    public void suspend()
    {
        stopTimer();
        for(Agent a : agents) { a.suspend(); }
        changed();
    }

    public void resume()
    {
        startTimer();
        for(Agent a : agents) { a.resume(); }
        changed();
    }

    public void stop()
    {
        stopTimer();
        for(Agent a : agents) { a.stop(); }
        changed();
    }

    public synchronized Agent getNeighbor(Agent a, double radius)
    {
        int xcLowerBound = a.xc - radius;
        int xcUpperBound = a.xc + radius;
        int ycLowerBound = a.yc - radius;
        int ycUpperBound = a.yc + radius;
        int start = Utilities.rng.nextInt(agents.size());
        Agent curr = agents.get(start);
        if(curr != a &&
                curr.xc >= xcLowerBound && curr.xc <= xcUpperBound &&
                curr.yc >= ycLowerBound && curr.yc <= ycUpperBound)
        {
            return curr;
        }

        // loop through agents until return to random starting point
        for(int i = start+1; i != start; i++)
        {
            curr = agents.get(i);
            if(curr != a &&
                    curr.xc >= xcLowerBound && curr.xc <= xcUpperBound &&
                    curr.yc >= ycLowerBound && curr.yc <= ycUpperBound)
            {
                return curr;
            }

            // index wraps back to the beginning
            if(i == agents.size()-1) i = 0;
        }
        return null;
    }

    public void populate() { /* specified in subclasses */ }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

    public int getClock() {
        return clock;
    }
}
