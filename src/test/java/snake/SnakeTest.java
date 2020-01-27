package snake;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    Snake s;
    int[] pos = {4, 4};
    int[] speed = {0, 1};
    String color = "red";
    String player = "Player1";

    @BeforeEach
    void setUp() {
        s = new Snake(pos, speed, color, player);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isAlive() {
        assertEquals(true, s.isAlive());
    }

    @Test
    void killSnake() {
        s.killSnake();
        assertEquals(false, s.isAlive());
    }

    @Test
    void getNextPosition() {
        int[] next = {4, 5};
        assertEquals(next[0] , s.getNextPosition()[0]);
        assertEquals(next[1] , s.getNextPosition()[1]);
    }

    @Test
    void turnRight() {
        s.turnRight();
        int[] next = {5, 4};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnRight2() {
        s.turnRight();
        s.turnRight();
        int[] next = {4, 3};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnRight3() {
        s.turnRight();
        s.turnRight();
        s.turnRight();
        int[] next = {3, 4};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnRight4() {
        s.turnRight();
        s.turnRight();
        s.turnRight();
        s.turnRight();
        int[] next = {4, 5};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnLeft() {
        s.turnLeft();
        int[] next = {3, 4};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnLeft2() {
        s.turnLeft();
        s.turnLeft();
        int[] next = {4, 3};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnLeft3() {
        s.turnLeft();
        s.turnLeft();
        s.turnLeft();
        int[] next = {5, 4};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void turnLeft4() {
        s.turnLeft();
        s.turnLeft();
        s.turnLeft();
        s.turnLeft();
        int[] next = {4, 5};
        assertEquals(next[0], s.getNextPosition()[0]);
        assertEquals(next[1], s.getNextPosition()[1]);
    }

    @Test
    void moveOn() {
        Grid g = new Grid(2, 4);
        int[] pos = {0, 0};
        int[] speed = {0, 1};
        String color = "red";
        String player = "Player1";
        Snake s = new Snake(pos, speed, color, player);
        s.moveOn(g);
        g.updateGrids(s);
        assertEquals(  "+ + + + + + \n" +
                                "+   *     +\n" +
                                "+         +\n" +
                                "+ + + + + + ", g.toString());
        s.moveOn(g);
        g.updateGrids(s);
        s.moveOn(g);
        g.updateGrids(s);
        s.moveOn(g);
        g.updateGrids(s);
        s.moveOn(g);
        g.updateGrids(s);
        assertEquals(  "+ + + + + + \n" +
                                "+   *     +\n" +
                                "+         +\n" +
                                "+ + + + + + ", g.toString());
        s.turnLeft();
        s.turnLeft();
        s.moveOn(g);
        g.updateGrids(s);
        s.moveOn(g);
        g.updateGrids(s);
        assertEquals(  "+ + + + + + \n" +
                                "+       * +\n" +
                                "+         +\n" +
                                "+ + + + + + ", g.toString());
        s.turnRight();
        s.moveOn(g);
        g.updateGrids(s);
        assertEquals(  "+ + + + + + \n" +
                                "+         +\n" +
                                "+       * +\n" +
                                "+ + + + + + ", g.toString());
    }
}