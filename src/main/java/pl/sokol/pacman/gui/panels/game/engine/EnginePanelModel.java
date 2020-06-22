package pl.sokol.pacman.gui.panels.game.engine;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

public class EnginePanelModel {

    private GamePanelController gamePanel;
    private StatsPanelController statsPanel;

    public EnginePanelModel(GamePanelController gamePanel, StatsPanelController statsPanel) {
        this.gamePanel = gamePanel;
        this.statsPanel = statsPanel;
    }

    public GamePanelController getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanelController gamePanel) {
        this.gamePanel = gamePanel;
    }

    public StatsPanelController getStatsPanel() {
        return statsPanel;
    }

    public void setStatsPanel(StatsPanelController statsPanel) {
        this.statsPanel = statsPanel;
    }
}
