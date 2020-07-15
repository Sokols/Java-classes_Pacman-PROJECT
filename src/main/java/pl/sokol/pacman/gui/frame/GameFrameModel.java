package pl.sokol.pacman.gui.frame;

import lombok.Getter;
import lombok.Setter;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.loading.LoadingPanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;
import pl.sokol.pacman.gui.panels.ranking.RankingPanelController;

@Getter
@Setter
class GameFrameModel {

    private GamePanelController gamePanel;
    private MenuPanelController menuPanel;
    private LoadingPanelController loadingPanel;
    private RankingPanelController rankingPanel;

    GameFrameModel(GamePanelController gamePanel, MenuPanelController menuPanel) {
        this.gamePanel = gamePanel;
        this.menuPanel = menuPanel;
    }
}
