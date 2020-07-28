package pl.sokol.pacman.gui.panels.menu;

import lombok.Getter;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

@Getter
public class MenuPanelController {

    private MenuPanelView menuPanelView;

    public MenuPanelController(GameFrameController game) {
        this.menuPanelView = new MenuPanelView();
        initListeners(game);
    }

    private void initListeners(GameFrameController game) {
        menuPanelView.getBackToGameButton().addActionListener(e -> game.backToGame());

        menuPanelView.getNewGameButton().addActionListener(e -> game.newGame(new GamePanelController(game, new StatsPanelController())));

        menuPanelView.getSaveButton().addActionListener(e -> game.saveGame());

        menuPanelView.getLoadButton().addActionListener(e -> game.loadGame());

        menuPanelView.getRankingButton().addActionListener(e -> game.showRanking());

        menuPanelView.getExitButton().addActionListener(e -> {
            game.getView().dispose();
            System.exit(0);
        });
    }
}
