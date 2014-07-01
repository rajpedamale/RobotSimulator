package au.com.rajesh.simulator.toyrobot.controller;

import au.com.rajesh.simulator.toyrobot.domain.Direction;
import au.com.rajesh.simulator.toyrobot.domain.Position;
import au.com.rajesh.simulator.toyrobot.domain.Robot;
import au.com.rajesh.simulator.toyrobot.exception.IllegalPositionException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by pedamaler on 25/06/2014.
 */
public class RobotController {

    public static enum COMMAND { // defines both Command and the pattern expected of data as a regex
        PLACE ("^PLACE (\\d+),(\\d+),(\\p{Alpha}+)$"),
        MOVE ("^MOVE$"),
        LEFT ("^LEFT$"),
        RIGHT ("^RIGHT$"),
        REPORT("^REPORT$");

        private final String patternString;

        COMMAND(String patternString){
            this.patternString = patternString;
        }

        public String getPatternString(){
            return patternString;
        }
    }

    private static Map<COMMAND, Pattern> commandsPatternEnumMap = new EnumMap<>(COMMAND.class);

    static {
        // do the setup once per class loading
        Arrays.asList(COMMAND.values()).forEach(command ->
                        commandsPatternEnumMap.put(command, Pattern.compile(command.getPatternString(), Pattern.CASE_INSENSITIVE))
        );
    }

    // injectable properties
    private Boolean debugEnabled = Boolean.FALSE;

    public RobotController(){
        debugEnabled = "true".equalsIgnoreCase(System.getProperty("debug"));
    }

    private Robot robot = new Robot(); // 1 Robot per controller

    public Boolean executeCommand(String commandString){
        Boolean returnValue = Boolean.FALSE;
        String trimmedCommandString = commandString.trim();
        COMMAND command = identifyCommand(trimmedCommandString);

        if (command == null) {
            if (debugEnabled)
                System.out.println("Command not recognized");
        } else {
            switch (command) {
                case PLACE:
                    Pattern placePattern = commandsPatternEnumMap.get(COMMAND.PLACE);
                    Matcher placeMatcher = placePattern.matcher(commandString.trim());
                    placeMatcher.matches(); // This step is only for forcing the regex execution, and hence capture the groups.
                                            // We already know that the match will be successful.
                    Integer x = new Integer(placeMatcher.group(1));
                    Integer y = new Integer(placeMatcher.group(2));
                    try {
                        Position position = new Position(x,y);
                        Direction direction ;
                        try {
                            direction = Direction.valueOf(placeMatcher.group(3).toUpperCase(Locale.ENGLISH));
                        } catch (IllegalArgumentException e) {
                            throw new IllegalPositionException("provided direction is not known");
                        }
                        robot.placeTheRobotAt(position,direction);
                        if (debugEnabled)
                            System.out.println("Placed the Robot successfully");
                        returnValue = Boolean.TRUE;
                    } catch (IllegalPositionException e) {
                        if (debugEnabled)
                            System.out.println("Cannot place Robot as " + e.getLocalizedMessage());
                    }
                    break;
                case MOVE:
                    try {
                        returnValue = robot.move();
                    } catch (IllegalStateException e) {
                        if (debugEnabled)
                            System.out.println("Cannot move Robot as " + e.getLocalizedMessage());
                    }
                    break;
                case LEFT:
                    try {
                        returnValue = robot.turnLeft();
                    } catch (IllegalStateException e) {
                        if (debugEnabled)
                            System.out.println("Cannot turn Robot left as " + e.getLocalizedMessage());
                    }
                    break;
                case RIGHT:
                    try {
                        returnValue = robot.turnRight();
                    } catch (IllegalStateException e) {
                        if (debugEnabled)
                            System.out.println("Cannot turn Robot right as " + e.getLocalizedMessage());
                    }
                    break;
                case REPORT:
                    try {
                        System.out.println(generateReport());
                        returnValue = Boolean.TRUE;
                    } catch (IllegalStateException e) {
                        if (debugEnabled)
                            System.out.println("Cannot generate report as " + e.getLocalizedMessage());
                    }
                    break;
            }
        }
        return returnValue;
    }

    private COMMAND identifyCommand(String commandString){
        List<COMMAND> filteredList =
                Arrays.asList(COMMAND.values())
                        .stream()
                        .filter(command ->
                            commandsPatternEnumMap
                                    .get(command)
                                    .matcher(commandString)
                                    .matches()
                        ).collect(Collectors.toList());

        if (filteredList.size() == 1) {
                return filteredList.get(0);
        }
        // ignore all other scenarios
        return null;
    }

    private String generateReport(){
        if (!robot.isValidState()){
            throw new IllegalStateException("Robot has not been placed yet!");
        }
        StringBuilder report = new StringBuilder("Output: ");
        report.append(robot.getPosition().getX());
        report.append(",");
        report.append(robot.getPosition().getY());
        report.append(",");
        report.append(robot.getDirection());
        return report.toString();
    }
}
