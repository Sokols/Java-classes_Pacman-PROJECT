package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;
import pl.sokol.pacman.gui.panels.settings.SettingsPanelController;

public class GameFrameController {

    private GameFrameModel model;
    private GameFrameView view;

    public GameFrameController() {
        this.model = new GameFrameModel(
                new GamePanelController(this),
                new MenuPanelController(),
                new SettingsPanelController()
        );

        this.view = new GameFrameView(
                model.getGame().getView(),
                model.getMenu().getView(),
                model.getSettings().getView()
        );

        Thread thread = new Thread(model.getGame());
        thread.start();
    }

    public GameFrameView getView() {
        return view;
    }
}
