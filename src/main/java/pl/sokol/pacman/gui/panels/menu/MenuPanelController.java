package pl.sokol.pacman.gui.panels.menu;

import pl.sokol.pacman.gui.frame.GameFrameController;

public class MenuPanelController {

    private MenuPanelModel model;
    private MenuPanelView menuPanelView;

    public MenuPanelController(GameFrameController game) {
        this.model = new MenuPanelModel(game);
        this.menuPanelView = new MenuPanelView();
        initListeners(game);
    }

    private void initListeners(GameFrameController game) {
        menuPanelView.getBackToGameButton().addActionListener(e -> game.backToGame());

        menuPanelView.getNewGameButton().addActionListener(e -> game.newGame());

        menuPanelView.getSaveButton().addActionListener(e -> game.saveGame());

        menuPanelView.getExitButton().addActionListener(e -> game.getView().dispose());

    }


    public MenuPanelView getMenuPanelView() {
        return menuPanelView;
    }
}
