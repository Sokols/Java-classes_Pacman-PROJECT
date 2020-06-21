package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.frames.game.GameFrameController;
import pl.sokol.pacman.gui.panels.stats.StatsPanelController;

public class GamePanelModel {

    private GameFrameController gameThread;
    private StatsPanelController statsPanelController;

    public GamePanelModel(GameFrameController gameThread, StatsPanelController statsPanelController) {
        this.gameThread = gameThread;
        this.statsPanelController = statsPanelController;
    }

    public GameFrameController getGameThread() {
        return gameThread;
    }

    public void setGameThread(GameFrameController gameThread) {
        this.gameThread = gameThread;
    }

    public StatsPanelController getStatsPanelController() {
        return statsPanelController;
    }

    public void setStatsPanelController(StatsPanelController statsPanelController) {
        this.statsPanelController = statsPanelController;
    }
}
