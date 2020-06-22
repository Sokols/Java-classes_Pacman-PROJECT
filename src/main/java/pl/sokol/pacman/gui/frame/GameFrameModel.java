package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;

public class GameFrameModel {

    private GamePanelController game;
    private MenuPanelController menu;

    public GameFrameModel(MenuPanelController menu) {
        this.menu = menu;
    }

    public GamePanelController getGame() {
        return game;
    }

    public void setGame(GamePanelController game) {
        this.game = game;
    }

    public MenuPanelController getMenu() {
        return menu;
    }

    public void setMenu(MenuPanelController menu) {
        this.menu = menu;
    }
}
