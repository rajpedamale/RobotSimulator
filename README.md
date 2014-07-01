RobotSimulator
==============

This is an experimental program that simulates a toy robot.
The program needs Java 1.8 for compilation and runtime
Run the below maven command to create the artififacts
mvn clean install
Then cd into the target directory and start the program.
Enjoy

Usage: java <optional parameters> -jar toyrobot-1.jar
Optional Parameters:
   -Ddebug=true                     // This generates helpful messages about invalid data
   -Dfile=<filename with full path> // This executes the commands from the specified file

Command List:
   PLACE <X>,<Y>,<Direction>
   LEFT
   RIGHT
   MOVE
   REPORT

X and Y can be in the range 0-4
Valid Directions are NORTH, SOUTH, EAST, WEST
All commands are case-insensitive
