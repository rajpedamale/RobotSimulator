package au.com.rajesh.simulator.toyrobot.domain;

import au.com.rajesh.simulator.toyrobot.domain.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static au.com.rajesh.simulator.toyrobot.utils.Constants.*;

/**
 * Tests for {@link Position}
 * Created by pedamaler on 24/06/2014.
 */
public class PositionTest {
    private int x;
    private int y;
    private Position position;

    @Before
    public void resetValues(){
        x = 0;
        y = 0;
        position = null;
    }

    @Test
    public void createPositionWithValidArguments(){
        position = new Position(x,y);

        Assert.assertNotNull("position should be created", position);
    }

    @Test(expected = NullPointerException.class)
    public void doNotCreatePositionWhenXisNull(){
        Position position = new Position(null,y);
    }

    @Test(expected = NullPointerException.class)
    public void doNotCreatePositionWhenYisNull(){
        position = new Position(x,null);
        Assert.fail("This test should have thrown NullPointerException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doNotCreatePositionWhenXisLessThanMinimum(){
        position = new Position(HORIZONTAL_MINIMUM-1,y);
        Assert.fail("This test should have thrown IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doNotCreatePositionWhenXisMoreThanMaximum(){
        position = new Position(HORIZONTAL_MAXIMUM+1,y);
        Assert.fail("This test should have thrown IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doNotCreatePositionWhenYisLessThanMinimum(){
        position = new Position(x,HORIZONTAL_MINIMUM-1);
        Assert.fail("This test should have thrown IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doNotCreatePositionWhenYisMoreThanMaximum(){
        position = new Position(x,HORIZONTAL_MINIMUM-1);
        Assert.fail("This test should have thrown IllegalArgumentException");
    }
}
