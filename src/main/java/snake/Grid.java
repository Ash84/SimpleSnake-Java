package snake;

import java.util.Arrays;

public class Grid {

    private String[][] grid = null;
    private int nRows = 0;
    private int nCols = 0;
    private int[][] intGrid;

    public Grid(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = new String[nRows][nCols];
        for (int i=0; i < nRows; i++) {
            for (int j=0; j < nCols; j++) {
                grid[i][j] = " ";
            }
        }
        this.intGrid = genIntGrid();
    }

    @Override
    public String toString() {
        String retour = "";
        for (int i=0; i< nCols+2; i++)
            retour = retour + "+ ";
        retour = retour + "\n";
        for (int i=0; i < nRows; i++) {
                retour = retour + "+ ";
            for (int j=0; j < nCols; j++) {
                retour = retour + grid[i][j] + " ";
            }
            retour = retour + "+\n";
        }
        for (int i=0; i< nCols+2; i++)
            retour = retour + "+ ";
        return retour;
    }

    public Boolean pointInsideGrid(int y, int x) {
        return (x >= 0 && x < nCols && y >= 0 && y < nRows);
    }

    public void placeObject(int y, int x, String o) {
        if (pointInsideGrid(y,x))
            grid[y][x] = o;
    }

    public void dropBlock(int y, int x) {
        placeObject(y, x, "#");
    }

    public void dropFood(int y, int x) {
        placeObject(y, x, "o");
    }

    public String getCell(int y, int x) {
        return grid[y][x];
    }

    public String[][] getGrid() {
        return grid;
    }

    public int getNRows() {
        return nRows;
    }

    public int getNCols() {
        return nCols;
    }

    public int[][] genIntGrid() {
        int[][] intGrid = new int[nRows][nCols];
        for (int i=0; i < nRows; i++) {
            for (int j=0; j < nCols; j++) {
                intGrid[i][j] = 0;
            }
        }
        return intGrid;
    }

    public void decrIntGrid() {
        for (int i=0; i < nRows; i++) {
            for (int j=0; j < nCols; j++) {
                if (intGrid[i][j] > 0)
                    intGrid[i][j]--;
            }
        }
    }
    public void updateGrids(Snake snake) {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (getCell(i, j).compareTo("*") == 0)
                    grid[i][j] = " ";
            }
        }
        if (snake.isAlive()) {
            int[] snakePos = snake.getPos();
            intGrid[snakePos[0]][snakePos[1]] = snake.getSize();
        }
        for (int i=0; i < nRows; i++) {
            for (int j=0; j < nCols; j++) {
                if (intGrid[i][j] > 0)
                    grid[i][j] = "*";
            }
        }
        decrIntGrid();
    }

}


