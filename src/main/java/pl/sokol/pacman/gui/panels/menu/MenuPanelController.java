package pl.sokol.pacman.gui.panels.menu;

import pl.sokol.pacman.gui.frame.GameFrameController;

public class MenuPanelController {

    private MenuPanelModel model;
    private MenuPanelView view;

    public MenuPanelController(GameFrameController game) {
        this.model = new MenuPanelModel(game);
        this.view = new MenuPanelView();
        initListeners(game);
    }

    private void initListeners(GameFrameController game) {
        view.getNewGameButton().addActionListener(e -> game.newGame());

        view.getExitButton().addActionListener(e -> game.getView().dispose());
    }


    public MenuPanelView getView() {
        return view;
    }
}
