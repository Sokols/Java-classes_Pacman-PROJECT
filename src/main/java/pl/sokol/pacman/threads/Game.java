package pl.sokol.pacman.threads;

import pl.sokol.pacman.gui.levels.Level;
import pl.sokol.pacman.gui.objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final String TITLE = "Pac-Man";

    public static Player player;
    public static Level level;

    private Thread thread;
    private boolean isRunningFlag = false;

    public Game() {
        // set size of the game window
        // GameFrame is not resizable!
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        player = new Player(WIDTH / 2, HEIGHT / 2);
        level = new Level("/maps/map1.png");
    }

    @Override
    public void run() {

        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        double ns = 1e9 / targetTick;
        long now;

        while (isRunningFlag) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                timer();
                render();
                fps++;
                delta--;
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println(fps);
                fps = 0;
                timer += 1000;
            }
        }
        // stop the game if isRunningFlag is false
        stop();
    }

    public synchronized void start() {
        if (!isRunningFlag) {
            isRunningFlag = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (isRunningFlag) {
            isRunningFlag = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(g);
        level.render(g);
        g.dispose();
        bs.show();
    }

    private void timer() {
        player.timer();
        level.timer();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
                player.setRight(true);
                break;

            case KeyEvent.VK_LEFT:
                player.setLeft(true);
                break;

            case KeyEvent.VK_UP:
                player.setUp(true);
                break;

            case KeyEvent.VK_DOWN:
                player.setDown(true);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
                player.setRight(false);

                break;

            case KeyEvent.VK_LEFT:
                player.setLeft(false);
                break;

            case KeyEvent.VK_UP:
                player.setUp(false);
                break;

            case KeyEvent.VK_DOWN:
                player.setDown(false);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
