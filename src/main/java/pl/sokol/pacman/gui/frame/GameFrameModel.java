package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.loading.LoadingPanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;
import pl.sokol.pacman.gui.panels.ranking.RankingPanelController;

class GameFrameModel {

    private GamePanelController gamePanel;
    private MenuPanelController menuPanel;
    private LoadingPanelController loadingPanel;
    private RankingPanelController rankingPanel;

    GameFrameModel(GamePanelController gamePanel, MenuPanelController menuPanel) {
        this.gamePanel = gamePanel;
        this.menuPanel = menuPanel;
    }

    GamePanelController getGamePanel() {
        return gamePanel;
    }

    void setGamePanel(GamePanelController gamePanel) {
        this.gamePanel = gamePanel;
    }

    MenuPanelController getMenuPanel() {
        return menuPanel;
    }

    LoadingPanelController getLoadingPanel() {
        return loadingPanel;
    }

    void setLoadingPanel(LoadingPanelController loadingPanel) {
        this.loadingPanel = loadingPanel;
    }

    public RankingPanelController getRankingPanel() {
        return rankingPanel;
    }

    public void setRankingPanel(RankingPanelController rankingPanel) {
        this.rankingPanel = rankingPanel;
    }
}
