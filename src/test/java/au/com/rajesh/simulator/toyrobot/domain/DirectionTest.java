package au.com.rajesh.simulator.toyrobot.domain;

import au.com.rajesh.simulator.toyrobot.domain.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Direction}
 * Created by pedamaler on 24/06/2014.
 */
public class DirectionTest {
    private Direction direction;

    @Before
    public void setUp(){
        direction = Direction.NORTH;
    }

    @Test
    public void testNext(){
        Assert.assertEquals("Expected East", Direction.EAST, direction.next());
    }

    @Test
    public void testNextForWest(){ // Testing the boundary condition
        direction = Direction.WEST;
        Assert.assertEquals("Expected North", Direction.NORTH, direction.next());
    }

    @Test
    public void testPrevious(){
        direction = Direction.SOUTH; // non-boundary condition
        Assert.assertEquals("Expected East", Direction.EAST, direction.previous());
    }

    @Test
    public void testPreviousForNorth(){ // Testing the boundary condition
        direction = Direction.NORTH;
        Assert.assertEquals("Expected West", Direction.WEST, direction.previous());
    }
}
