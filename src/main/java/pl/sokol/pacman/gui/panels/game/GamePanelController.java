package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.game.RenderTimer;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.engine.EnginePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GamePanelController implements Runnable, KeyListener {

    private GamePanelModel model;
    private GamePanelView view;

    private RenderTimer renderTimer;

    public GamePanelController(GameFrameController gameFrameController) {
        StatsPanelController statsPanelController = new StatsPanelController();

        this.model = new GamePanelModel.Builder()
                .gameFrame(gameFrameController)
                .level(new Level("/graphics/maps/map1.png", this, statsPanelController))
                .isStoppedFlag(false)
                .isEndedFlag(false)
                .enginePanel(new EnginePanelController(this))
                .statsPanel(statsPanelController)
                .isPermissionForNewClick(true)
                .build();

        this.view = new GamePanelView(
                this.model.getEnginePanel().getView(),
                this.model.getStatsPanel().getView()
        );

        this.renderTimer = new RenderTimer.Builder()
                .fps(0)
                .timer(System.currentTimeMillis())
                .lastTime(System.nanoTime())
                .actualTime(System.nanoTime())
                .delta(0)
                .build();
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

    @Override
    public void keyPressed(KeyEvent e) {
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
                if (model.isPermissionForNewClick()) {
                    if (model.isStoppedFlag()) {
                        resume();
                        model.getStatsPanel().getView().getSpaceLabel().setText("PRESS SPACE TO PAUSE");
                    } else {
                        pause();
                        model.getStatsPanel().getView().getSpaceLabel().setText("PRESS SPACE TO RESUME");
                    }
                    model.setPermissionForNewClick(false);
                }
                break;

            case KeyEvent.VK_ESCAPE:
                model.getGameFrame().goToMenu();
                break;

            case KeyEvent.VK_ENTER:
                if (!model.isStoppedFlag() && model.isPermissionForNewClick()) {
                    model.getLevel().addEnemy();
                    model.setPermissionForNewClick(false);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.setPermissionForNewClick(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused
    }

    public void restartLevel() {
        Level restartLevel = model.getLevel();
        for (int i = 0; i < restartLevel.getEnemies().size(); i++) {
            restartLevel.getEnemies().get(i).setLocation(model.getLevel().getEnemiesPoints().get(i % model.getLevel().getEnemiesPoints().size()));
            restartLevel.getEnemies().get(i).setCurrentMovement(0);
        }
        restartLevel.getPlayer().setLocation(32, 64);
        restartLevel.getPlayer().setCurrentMovement(0);
        model.setLevel(restartLevel);
    }

    public void resume() {
        model.setStoppedFlag(false);
    }

    public void pause() {
        model.setStoppedFlag(true);
    }

    public GamePanelModel getModel() {
        return model;
    }

    public GamePanelView getView() {
        return view;
    }

    private void mainRender() {
        BufferStrategy bufferStrategy = model.getGameFrame().getView().getBufferStrategy();
        if (bufferStrategy == null) {
            model.getGameFrame().getView().createBufferStrategy(2);
            bufferStrategy = model.getGameFrame().getView().getBufferStrategy();
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        model.getEnginePanel().renderGame(graphics);
        model.getStatsPanel().renderStats(graphics);
        bufferStrategy.show();
        graphics.dispose();
    }
}