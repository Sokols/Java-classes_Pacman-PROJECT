package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;

import static pl.sokol.pacman.Utils.GAME;

public class GameFrameController {

    private GameFrameModel model;
    private GameFrameView view;

    public GameFrameController() {
        this.model = new GameFrameModel(
                new MenuPanelController(this)
        );

        this.view = new GameFrameView(
                model.getMenu().getView()
        );
    }

    public void newGame() {
        model.setGame(new GamePanelController(this));
        view.getMainPanel().add(GAME, model.getGame().getView());
        view.getCard().show(view.getMainPanel(), GAME);
        view.addKeyListener(model.getGame());
        Thread thread = new Thread(model.getGame());
        thread.start();
    }

    public GameFrameView getView() {
        return view;
    }
}
