package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {
    // TODO add variables and methods to Simulation

    // added professor's code below
    private Timer timer;
    private int clock;

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

    // etc.
}
