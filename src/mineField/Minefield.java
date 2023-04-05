package mineField;

import mvc.*;

public class Minefield extends Model {

    public static int percentMined = 5; // default percentage of Patches with mines in them
    final int mineCount;
    Patch[][] field;
    int playerX;
    int playerY;
    boolean isGameOver;
    public final int FIELD_SIZE;


    public Minefield(int fieldSize) {
        this.FIELD_SIZE = fieldSize;
        field = new Patch[fieldSize][fieldSize];
        // initialize field patches
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Patch currentPatch = new Patch(i, j);
                if (i == fieldSize-1 && j == fieldSize-1) {
                    currentPatch = new Patch(i, j, true);
                }

                // rng method that just randomly places mines as they are generated, number of mines is random
                /*
                if (!((i == 0 || i == fieldSize - 1) && (i == j))) { // starting and ending tile check
                    int rng = Utilities.rng.nextInt(100) + 1; // nextInt returns 0 to bound - 1, rng = 1 to bound incl
                    if (percentMined >= rng) { // if rng is 1 to percentMined, place a mine
                        currentPatch.placeMine();
                    }
                }
                */
                field[i][j] = currentPatch;
            }
        }

        // random population method that gives an exact number of mines to place
        // determine probabilistic mine count
        double patchCount = fieldSize * fieldSize;
        double percentDouble = (double)percentMined / 100.0;
        mineCount = (int)Math.floor(patchCount * percentDouble);

        // populate field with mines, excluding top left and bottom right patches (0,0 and fieldSize,fieldSize)
        // TODO potential issue: code can loop forever if there are more mines than patches available to place
        // TODO this code may be inefficient for high numbers of mines, since it just keeps picking randomly until it finds a working spot
        // could happen if mineCount > fieldSize^2 - 2 (2 safe tiles)
        int minesToPlace = mineCount;
        while (minesToPlace > 0) {
            // use Utilities rng to choose random mines
            int x = Utilities.rng.nextInt(fieldSize);
            int y = Utilities.rng.nextInt(fieldSize);
            Patch currentPatch = field[x][y];
            // check if the current patch doesn't have a mine on it, and that it isn't the start/end safe patches
            if (!(currentPatch.hasMine() || isSafePatch(x,y))) {
                currentPatch.placeMine();
                 incrementSurroundingPatches(x,y);
                minesToPlace--;
            }
        }
//        generateAdjacentMines(); // Or use incrementSurroundingPatches(x,y);

        // init player location & gamestate
        playerX = 0;
        playerY = 0;
        isGameOver = false;
        // reveal starting tile
        field[0][0].reveal();
    }

    // default constructor
    public Minefield() {
        this(20);
    }

    private void generateAdjacentMines() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].setMinesAround(findAdjacentMines(i, j));
            }
        }
    }

    public int findAdjacentMines(int x, int y) {
        int result = 0;
        for (int i = x - 1; i < x + 1; i++) {
            for (int j = y - 1; j < y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                try {
                    if (field[i][j].hasMine()) {
                        result++;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return result;
    }

    /* 
    public PatchShape[][] generateShapes() {
        PatchShape[][] result = new PatchShape[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                result[i][j] = new PatchShape(field[i][j], i, j, 20); // TODO: Replace 20 with some calculated val?
            }
        }
        return  result;
    }
    */
    // helper method for determining if the coordinates given are inbounds
    private boolean isInBounds(int x, int y) {
        return (0 <= x && x < FIELD_SIZE) && (0 <= y && y < FIELD_SIZE);
    }

    private boolean isSafePatch(int x, int y) {
        return (x == 0 || x == FIELD_SIZE - 1) && (x == y);
    }

    // Increment all surrounding Patch mineCounts by 1 at an x,y. Called when a mine is placed
    private void incrementSurroundingPatches(int x, int y) {
        if (isInBounds(x, y-1)) field[x][y-1].incrementMinesAround(); // NORTH
        if (isInBounds(x+1, y-1)) field[x+1][y-1].incrementMinesAround(); // NORTHEAST
        if (isInBounds(x+1, y)) field[x+1][y].incrementMinesAround(); // EAST
        if (isInBounds(x+1, y+1)) field[x+1][y+1].incrementMinesAround(); // SOUTHEAST
        if (isInBounds(x, y+1)) field[x][y+1].incrementMinesAround(); // SOUTH
        if (isInBounds(x-1, y+1)) field[x-1][y+1].incrementMinesAround(); // SOUTHWEST
        if (isInBounds(x-1, y)) field[x-1][y].incrementMinesAround(); // WEST
        if (isInBounds(x-1, y-1)) field[x-1][y-1].incrementMinesAround(); // NORTHWEST
    }

    /* Move the player in the given direction. Throws exceptions in the following scenarios:
     * - when the player moves off the grid
     * - when the player steps on a mine
     * - when the player reaches the goal
     * - when the player attempts to move after the game ends
     */
    public void movePlayer(int xChange, int yChange) throws MinefieldException {
        // check if game is over
        if (isGameOver) {
            // don't let the player move
            throw MinefieldException.create(MinefieldExceptionType.GAME_OVER);
        }

        // store new values
        int newX = playerX + xChange;
        int newY = playerY + yChange;

        // check if in bounds
        if (isInBounds(newX, newY)) {
            // movement is in bounds, so do it
            playerX = newX;
            playerY = newY;
            // get stepped on patch
            Patch steppedPatch = field[playerX][playerY];
            steppedPatch.reveal(); // reveal it
            changed(); // lets the system know the model is changed

            // check if player moved onto a mine
            if (steppedPatch.hasMine()) {
                // player lost
                this.isGameOver = true;
                throw MinefieldException.create(MinefieldExceptionType.STEPPED_ON_MINE);
            }
            // check if player won
            if (playerX == FIELD_SIZE - 1 && playerX == playerY) { // manual check for bottom right tile
                // player won
                this.isGameOver = true;
                throw MinefieldException.create(MinefieldExceptionType.WON);
            }
        } else {
            // move is not allowed out of bounds
            this.firePropertyChange("PlayerMoveUnsuccessful", null, null); // TODO consider, may be unnecessary
            throw MinefieldException.create(MinefieldExceptionType.MOVED_OUT_OF_BOUNDS);
        }
    }

    public Patch getPatch(int x, int y) {
        return field[x][y];
    }

    // TODO implement getters/setters and other important methods

    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }

    public Patch[][] getField() {
        return field;
    }

}
