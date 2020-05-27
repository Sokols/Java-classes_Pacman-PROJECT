package pl.sokol.pacman.game;

import pl.sokol.pacman.Utils;
import pl.sokol.pacman.elements.dynamic.Player;
import pl.sokol.pacman.gui.GamePanel;

import javax.swing.JPanel;

public class GameThread implements Runnable {

    public static Level level;

    private final int RENDER_SLEEP_TIME = 25;

    private Player player;

    private Thread gameThread;

    private boolean isStoppedFlag = false;
    private boolean isEndedFlag = false;

    private JPanel gamePanel;

    public GameThread() {
        player = new Player(0, 0, this);
        level = new Level("/maps/map1.png", player, this);
        gamePanel = new GamePanel(this, player, level);
    }

    @Override
    public void run() {
        while (!isEndedFlag) {
            System.out.println("END");
            while (!isStoppedFlag) {
                //System.out.println("STOP");
                mainRender();
            }
        }
    }

    public void startNewGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void resume() {
        isStoppedFlag = false;
    }

    public void stop() {
        isStoppedFlag = true;
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

    public boolean isStoppedFlag() {
        return isStoppedFlag;
    }
}
