package simstation;

import mvc.Utilities;

public enum Heading {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public static Heading random(){
        int num = Utilities.rng.nextInt(8);
        switch (num){
            case 1: {
                return NORTH;
            }
            case 2: {
                return NORTHWEST;
            }
            case 3: {
                return NORTHEAST;
            }
            case 4: {
                return SOUTH;
            }
            case 5: {
                return SOUTHWEST;
            }
            case 6: {
                return SOUTHEAST;
            }
            case 7: {
                return EAST;
            }
            case 8: {
                return WEST;
            }
        }
        return null;
    }
}
