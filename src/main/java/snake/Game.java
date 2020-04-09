package snake;

import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame implements KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int nRows;
    int nCols;
    Grid g;
    Snake s;
    static boolean turnright;
    static boolean turnleft;
    static Timer timer;

    public Game(String name, int nRows, int nCols) {
        super(name);
        addKeyListener(this);
        this.nRows = nRows;
        this.nCols = nCols;

        this.g = new Grid(nRows, nCols);
        int[] pos = { 2, 1 };
        int[] speed = { 1, 0 };
        this.s = new Snake(pos, speed, "red", name);
        g.dropBlock(6, 4);
        g.dropFood(2, 8);
        g.dropFood(7, 3);
        pack();
        setResizable(false);
        setVisible(true);

        timer = new Timer(1000, null);
        GridActionListener listener = new GridActionListener(this, g, s, timer);
        timer.addActionListener(listener);
        timer.start();
    }

    // This method is where the GUI is built although here it's
    // a very minimalistic one with just a window and nothing inside.
    private static void createAndShowGUI(int nRows, int nCols) {
        // Create and set up the window.
        Game frame = new Game("SnakeThis", nRows, nCols);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Satisfy KeyListener interface constraint
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            turnright = true;
            turnleft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            turnleft = true;
            turnright = false;
        }
    }

    public static void main(String[] args) {
        // Recover command line arguments for the size of
        // the grid to generate
        int nRows = 10;// Integer.parseInt(args[0]);
        int nCols = 20;// Integer.parseInt(args[1]);

        // Schedule app boot in the EDT
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(nRows, nCols);
            }
        });
    }
}

class GridActionListener implements ActionListener {

    Game m;
    Grid g;
    Snake s;
    Timer t;
    static int i = 0;

    public GridActionListener(Game m, Grid g, Snake s, Timer t) {
        this.m = m;
        this.g = g;
        this.s = s;
        this.t = t;
    }

    public void actionPerformed(ActionEvent e) {

        // Game sets the last input for a turn, and sets all to false for next tick.

        if (Game.turnright)
            s.turnRight();
        if (Game.turnleft)
            s.turnLeft();
        Game.turnleft = false;
        Game.turnright = false;
        
        // Tick

        s.moveOn(g);
        g.updateGrids(s);
        System.out.println(g);
        this.i++;

        // If snake dies.
        
        if (!s.isAlive()) {
            try {
                ScoreDisplay.scoreDisplay(s,i);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            m.dispatchEvent(new WindowEvent(m, WindowEvent.WINDOW_CLOSING));
        }
    }
}