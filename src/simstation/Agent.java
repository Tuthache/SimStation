package simstation;

import mvc.*;
import java.io.Serializable;
import java.lang.Runnable;

public abstract class Agent implements Serializable, Runnable {
    protected Simulation world;
    protected String name;
    protected Heading heading;
    public int xc;
    public int yc;
    private boolean suspended;
    private boolean stopped;
    protected Thread myThread;

    public Agent(String name){
        super();
        this.name = name;
    }
    public Agent() {
        super();
        suspended = false;
        stopped = false;
        myThread = null;
    }
    public void setWorld(Simulation world){
        this.world = world;
    }

    public void run(){
        myThread = Thread.currentThread();
        onStart();
        while(!stopped){
            try {
                if (suspended){
                    onInterrupted();
                }
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch(InterruptedException e){
                Utilities.error(e);
            }
        }
        onExit();
    }
    public synchronized void start(){
//        world.populate();
        myThread = new Thread(this);
        myThread.start();
    }
    public synchronized void suspend(){
        suspended = true;
    }
    public synchronized boolean isSuspended(){
        return suspended;
    }
    public synchronized void resume(){
        notify();
    }
    public synchronized void stop(){
        stopped = true;
    }
    public synchronized boolean isStopped(){
        return stopped;
    }
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }

    public void move(int steps){
        // TODO un-hardcode values for boundary box
        switch (heading){
            case NORTH: {
                for (int i = 0; i < steps; i++){
                    if(yc > 0)
                       yc--;
                }
                world.changed();
                break;
            }
            case NORTHEAST:{
                for (int i = 0; i < steps; i++){
                    if(yc > 0 && xc < 250) {
                        yc--;
                        xc++;
                    }
                }
                world.changed();
                break;
            }
            case EAST:{
                for (int i = 0; i < steps; i++){
                    if(xc < 250)
                        xc++;
                }
                world.changed();
                break;
            }
            case SOUTHEAST:{
                for (int i = 0; i < steps; i++){
                    if(xc < 250 && yc < 250) {
                        xc++;
                        yc++;
                    }
                }
                world.changed();
                break;
            }
            case SOUTH:{
                for (int i = 0; i < steps; i++){
                    if(yc < 250)
                        yc++;
                }
                world.changed();
                break;
            }
            case SOUTHWEST:{
                for (int i = 0; i < steps; i++){
                    if(xc > 0 && yc < 250) {
                        yc++;
                        xc--;
                    }
                }
                world.changed();
                break;
            }
            case WEST:{
                for (int i = 0; i < steps; i++){
                    if(xc > 0)
                        xc--;
                }
                world.changed();
                break;
            }
            case NORTHWEST:{
                for (int i = 0; i < steps; i++){
                    if(xc > 0 && yc > 0) {
                        yc--;
                        xc--;
                    }
                }
                world.changed();
                break;
            }
        }
    }
    public abstract void update();

    // required empty methods to be called in run()
    public void onStart() {

    }

    public void onInterrupted() {

    }

    public void onExit() {

    }
}
