package test;

import main.MarsRover;
import main.Rover;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RoverMove {

    private Rover rover;
    private int[][] maps = {{0,0,0},
                            {0,1,0},
                            {0,0,0}};

    @Before
    public void initialise(){
        rover = new Rover("N",0,0,maps);
    }

    //Test for the moves right/left
    @Test
    public void testTurnRight(){
        rover.turn('R');
        assertThat(rover.getDirection(),is("E"));
        rover.turn('R');
        assertThat(rover.getDirection(),is("S"));
        rover.turn('r');
        assertThat(rover.getDirection(),is("W"));
        rover.turn('K');
        assertThat(rover.getDirection(),is("W"));
    }


    @Test
    public void testTurnLeft(){
        rover.setDirection("N");
        rover.turn('L');
        assertThat(rover.getDirection(),is("W"));
        rover.turn('L');
        assertThat(rover.getDirection(),is("S"));
        rover.turn('l');
        assertThat(rover.getDirection(),is("E"));
        rover.turn('K');
        assertThat(rover.getDirection(),is("E"));
    }

    //test for moves forward/back
    @Test
    public void testForward(){
        rover.setDirection("N");
        rover.setY(0);
        rover.execute('F');
        assertThat(rover.getY(),is(1));
        rover.execute('F');
        assertThat(rover.getY(),is(2));
        rover.execute('f');
        assertThat(rover.getY(),is(3));
        rover.execute('f');
        assertThat(rover.getY(),is(0));
        rover.execute('%');
        assertThat(rover.getY(),is(0));
    }

    @Test
    public void testBack(){
        rover.setDirection("N");
        rover.setY(0);
        rover.execute('B');
        assertThat(rover.getY(),is(3));
        rover.execute('B');
        assertThat(rover.getY(),is(2));
        rover.execute('b');
        assertThat(rover.getY(),is(1));
        rover.execute('b');
        assertThat(rover.getY(),is(0));
        rover.execute('b');
        assertThat(rover.getY(),is(3));
        rover.execute('%');
        assertThat(rover.getY(),is(3));
    }

    //Test for validate commands
    @Test
    public void testCommands(){
        assertThat(MarsRover.isCorrectCommands("FFFF"),is(true));
        assertThat(MarsRover.isCorrectCommands("FffL"),is(true));
        assertThat(MarsRover.isCorrectCommands("FffLBBLRlr"),is(true));
        assertThat(MarsRover.isCorrectCommands("FffLbbb"),is(true));
        assertThat(MarsRover.isCorrectCommands("1231"),is(false));
        assertThat(MarsRover.isCorrectCommands(null),is(false));
    }

    //Test for test roadblocks
    @Test
    public void testRoadBlocks(){
        assertThat(rover.isRoadBlock(1,1,maps),is(true));
        assertThat(rover.isRoadBlock(1,2,maps),is(false));
        rover.setY(0); rover.setX(1); rover.setDirection("N"); // Rover cant advance becouse exist a roadblock
        rover.execute('F');
        assertThat(rover.getY(),is(0));
        rover.execute('F');
        assertThat(rover.getY(),is(0));
        rover.execute('b');
        assertThat(rover.getY(),is(3));

    }

}
