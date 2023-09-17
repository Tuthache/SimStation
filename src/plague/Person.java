package plague;

import mvc.*;
import simstation.*;

public class Person extends Agent{
    private static final int RESISTANCE = 5;
    private static final int VIRULENCE = 50;
    private static final int STARTING_INFECTED_RATE = 3;
    private boolean infected;
    public boolean getInfected(){
        return infected;
    }
    public Person(){
        int startingInfected = Utilities.rng.nextInt(10) + 1;
        if (startingInfected < STARTING_INFECTED_RATE){
            infected = true;
        } else {
            infected = false;
        }
        heading = Heading.random();
    }
    public void update(){
        //
        Person neighbor = (Person) world.getNeighbor(this, 10);
        if (neighbor != null){
            //if infection > INFECTION_RATE person does not get infected
            int infection = Utilities.rng.nextInt(100) + 1;
            //During process of getting infected, if resist > RESISTANCE person does not get infected
            int resist = Utilities.rng.nextInt(100) + 1;

            if (neighbor.getInfected() && !this.getInfected()){
                if (infection > VIRULENCE){
                    this.infected = true;
                    if (resist > RESISTANCE){
                        this.infected = false;
                    }
                }
            }
        }
        heading = Heading.random();
        move(5);
    }
}
