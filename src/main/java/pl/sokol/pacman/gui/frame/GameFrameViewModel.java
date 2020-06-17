package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.Utils;
import pl.sokol.pacman.elements.dynamic.Player;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.panels.game.GamePanelViewModel;
import pl.sokol.pacman.gui.panels.stats.StatsPanelViewModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

public class GameFrameViewModel implements Runnable, KeyListener {

    private final int RENDER_SLEEP_TIME = 25;

    private GameFrameView view;
    private GameFrameModel model;

    public GameFrameViewModel() {
        Player player = new Player(0, 0, this);
        this.model = new GameFrameModel(
                player,
                new Level("/maps/map1.png", player),
                false,
                false,
                new GamePanelViewModel(this),
                new StatsPanelViewModel(this));

        this.view = new GameFrameView(
                this.model.getGamePanelViewModel().getView(),
                this.model.getStatsPanelViewModel().getView());

        view.addKeyListener(this);
    }

    @Override
    public void run() {
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        double ns = 1e9 / targetTick;

        while (!model.isEndedFlag()) {
            while (!model.isStoppedFlag()) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    mainRender();
                    fps++;
                    delta--;
                }
                if (System.currentTimeMillis() - timer >= 1000) {
                    System.out.println(fps);
                    fps = 0;
                    timer += 1000;
                }
            }
        }
    }

    private void mainRender() {
        BufferStrategy bs = view.getBufferStrategy();
        if (bs == null) {
            view.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        model.getGamePanelViewModel().renderGame(g);
        model.getStatsPanelViewModel().renderStats(g);
        bs.show();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // switch by pressed key
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                model.getPlayer().setCurrentMovement(0);
                break;

            case KeyEvent.VK_RIGHT:
                model.getPlayer().setCurrentMovement(1);
                break;

            case KeyEvent.VK_DOWN:
                model.getPlayer().setCurrentMovement(2);
                break;

            case KeyEvent.VK_LEFT:
                model.getPlayer().setCurrentMovement(3);
                break;

            case KeyEvent.VK_SPACE:
                if (model.isStoppedFlag()) {
                    resume();
                } else {
                    stop();
                }
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

    public void startNewGame() {
        // hmmm
    }

    public void resume() {
        model.setStoppedFlag(false);
    }

    public void stop() {
        model.setStoppedFlag(true);
    }

    public GameFrameModel getModel() {
        return model;
    }
}
