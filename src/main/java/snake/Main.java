package snake;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        // Create new game grid and snake.

        Grid grid = new Grid(10, 20);
        int [] pos = {0,0}; int [] speed = {0,1};
        String player = "Player2";
        Snake s = new Snake(pos, speed, "red", player);

        // Test dropping a Block and some Food.

        grid.dropBlock(8,8);
        int i; int j = 4;
        for(i = 5; i <= 7; i++) {
            grid.dropFood(i,j);
        }
        for (j = 4; j <= 7; j++) {
            grid.dropFood(i,j);
        }

        // Start tests on a simple game.

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
            Thread.sleep(1);
        }

        // Game ends, display score.

        ScoreDisplay.scoreDisplay(s,i);
    }
}
