package snake;

public class Snake {

    private int[] pos = new int[2];
    private int[] speed = new int[2];
    private String color = null;
    private Boolean alive = true;
    private int size = 1;

    public Snake(int[] pos, int[] speed, String color) {
        this.pos = pos;
        this.speed = speed;
        this.color = color;
    }

    public Boolean isAlive() {
        return alive;
    }

    public void killSnake() {
        this.alive = false;
    }

    public int[] getNextPosition() {
        int newy = pos[0] + speed[0];
        int newx = pos[1] + speed[1];
        int[] newpos = {newy, newx};
        return newpos;
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

    public void eat() {
        this.size++;
    }

    public int[] getPos() {
        return pos;
    }

    public int getSize() {
        return size;
    }

    public void moveOn(Grid grid) {
        int[] posBuffer = new int[2];
        posBuffer = this.getNextPosition();
        int rows = grid.getNRows();
        int cols = grid.getNCols();
        if (posBuffer[0] >= grid.getNRows())
            posBuffer[0] = posBuffer[0] - rows;
        if (posBuffer[1] >= grid.getNCols())
            posBuffer[1] = posBuffer[1] - cols;
        if (posBuffer[0] < 0)
            posBuffer[0] = posBuffer[0] + rows;
        if (posBuffer[1] < 0)
            posBuffer[1] = posBuffer[1] + cols;

        if(grid.getCell(posBuffer[0],posBuffer[1]).compareTo("#") == 0)
            killSnake();
        else if(grid.getCell(posBuffer[0],posBuffer[1]).compareTo("*") == 0)
            killSnake();
        else if(grid.getCell(posBuffer[0],posBuffer[1]).compareTo("o") == 0)
            eat();

        this.pos = posBuffer;
    }


}
