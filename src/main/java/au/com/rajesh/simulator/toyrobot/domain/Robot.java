package au.com.rajesh.simulator.toyrobot.domain;

import static au.com.rajesh.simulator.toyrobot.utils.Constants.*;

/**
 * Created by pedamaler on 24/06/2014.
 */
public class Robot {
    private Position position;
    private Direction direction;

    public void placeTheRobotAt(Position position, Direction direction) {
        if (position == null || direction == null){
            throw new NullPointerException("Parameters cannot be null");
        }
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Boolean isValidState(){
        return (position != null && direction != null);
    }

    public Boolean turnRight(){
        if (!isValidState()){
            throw new IllegalStateException("Robot has not been placed yet!");
        }
        direction = direction.next();
        return Boolean.TRUE; // This is redundant, I just prefer the style where the method returns some status
    }

    public Boolean turnLeft(){
        if (!isValidState()){
            throw new IllegalStateException("Robot has not been placed yet!");
        }
        direction = direction.previous();
        return Boolean.TRUE; // This is redundant, I just prefer the style where the method returns some status
    }

    public Boolean move(){
        if (!isValidState()){
            throw new IllegalStateException("Robot has not been placed yet!");
        }

        Boolean resultOfMove = Boolean.FALSE;

        int x = position.getX();
        int y = position.getY();

        switch (direction) {
            case NORTH:
                if (y < VERTICAL_MAXIMUM) {
                    y++;
                    resultOfMove = Boolean.TRUE;
                }
                break;
            case EAST:
                if (x < HORIZONTAL_MAXIMUM) {
                    x++;
                    resultOfMove = Boolean.TRUE;
                }
                break;
            case SOUTH:
                if (y > VERTICAL_MINIMUM) {
                    y--;
                    resultOfMove = Boolean.TRUE;
                }
                break;
            case WEST:
                if (x > HORIZONTAL_MINIMUM) {
                    x--;
                    resultOfMove = Boolean.TRUE;
                }
                break;
        }
        position = new Position(x,y);

        return resultOfMove;
    }
}
