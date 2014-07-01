package au.com.rajesh.simulator.toyrobot.domain;

import au.com.rajesh.simulator.toyrobot.exception.IllegalPositionException;

import static au.com.rajesh.simulator.toyrobot.utils.Constants.*;

/**
 * Created by pedamaler on 24/06/2014.
 */
public class Position {
    final private Integer x;

    final private Integer y;

    public Position(Integer x, Integer y){
        if (x == null || y == null){
            throw new NullPointerException("Parameters cannot be null");
        }
        if (x<HORIZONTAL_MINIMUM || x>HORIZONTAL_MAXIMUM || y<VERTICAL_MINIMUM || y>VERTICAL_MAXIMUM){
            throw new IllegalPositionException("Position parameters are outside allowed range.");
        }
        this.x = x;
        this.y = y;
    }

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

}
