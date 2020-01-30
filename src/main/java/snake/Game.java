package snake;

import org.json.simple.JSONObject;

import java.awt.event.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;


public class Game extends JFrame implements KeyListener {

    int nRows;
    int nCols;
    Grid g;
    Snake s;

    public Game(String name, int nRows, int nCols) {
        super(name);
        addKeyListener(this);
        this.nRows = nRows;
        this.nCols = nCols;

        this.g = new Grid(nRows, nCols);
        int[] pos = {4, 4};
        int[] speed = {1, 0};
        this.s = new Snake(pos, speed, "red", name);
        g.dropBlock(6, 4);
        pack();
        setResizable(false);
        setVisible(true);

        Timer timer = new Timer(1000, null);
        GridActionListener listener = new GridActionListener(
                this, g, s, timer
        );
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
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            s.turnRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            s.turnLeft();
        }
    }

    public static void main(String[] args) {
        // Recover command line arguments for the size of
        // the grid to generate
        int nRows = Integer.parseInt(args[0]);
        int nCols = Integer.parseInt(args[1]);

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
        s.moveOn(g);
        g.updateGrids(s);
        System.out.println(g);
        this.i++;
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