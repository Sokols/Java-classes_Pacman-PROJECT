package pl.sokol.pacman.game;

import pl.sokol.pacman.Utils;
import pl.sokol.pacman.elements.dynamic.Player;
import pl.sokol.pacman.gui.GamePanel;

import javax.swing.JPanel;

public class GameThread implements Runnable {

    private final int RENDER_SLEEP_TIME = 25;

    public static Player player;
    public static Level level;

    private Thread gameThread;
    private boolean isStoppedFlag = false;
    private boolean isEndedFlag = false;

    private JPanel gamePanel;

    public GameThread() {
        player = new Player(0, 0, this);
        level = new Level("/maps/map1.png", player, this);
        this.gamePanel = new GamePanel(player, level);
    }

    @Override
    public void run() {
        while (!isEndedFlag) {
            mainRender();
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
        gamePanel.repaint();
        Utils.sleep(RENDER_SLEEP_TIME);
    }

    public void setEndedFlag(boolean endedFlag) {
        isEndedFlag = endedFlag;
    }

    public Level getLevel() {
        return level;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }
}
