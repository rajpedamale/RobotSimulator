package au.com.rajesh.simulator.toyrobot.domain;

/**
 * Created by pedamaler on 24/06/2014.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction next(){
        int sizeOfEnum = Direction.values().length;
        return values()[(ordinal() + 1) % sizeOfEnum];
    }

    public Direction previous(){
        int sizeOfEnum = Direction.values().length;
        return values()[(ordinal() + (sizeOfEnum - 1)) % sizeOfEnum]; // n-1 increments are same as 1 down. Helps with avoiding negative values.
    }

}
