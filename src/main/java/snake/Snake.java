package snake;

public class Snake {

    private int[] pos;
    private int[] speed;
    private String color;
    private Boolean alive = true;
    private int size = 1;
    private String player;

    public Snake(int[] pos, int[] speed, String color, String player) {
        this.pos = pos;
        this.speed = speed;
        this.color = color;
        this.player = player;
    }

    public Boolean isAlive() {
        return alive;
    }

    public void killSnake() {
        this.alive = false;
    }

    public String getPlayer() {
        return player;
    }

    public int[] getNextPosition() {
        return new int[]{pos[0] + speed[0], pos[1] + speed[1]};
    }

    public void turnRight() {
        if (speed[0] == 0 && speed[1] == 1) {
            speed[0] = 1;
            speed[1] = 0;
        }
        else if (speed[0] == -1 && speed[1] == 0) {
            speed[0] = 0;
            speed[1] = 1;
        }
        else if (speed[0] == 0 && speed[1] == -1) {
            speed[0] = -1;
            speed[1] = 0;
        }
        else if (speed[0] == 1 && speed[1] == 0) {
            speed[0] = 0;
            speed[1] = -1;
        }
    }

    public void turnLeft() {
        if (speed[0] == 0 && speed[1] == 1) {
            speed[0] = -1;
            speed[1] = 0;
        }
        else if (speed[0] == -1 && speed[1] == 0) {
            speed[0] = 0;
            speed[1] = -1;
        }
        else if (speed[0] == 0 && speed[1] == -1) {
            speed[0] = 1;
            speed[1] = 0;
        }
        else if (speed[0] == 1 && speed[1] == 0) {
            speed[0] = 0;
            speed[1] = 1;
        }
    }

    public void eat(Grid g) {
        this.size++;
        g.incrIntGrid();
    }

    public int[] getPos() {
        return pos;
    }

    public int getSize() {
        return size;
    }

    public void moveOn(Grid grid) {

        // Next position generation, and switch side if needed.

        int[] posBuffer = this.getNextPosition();
        int rows = grid.getNRows(); int cols = grid.getNCols();
        if (!grid.pointInsideGrid(posBuffer[0], posBuffer[1])) {
            if (posBuffer[0] >= rows)
                posBuffer[0] = posBuffer[0] - rows;
            else if (posBuffer[1] >= cols)
                posBuffer[1] = posBuffer[1] - cols;
            else if (posBuffer[0] < 0)
                posBuffer[0] = posBuffer[0] + rows;
            else if (posBuffer[1] < 0)
                posBuffer[1] = posBuffer[1] + cols;
        }

        // Process next position.

        if(grid.getCell(posBuffer[0],posBuffer[1]).compareTo("#") == 0)
            killSnake();
        else if(grid.getCell(posBuffer[0],posBuffer[1]).compareTo("*") == 0)
            killSnake();
        else if(grid.getCell(posBuffer[0],posBuffer[1]).compareTo("o") == 0)
            eat(grid);

        this.pos = posBuffer;
    }


}
