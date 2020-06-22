package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.game.RenderTimer;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.engine.EnginePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GamePanelController implements Runnable, KeyListener {

    private GamePanelModel model;
    private GamePanelView view;

    private RenderTimer renderTimer;

    public GamePanelController(GameFrameController gameFrameController) {
        StatsPanelController statsPanelController = new StatsPanelController();
        this.model = new GamePanelModel(
                gameFrameController,
                new Level("/maps/map1.png", this, statsPanelController),
                false,
                false,
                new EnginePanelController(this, statsPanelController),
                statsPanelController
        );

        this.view = new GamePanelView(
                this.model.getEnginePanelController().getView(),
                this.model.getStatsPanelController().getView()
        );

        this.renderTimer = new RenderTimer(
                0,
                System.currentTimeMillis(),
                System.nanoTime(),
                System.nanoTime(),
                0
        );
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

    public void restartLevel() {
        for (int i = 0; i < model.getLevel().getEnemies().size(); i++) {
            model.getLevel().getEnemies().get(i).setLocation(model.getLevel().getEnemiesPoint().get(i % model.getLevel().getEnemiesPoint().size()));
            model.getLevel().getEnemies().get(i).setCurrentMovement(0);
        }
        model.getLevel().getPlayer().setLocation(32, 64);
        model.getLevel().getPlayer().setCurrentMovement(0);
    }

    private void mainRender() {
        BufferStrategy bs = model.getGameFrameController().getView().getBufferStrategy();
        if (bs == null) {
            model.getGameFrameController().getView().createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        model.getEnginePanelController().renderGame(g);
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

            case KeyEvent.VK_ENTER:
                model.getLevel().addEnemy();
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

    public GamePanelModel getModel() {
        return model;
    }

    public GamePanelView getView() {
        return view;
    }
}
