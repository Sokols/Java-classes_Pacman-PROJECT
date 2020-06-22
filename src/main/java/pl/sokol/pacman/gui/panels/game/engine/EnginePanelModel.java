package pl.sokol.pacman.gui.panels.game.engine;

import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

public class EnginePanelModel {

    private GamePanelController gameThread;
    private StatsPanelController statsPanelController;

    public EnginePanelModel(GamePanelController gameThread, StatsPanelController statsPanelController) {
        this.gameThread = gameThread;
        this.statsPanelController = statsPanelController;
    }

    public GamePanelController getGameThread() {
        return gameThread;
    }

    public void setGameThread(GamePanelController gameThread) {
        this.gameThread = gameThread;
    }

    public StatsPanelController getStatsPanelController() {
        return statsPanelController;
    }

    public void setStatsPanelController(StatsPanelController statsPanelController) {
        this.statsPanelController = statsPanelController;
    }
}
