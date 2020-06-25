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

    public static final class Builder {
        private GameFrameController gameFrame;
        private Level level;
        private boolean isStoppedFlag;
        private boolean isEndedFlag;
        private EnginePanelController enginePanel;
        private StatsPanelController statsPanel;

        public Builder gameFrame(GameFrameController gameFrame) {
            this.gameFrame = gameFrame;
            return this;
        }

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public Builder isStoppedFlag(boolean isStoppedFlag) {
            this.isStoppedFlag = isStoppedFlag;
            return this;
        }

        public Builder isEndedFlag(boolean isEndedFlag) {
            this.isEndedFlag = isEndedFlag;
            return this;
        }

        public Builder enginePanel(EnginePanelController enginePanel) {
            this.enginePanel = enginePanel;
            return this;
        }

        public Builder statsPanel(StatsPanelController statsPanel) {
            this.statsPanel = statsPanel;
            return this;
        }

        public GamePanelModel build() {
            GamePanelModel model = new GamePanelModel();
            model.gameFrame = this.gameFrame;
            model.level = this.level;
            model.isStoppedFlag = this.isStoppedFlag;
            model.isEndedFlag = this.isEndedFlag;
            model.enginePanel = this.enginePanel;
            model.statsPanel = this.statsPanel;
            return model;
        }
    }

    public GameFrameController getGameFrame() {
        return gameFrame;
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

    public StatsPanelController getStatsPanel() {
        return statsPanel;
    }

    public void setStatsPanel(StatsPanelController statsPanel) {
        this.statsPanel = statsPanel;
    }
}
