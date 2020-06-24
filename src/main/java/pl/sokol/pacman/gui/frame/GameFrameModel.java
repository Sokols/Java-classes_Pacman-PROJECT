package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.loading.LoadingPanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;

public class GameFrameModel {

    private GamePanelController gamePanel;
    private MenuPanelController menuPanel;
    private LoadingPanelController loadingPanel;

    public GameFrameModel(GamePanelController gamePanel, MenuPanelController menuPanel) {
        this.gamePanel = gamePanel;
        this.menuPanel = menuPanel;
    }

    public GamePanelController getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanelController gamePanel) {
        this.gamePanel = gamePanel;
    }

    public MenuPanelController getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(MenuPanelController menuPanel) {
        this.menuPanel = menuPanel;
    }

    public LoadingPanelController getLoadingPanel() {
        return loadingPanel;
    }

    public void setLoadingPanel(LoadingPanelController loadingPanel) {
        this.loadingPanel = loadingPanel;
    }
}
