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
        while(!isStopped()){
            try {
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
                onInterrupted();
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }

    public void move(int steps){
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
                    if(yc > 0 && xc < World.VIEW_SIZE) {
                        yc--;
                        xc++;
                    }
                }
                world.changed();
                break;
            }
            case EAST:{
                for (int i = 0; i < steps; i++){
                    if(xc < World.VIEW_SIZE)
                        xc++;
                }
                world.changed();
                break;
            }
            case SOUTHEAST:{
                for (int i = 0; i < steps; i++){
                    if(xc < World.VIEW_SIZE && yc < World.VIEW_SIZE) {
                        xc++;
                        yc++;
                    }
                }
                world.changed();
                break;
            }
            case SOUTH:{
                for (int i = 0; i < steps; i++){
                    if(yc < World.VIEW_SIZE)
                        yc++;
                }
                world.changed();
                break;
            }
            case SOUTHWEST:{
                for (int i = 0; i < steps; i++){
                    if(xc > 0 && yc < World.VIEW_SIZE) {
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
        // override me when needed
    }

    public void onInterrupted() {
        // override me when needed
    }

    public void onExit() {
        // override me when needed
    }
}
