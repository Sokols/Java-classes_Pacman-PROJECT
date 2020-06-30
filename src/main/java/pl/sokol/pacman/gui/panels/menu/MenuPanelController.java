package pl.sokol.pacman.gui.panels.menu;

import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.GamePanelController;

public class MenuPanelController {

    private MenuPanelView menuPanelView;

    public MenuPanelController(GameFrameController game) {
        this.menuPanelView = new MenuPanelView();
        initListeners(game);
    }

    public MenuPanelView getMenuPanelView() {
        return menuPanelView;
    }

    private void initListeners(GameFrameController game) {
        menuPanelView.getBackToGameButton().addActionListener(e -> game.backToGame());

        menuPanelView.getNewGameButton().addActionListener(e -> game.newGame(new GamePanelController(game)));

        menuPanelView.getSaveButton().addActionListener(e -> game.saveGame());

        menuPanelView.getLoadButton().addActionListener(e -> game.loadGame());

        menuPanelView.getExitButton().addActionListener(e -> game.getView().dispose());
    }
}
