package pl.sokol.pacman.gui.frames.game;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.stats.StatsPanelController;

public class GameFrameModel {

    private Level level;

    private boolean isStoppedFlag;
    private boolean isEndedFlag;

    private GamePanelController gamePanelController;
    private StatsPanelController statsPanelController;

    public GameFrameModel(Level level, boolean isStoppedFlag, boolean isEndedFlag, GamePanelController gamePanelController, StatsPanelController statsPanelController) {
        this.level = level;
        this.isStoppedFlag = isStoppedFlag;
        this.isEndedFlag = isEndedFlag;
        this.gamePanelController = gamePanelController;
        this.statsPanelController = statsPanelController;
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

    public GamePanelController getGamePanelController() {
        return gamePanelController;
    }

    public void setGamePanelController(GamePanelController gamePanelController) {
        this.gamePanelController = gamePanelController;
    }

    public StatsPanelController getStatsPanelController() {
        return statsPanelController;
    }

    public void setStatsPanelController(StatsPanelController statsPanelController) {
        this.statsPanelController = statsPanelController;
    }
}
