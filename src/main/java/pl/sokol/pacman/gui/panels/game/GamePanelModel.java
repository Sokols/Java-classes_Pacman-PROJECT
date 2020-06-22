package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.engine.EnginePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

public class GamePanelModel {

    private GameFrameController gameFrame;

    private Level level;

    private boolean isStoppedFlag;
    private boolean isEndedFlag;

    private EnginePanelController enginePanel;
    private StatsPanelController statsPanel;

    public GamePanelModel(GameFrameController gameFrame, Level level, boolean isStoppedFlag, boolean isEndedFlag, EnginePanelController enginePanel, StatsPanelController statsPanel) {
        this.gameFrame = gameFrame;
        this.level = level;
        this.isStoppedFlag = isStoppedFlag;
        this.isEndedFlag = isEndedFlag;
        this.enginePanel = enginePanel;
        this.statsPanel = statsPanel;
    }

    public GameFrameController getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrameController gameFrame) {
        this.gameFrame = gameFrame;
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

    public EnginePanelController getEnginePanel() {
        return enginePanel;
    }

    public void setEnginePanel(EnginePanelController enginePanel) {
        this.enginePanel = enginePanel;
    }

    public StatsPanelController getStatsPanel() {
        return statsPanel;
    }

    public void setStatsPanel(StatsPanelController statsPanel) {
        this.statsPanel = statsPanel;
    }
}
