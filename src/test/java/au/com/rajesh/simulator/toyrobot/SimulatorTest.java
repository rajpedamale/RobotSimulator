package au.com.rajesh.simulator.toyrobot;

import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;

/**
 * Tests for {@link Simulator}
 * Created by pedamaler on 24/06/2014.
 */
public class SimulatorTest {
    private String testFile ;
    private Path path;
    private Simulator simulator;

    @Before
    public void setUp(){
        testFile = "src/test/resources/GoodCommands.txt";
        path = Paths.get(testFile);
        simulator = new Simulator();
    }

    @Test
    public void shouldExecuteCommandsFromFile(){
        try {
            Assert.assertTrue("The file should have been processed with the output 0,1,NORTH",
                    simulator.simulateFromFile(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
