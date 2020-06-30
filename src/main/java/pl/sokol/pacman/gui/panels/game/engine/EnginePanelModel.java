package pl.sokol.pacman.gui.panels.game.engine;

import pl.sokol.pacman.gui.panels.game.GamePanelController;

public class EnginePanelModel {

    private GamePanelController gamePanel;

    public EnginePanelModel(GamePanelController gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GamePanelController getGamePanel() {
        return gamePanel;
    }
}
