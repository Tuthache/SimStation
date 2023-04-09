package simstation;

import mvc.*;
import java.io.Serializable;
import java.lang.Runnable;

public abstract class Agent implements Serializable, Runnable {
    // TODO add variables and methods to Agent
    protected Simulation world;
    protected String name;
    protected Heading heading;
    public int xc;
    public int yc;
    private boolean suspended;
    private boolean stopped;
    protected Thread myThread;

    public Agent(String name){
        this.name = name;
        suspended = false;
        stopped = false;
        myThread = null;
    }
    public Agent(){
        suspended = false;
        stopped = false;
        myThread = null;
    }
    public void setWorld(Simulation world){
        this.world = world;
    }

    public void run(){
        myThread = Thread.currentThread();
        while(!isStopped()){
            try{
                update();
                Thread.sleep(20);
            } catch(InterruptedException e){
                Utilities.error(e);
            }
        }
    }
    public synchronized void start(){
        world.populate();
    }
    public synchronized void suspend(){
        suspended = true;
    }
    public synchronized boolean isSuspended(){
        return suspended;
    };
    public synchronized void resume(){
        notify();
    }
    public synchronized void stop(){
        stopped = true;
    }
    public synchronized boolean isStopped(){
        return stopped;
    }
    public void move(int steps){
        switch (heading){
            case NORTH: {
                for (int i = 0; i < steps; i++){
                    yc--;
                    world.changed();
                }
            }
            case NORTHEAST:{
                for (int i = 0; i < steps; i++){
                    yc--;
                    xc++;
                    world.changed();
                }
            }
            case EAST:{
                for (int i = 0; i < steps; i++){
                    xc++;
                    world.changed();
                }
            }
            case SOUTHEAST:{
                for (int i = 0; i < steps; i++){
                    xc++;
                    yc++;
                    world.changed();
                }
            }
            case SOUTH:{
                for (int i = 0; i < steps; i++){
                    yc++;
                    world.changed();
                }
            }
            case SOUTHWEST:{
                for (int i = 0; i < steps; i++){
                    yc++;
                    xc--;
                    world.changed();
                }
            }
            case WEST:{
                for (int i = 0; i < steps; i++){
                    xc--;
                    world.changed();
                }
            }
            case NORTHWEST:{
                for (int i = 0; i < steps; i++){
                    yc--;
                    xc--;
                    world.changed();
                }
            }
        }
    }
    public abstract void update();
}
