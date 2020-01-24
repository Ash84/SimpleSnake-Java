package snake;

import java.util.Arrays;

public class Grid {

    private String[][] grid;
    private int nRows;
    private int nCols;
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
        StringBuilder retour = new StringBuilder();
        retour.append("+ ".repeat(Math.max(0, nCols + 2)));
        retour.append("\n");
        for (int i=0; i < nRows; i++) {
                retour.append("+ ");
            for (int j=0; j < nCols; j++) {
                retour.append(grid[i][j]).append(" ");
            }
            retour.append("+\n");
        }
        retour.append("+ ".repeat(Math.max(0, nCols + 2)));
        return retour.toString();
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

    public int[][] getIntGrid() {
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

    public void incrIntGrid() {
        for (int i=0; i < nRows; i++) {
            for (int j=0; j < nCols; j++) {
                if (intGrid[i][j] > 0)
                    intGrid[i][j]++;
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

    public void setIntGrid(int[][] intGrid) {
        this.intGrid = intGrid;
    }
}


