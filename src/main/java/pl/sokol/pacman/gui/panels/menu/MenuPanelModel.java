package pl.sokol.pacman.gui.panels.menu;

import pl.sokol.pacman.gui.frame.GameFrameController;

public class MenuPanelModel {

    private GameFrameController game;

    public MenuPanelModel(GameFrameController game) {
        this.game = game;
    }

    public GameFrameController getGame() {
        return game;
    }

    public void setGame(GameFrameController game) {
        this.game = game;
    }
}

