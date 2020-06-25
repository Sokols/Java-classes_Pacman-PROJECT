package pl.sokol.pacman.gui.panels.loading;

import pl.sokol.pacman.gui.frame.GameFrameController;

import java.util.ArrayList;
import java.util.List;

public class LoadingPanelModel {

    private GameFrameController game;
    private List<String> savesNames;

    public LoadingPanelModel(GameFrameController game) {
        this.game = game;
        this.savesNames = new ArrayList<>();
    }

    public GameFrameController getGame() {
        return game;
    }

    public void setGame(GameFrameController game) {
        this.game = game;
    }

    public List<String> getSavesNames() {
        return savesNames;
    }
}
