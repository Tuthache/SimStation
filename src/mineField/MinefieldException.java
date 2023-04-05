package mineField;

public class MinefieldException extends Exception {

    MinefieldExceptionType type;

    public MinefieldException(String message) {
        super(message);
    }

    public static MinefieldException create(MinefieldExceptionType type) {
        String message = "DEFAULT MESSAGE FOR MINEFIELD EXCEPTION";
        switch (type) {
            case MOVED_OUT_OF_BOUNDS: {
                message = "Sargent Rock cannot move out of bounds!";
                break;
            }
            case STEPPED_ON_MINE: {
                message = "Sargent Rock stepped on a mine! Game over!";
                break;
            }
            case WON: {
                message = "Sargent Rock made it to the end! You won!";
                break;
            }
            case GAME_OVER: {
                message = "The game is over, please start a new game";
                break;
            }
        }
        MinefieldException e = new MinefieldException(message);
        e.type = type;
        return e;
    }

    public MinefieldExceptionType getType() {
        return type;
    }

}
