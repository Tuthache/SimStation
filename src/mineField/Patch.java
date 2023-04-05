package mineField;

import java.io.Serializable;

public class Patch implements Serializable {
    // Class that handles storing variables for the Minefield tiles

    private boolean isRevealed;
    private boolean hasMine; // change these names if something makes more sense
    private int minesAround;
    private boolean isGoal; // for the goal
    private int x;
    private int y;

    public Patch(int x, int y) {
        super();
        isRevealed = false;
        hasMine = false;
        minesAround = 0;
        this.x = x;
        this.y = y;
        // TODO implement constructor
    }

    public Patch(int x, int y, boolean isGoal) {
        this(x, y);
        this.isGoal = isGoal;
    }

    public void placeMine() {
        hasMine = true;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public boolean isGoal() {return isGoal;}

    public void reveal() {
        isRevealed = true;
        // TODO push an update in the Minefield model that calls this!
    }

    public boolean isRevealed() {
        return isRevealed;
    }
    public void setMinesAround(int n) {minesAround = n;}

    public int getMinesAround() {
        return minesAround;
    }

    public void incrementMinesAround() {
        minesAround++;
    }

    // TODO implement helper methods for init/revealing

    public int getX() { return x; }

    public int getY() { return y; }

}
