package snake;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Grid grid = new Grid(10, 20);
        int [] pos = {0,0}; int [] speed = {0,1};
        Snake s = new Snake(pos, speed, "red");
        grid.dropBlock(8,8);
        int i; int j = 4;
        for(i = 5; i <= 7; i++) {
            grid.dropFood(i,j);
        }
        for (j = 4; j <= 7; j++) {
            grid.dropFood(i,j);
        }
        i = 0;
        grid.updateGrids(s);
        System.out.println(grid.toString());
        while(s.isAlive()) {
            if (i == 3 || i == 20 || i == 22 || i == 24 || i == 26)
                s.turnRight();
            else if (i == 11 || i == 14 || i == 16 || i == 18 || i == 28)
                s.turnLeft();
            s.moveOn(grid);
            grid.updateGrids(s);
            i++;
            System.out.println(grid.toString() + "\n");
            Thread.sleep(1000);
        }
        System.out.println(s.getSize());
    }
}
