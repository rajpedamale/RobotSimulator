package au.com.rajesh.simulator.toyrobot.domain;

import au.com.rajesh.simulator.toyrobot.domain.Direction;
import au.com.rajesh.simulator.toyrobot.domain.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import au.com.rajesh.simulator.toyrobot.domain.Robot;

import static au.com.rajesh.simulator.toyrobot.utils.Constants.*;

/**
 * Tests for {@link Robot}
 * Created by pedamaler on 24/06/2014.
 */
public class RobotTest {
    private Robot robot;
    private Position position;
    private Direction direction;

    @Before
    public void resetValues(){
        robot = new Robot();
        position = new Position(0,0);
        direction = Direction.NORTH;
    }

    @Test
    public void robotShouldBePlacedWithValidArguments(){
        robot.placeTheRobotAt(position, direction);
        Assert.assertEquals("Values of position and direction should be set",
                position.getX() + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test(expected = NullPointerException.class)
    public void robotShouldNotBePlacedWithNullAsPosition(){
        robot.placeTheRobotAt(null, direction);
        Assert.fail("This test should have thrown NullPointerException");
    }

    @Test(expected = NullPointerException.class)
    public void robotShouldNotBePlacedWithNullAsDirection(){
        robot.placeTheRobotAt(position, null);
        Assert.fail("This test should have thrown NullPointerException");
    }

    @Test
    public void shouldTurnRight(){
        robot.placeTheRobotAt(position,direction);
        robot.turnRight();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + position.getY() + "," + direction.next(),
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailTurningRightWhenPositionAndDirectionAreNotSet(){
        robot.turnRight();
        Assert.fail("This test should have thrown IllegalStateException");
    }

    @Test
    public void shouldTurnLeft(){
        robot.placeTheRobotAt(position,direction);
        robot.turnLeft();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + position.getY() + "," + direction.previous(),
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailTurningLeftWhenPositionAndDirectionAreNotSet(){
        robot.turnLeft();
        Assert.fail("This test should have thrown IllegalStateException");
    }

    @Test
    public void shouldMoveUp(){
        position = new Position(2,2);
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + (position.getY() + 1) + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldMoveDown(){
        position = new Position(2,2);
        direction = Direction.SOUTH;
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + (position.getY() - 1) + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldMoveRight(){
        position = new Position(2,2);
        direction = Direction.EAST;
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                (position.getX() + 1) + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldMoveLeft(){
        position = new Position(2,2);
        direction = Direction.WEST;
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                (position.getX() - 1 ) + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldNotMoveUpBeyondTheBoundary(){
        position = new Position(2, VERTICAL_MAXIMUM);
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldNotMoveDownBeyondTheBoundary(){
        position = new Position(2, VERTICAL_MINIMUM);
        direction = Direction.SOUTH;
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldNotMoveRightBeyondTheBoundary(){
        position = new Position(HORIZONTAL_MAXIMUM, 2);
        direction = Direction.EAST;
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test
    public void shouldNotMoveLeftBeyondTheBoundary(){
        position = new Position(HORIZONTAL_MINIMUM, 2);
        direction = Direction.WEST;
        robot.placeTheRobotAt(position,direction);
        robot.move();
        Assert.assertEquals("Values of position should appear in report",
                position.getX() + "," + position.getY() + "," + direction,
                robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getDirection());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailToMoveWhenPositionAndDirectionAreNotSet(){
        robot.move();
        Assert.fail("This test should have thrown IllegalStateException");
    }
}
