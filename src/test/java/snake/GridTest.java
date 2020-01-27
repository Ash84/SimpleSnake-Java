package snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    Grid g;

    @BeforeEach
    void setUp() {
        g = new Grid(2, 4);
    }

    @Test
    void getGrid() {
        String[][] gE = {{" ", " ", " ", " "},
                {" ", " ", " ", " "}};
        String[][] actual = g.getGrid();
        for (int i = 0; i < gE.length; i++) {
            for (int j = 0; j < gE[0].length; j++) {
                assertEquals(gE[i][j], actual[i][j]);
            }
        }
    }

    @Test
    void testToString() {
        assertEquals("+ + + + + + \n" +
                "+         +\n" +
                "+         +\n" +
                "+ + + + + + ", g.toString());
        Grid h = new Grid(0,0);
        assertEquals("+ + \n" +
                              "+ + ", h.toString());
    }

    @Test
    void pointInsideGrid() {
        assertEquals(false, g.pointInsideGrid(-1, 0));
        assertEquals(false, g.pointInsideGrid(0, -1));
        assertEquals(false, g.pointInsideGrid(3, 3));
        assertEquals(true, g.pointInsideGrid(0, 0));
        assertEquals(true, g.pointInsideGrid(1, 3));
    }

    @Test
    void placeObject() {
        g.placeObject(0, 0, "/");
        assertEquals("+ + + + + + \n" +
                "+ /       +\n" +
                "+         +\n" +
                "+ + + + + + ", g.toString());
        g.placeObject(1, 3, "%");
        assertEquals("+ + + + + + \n" +
                "+ /       +\n" +
                "+       % +\n" +
                "+ + + + + + ", g.toString());
    }

    @Test
    void dropBlock() {
        g.dropBlock(1, 1);
        assertEquals("+ + + + + + \n" +
                "+         +\n" +
                "+   #     +\n" +
                "+ + + + + + ", g.toString());
    }

    @Test
    void dropFood() {
        g.dropFood(1, 2);
        assertEquals("+ + + + + + \n" +
                "+         +\n" +
                "+     o   +\n" +
                "+ + + + + + ", g.toString());

    }

    @Test
    void getNRows() {
        assertEquals(2, g.getNRows());
    }

    @Test
    void getNCols() {
        assertEquals(4, g.getNCols());
    }

    @Test
    void getCell() {
        assertEquals(" ", g.getCell(0, 0));
        g.dropFood(1, 1);
        assertEquals("o", g.getCell(1, 1));
    }

    @Test
    void updateGrids() {
        int[] pos = {0, 0};
        int[] speed = {0, 1};
        String color = "red";
        Snake s = new Snake(pos, speed, color);
        g.updateGrids(s);
        assertEquals(  "+ + + + + + \n" +
                                "+ *       +\n" +
                                "+         +\n" +
                                "+ + + + + + ", g.toString());
    }

    @Test
    void decrIntGrid() {
        int[][] intGrid = {{1, 2, 3, 0},
                           {0, 0, 0, 6}};
        int[][] intGridplusOne = {{0, 1, 2, 0},
                                  {0, 0, 0, 5}};
        g.setIntGrid(intGrid);
        g.decrIntGrid();
        intGrid = g.getIntGrid();
        for (int i = 0; i < g.getNRows(); i++) {
            for (int j = 0; j < g.getNCols(); j++) {
                assertEquals(intGrid[i][j], intGridplusOne[i][j]);
            }
        }
    }

    @Test
    void genIntGrid() {
        int[][] compare = { {0, 0, 0, 0},
                            {0, 0, 0, 0}};
        int [][] compare2 = g.genIntGrid();
        for (int i = 0; i < g.getNCols(); i++) {
            for (int j = 0; j < g.getNRows(); j++) {
                assertEquals(compare[j][i], compare2[j][i]);
            }
        }
    }
}