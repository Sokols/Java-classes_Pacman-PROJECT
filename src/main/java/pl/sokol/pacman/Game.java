package pl.sokol.pacman;

import pl.sokol.pacman.gui.elements.dynamic.Player;
import pl.sokol.pacman.gui.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final String TITLE = "Pac-Man";

    public static Player player;
    public static Level level;

    private Thread gameThread;
    private boolean isStoppedFlag = false;
    private boolean isEndedFlag = false;


    public Game() {
        // set size of the game window
        // GameFrame is not resizable!
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        player = new Player(0, 0);
        level = new Level("/maps/map1.png", player, this);
    }

    @Override
    public void run() {
        while (!isEndedFlag) {
            //while (!isStoppedFlag && !isEndedFlag) {
                mainRender();
           // }
        }
    }

    public synchronized void startNewGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void resume() {
        if (isStoppedFlag) {
            isStoppedFlag = false;
            try {
                gameThread.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void stop() {
        if (!isStoppedFlag) {
            isStoppedFlag = true;
            try {
                gameThread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void mainRender() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(g);
        level.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                clearKeys();
                player.getMovements().set(0, true);
                break;

            case KeyEvent.VK_RIGHT:
                clearKeys();
                player.getMovements().set(1, true);
                break;

            case KeyEvent.VK_DOWN:
                clearKeys();
                player.getMovements().set(2, true);
                break;

            case KeyEvent.VK_LEFT:
                clearKeys();
                player.getMovements().set(3, true);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // unused
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused
    }

    private void clearKeys() {
        player.getMovements().set(0, false);
        player.getMovements().set(1, false);
        player.getMovements().set(2, false);
        player.getMovements().set(3, false);
    }

    public boolean isStoppedFlag() {
        return isStoppedFlag;
    }

    public void setStoppedFlag(boolean stoppedFlag) {
        isStoppedFlag = stoppedFlag;
    }

    public boolean isEndedFlag() {
        return isEndedFlag;
    }

    public void setEndedFlag(boolean endedFlag) {
        isEndedFlag = endedFlag;
    }
}
