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
        this(); // calls empty constructor
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
        int xTemp, yTemp;
        switch (heading){
            case NORTH:{
                yc = (yc - steps % World.VIEW_SIZE + World.VIEW_SIZE) % World.VIEW_SIZE;
                world.changed();
                break;
            }
            case NORTHEAST:{
                yc -= steps;
                xc += steps;
                if(yc < 0) {
                    xTemp = xc;
                    xc = Math.abs((yc % World.VIEW_SIZE) % World.VIEW_SIZE);
                    yc = Math.abs(xTemp-xc+yc);
                }
                if(xc > World.VIEW_SIZE) {
                    yTemp = yc;
                    yc = World.VIEW_SIZE - (xc % World.VIEW_SIZE);
                    xc = xc - (yc - yTemp);
                }
                world.changed();
                break;
            }
            case EAST:{
                xc = (xc + steps % World.VIEW_SIZE) % World.VIEW_SIZE;
                world.changed();
                break;
            }
            case SOUTHEAST:{
                xc += steps;
                yc += steps;
                if(xc > World.VIEW_SIZE) {
                    yTemp = yc;
                    yc = (xc % World.VIEW_SIZE) % World.VIEW_SIZE;
                    xc = xc - yTemp + yc;
                }
                if(yc > World.VIEW_SIZE) {
                    xTemp = xc;
                    xc = (yc % World.VIEW_SIZE) % World.VIEW_SIZE;
                    yc = yc - xTemp + xc;
                }
                world.changed();
                break;
            }
            case SOUTH:{
                yc = (yc + steps % World.VIEW_SIZE) % World.VIEW_SIZE;
                world.changed();
                break;
            }
            case SOUTHWEST:{
                xc -= steps;
                yc += steps;
                if(yc > World.VIEW_SIZE) {
                    xTemp = xc;
                    xc = World.VIEW_SIZE - ((yc % World.VIEW_SIZE) % World.VIEW_SIZE);
                    yc = yc-(xc-xTemp);
                }
                if(xc < 0) {
                    yTemp = yc;
                    yc = Math.abs(xc);
                    xc = xc + (yTemp - yc);
                }
                world.changed();
                break;
            }
            case WEST:{
                xc = (xc - steps % World.VIEW_SIZE + World.VIEW_SIZE) % World.VIEW_SIZE;
                world.changed();
                break;
            }
            case NORTHWEST:{
                yc -= steps;
                xc -= steps;
                if(xc < 0) {
                    yTemp = yc;
                    yc = World.VIEW_SIZE + xc;
                    xc = xc - (yTemp - yc);
                }
                if(yc < 0) {
                    xTemp = xc;
                    xc = World.VIEW_SIZE - yc;
                    yc = yc + (xc - xTemp);
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
