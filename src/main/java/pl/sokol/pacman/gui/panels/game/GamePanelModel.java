package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.engine.EnginePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

public class GamePanelModel {

    private GameFrameController gameFrameController;

    private Level level;

    private boolean isStoppedFlag;
    private boolean isEndedFlag;

    private EnginePanelController enginePanelController;
    private StatsPanelController statsPanelController;

    public GamePanelModel(GameFrameController gameFrameController, Level level, boolean isStoppedFlag, boolean isEndedFlag, EnginePanelController enginePanelController, StatsPanelController statsPanelController) {
        this.gameFrameController = gameFrameController;
        this.level = level;
        this.isStoppedFlag = isStoppedFlag;
        this.isEndedFlag = isEndedFlag;
        this.enginePanelController = enginePanelController;
        this.statsPanelController = statsPanelController;
    }

    public GameFrameController getGameFrameController() {
        return gameFrameController;
    }

    public void setGameFrameController(GameFrameController gameFrameController) {
        this.gameFrameController = gameFrameController;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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

    public EnginePanelController getEnginePanelController() {
        return enginePanelController;
    }

    public void setEnginePanelController(EnginePanelController enginePanelController) {
        this.enginePanelController = enginePanelController;
    }

    public StatsPanelController getStatsPanelController() {
        return statsPanelController;
    }

    public void setStatsPanelController(StatsPanelController statsPanelController) {
        this.statsPanelController = statsPanelController;
    }
}
