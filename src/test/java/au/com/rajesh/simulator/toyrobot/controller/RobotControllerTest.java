package au.com.rajesh.simulator.toyrobot.controller;

import au.com.rajesh.simulator.toyrobot.domain.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pedamaler on 25/06/2014.
 */
public class RobotControllerTest {
    private RobotController robotController;
    @Before
    public void setUp(){
        robotController = new RobotController();
    }

    @Test
    public void createNewRobotController(){
        Assert.assertNotNull("Robot Controller should have been created", robotController);
    }

    @Test
    public void shouldPlaceTheRobot(){
        String command = "PLACE 1,2,NORTH";
        Boolean result = robotController.executeCommand(command);
        Assert.assertTrue("Command should have completed successfully", result);
    }

    @Test
    public void shouldNotPlaceOutOfBoundaryRobot(){
        String command = "PLACE 10,2,NORTH";
        Boolean result = robotController.executeCommand(command);
        Assert.assertFalse("Command NOT should have completed successfully", result);
    }

    @Test
    public void shouldNotPlaceTheRobotInInvalidDirection(){
        String command = "PLACE 1,2,NORTHPOLE";
        Boolean result = robotController.executeCommand(command);
        Assert.assertFalse("Command should NOT have completed successfully", result);
    }

    @Test
    public void shouldGenerateReportWithValidPositionAndDirection(){
        String command = "PLACE 1,2,NORTH";
        Boolean result = robotController.executeCommand(command);
        command = "REPORT";
        Assert.assertTrue("Report should not be null", robotController.executeCommand(command));
    }

    @Test
    public void shouldSuccessfullyTurnLeft(){
        String command = "PLACE 1,2,NORTH";
        Boolean result = robotController.executeCommand(command);
        command = "LEFT";
        Assert.assertTrue("Command should be successfully executed", robotController.executeCommand(command));
    }

    @Test
    public void shouldSuccessfullyTurnRight(){
        String command = "PLACE 1,2,NORTH";
        Boolean result = robotController.executeCommand(command);
        command = "RIGHT";
        Assert.assertTrue("Command should be successfully executed", robotController.executeCommand(command));
    }

    @Test
    public void shouldNotGenerateReportWithPositionAndDirectionNotSet(){
        String command = "REPORT";
        Boolean result = robotController.executeCommand(command);
        Assert.assertFalse("Command should NOT have completed successfully", result);
    }
}
