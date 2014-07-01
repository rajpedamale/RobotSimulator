package au.com.rajesh.simulator.toyrobot;

import au.com.rajesh.simulator.toyrobot.controller.RobotController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Simulator
{
    private RobotController robotController = new RobotController();
    private Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public static void main( String[] args )
    {
        Simulator simulator = new Simulator();
        Boolean showUsage = Boolean.TRUE;
        String fileName = System.getProperty("file");
        if (fileName != null) {
            Path path = Paths.get(fileName);
            try {
                simulator.simulateFromFile(path);
                showUsage = Boolean.FALSE;
            } catch (IOException e) {
                // ignore exception, as this will take to the default behaviour.
            }
        }
        if (showUsage) {
            displayUsage();

            simulator.simulateFromCommandLine();
        }
    }

    private static void displayUsage() {
        System.out.println( "Usage: java <optional parameters> -jar toyrobot-1.jar" );
        System.out.println( "Optional Parameters: ");
        System.out.println( "   -Ddebug=true                     // This generates helpful messages about invalid data" );
        System.out.println( "   -Dfile=<filename with full path> // This executes the commands from the specified file" );
        System.out.println();
        System.out.println( "Command List:");
        System.out.println( "   PLACE <X>,<Y>,<Direction>");
        System.out.println( "   LEFT");
        System.out.println( "   RIGHT");
        System.out.println( "   MOVE");
        System.out.println( "   REPORT");
        System.out.println();
        System.out.println( "X and Y can be in the range 0-4");
        System.out.println( "Valid Directions are NORTH, SOUTH, EAST, WEST");
        System.out.println( "All commands are case-insensitive");
        System.out.println();
        System.out.println( "Type EXIT to stop the simulator." );
        System.out.println( "Press <enter> to start typing the commands for the robot" );
        System.out.println();
    }

    public Boolean simulateFromFile(Path filePath) throws IOException {
        Files.lines(filePath).forEach(line ->
            robotController.executeCommand(line)
        );
        return Boolean.TRUE;
    }

    public Boolean simulateFromCommandLine() {
        String input = scanner.nextLine();

        while (!"Exit".equalsIgnoreCase(input)) {
            robotController.executeCommand(input.trim());

            input = scanner.nextLine();
        }

        return Boolean.TRUE;
    }
}
