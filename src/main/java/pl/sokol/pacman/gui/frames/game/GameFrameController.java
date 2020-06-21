package pl.sokol.pacman.gui.frames.game;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.game.RenderTimer;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.stats.StatsPanelController;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GameFrameController implements Runnable, KeyListener {

    private GameFrameView view;
    private GameFrameModel model;

    private RenderTimer renderTimer;

    public GameFrameController() {
        StatsPanelController statsPanelController = new StatsPanelController(this);
        this.model = new GameFrameModel(
                new Level("/maps/map1.png", this, statsPanelController),
                false,
                false,
                new GamePanelController(this, statsPanelController),
                statsPanelController);

        this.view = new GameFrameView(
                this.model.getGamePanelController().getView(),
                this.model.getStatsPanelController().getView());

        this.renderTimer = new RenderTimer(
                0,
                System.currentTimeMillis(),
                System.nanoTime(),
                System.nanoTime(),
                0);

        view.addKeyListener(this);
    }

    @Override
    public void run() {
        while (!model.isEndedFlag()) {
            renderTimer.startCounting();
            while (renderTimer.getDelta() >= 1) {
                if (!model.isStoppedFlag()) {
                    mainRender();
                }
                renderTimer.updateValues();
            }
            renderTimer.finishCounting();
        }
    }

    public void newGame(StatsPanelController statsPanelController) {
        model = new GameFrameModel(new Level("/maps/map1.png", this, statsPanelController),
                false,
                false,
                new GamePanelController(this, statsPanelController),
                statsPanelController);
    }

    private void mainRender() {
        BufferStrategy bs = view.getBufferStrategy();
        if (bs == null) {
            view.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        model.getGamePanelController().renderGame(g);
        model.getStatsPanelController().renderStats(g);
        bs.show();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // switch by pressed key
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                model.getLevel().getPlayer().setCurrentMovement(0);
                break;

            case KeyEvent.VK_RIGHT:
                model.getLevel().getPlayer().setCurrentMovement(1);
                break;

            case KeyEvent.VK_DOWN:
                model.getLevel().getPlayer().setCurrentMovement(2);
                break;

            case KeyEvent.VK_LEFT:
                model.getLevel().getPlayer().setCurrentMovement(3);
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
