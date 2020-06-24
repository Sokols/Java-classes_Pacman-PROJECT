package pl.sokol.pacman.gui.panels.loading;

import pl.sokol.pacman.gui.frame.GameFrameController;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class LoadingPanelModel {

    private GameFrameController game;
    private List<JPanel> saves;
    private List<String> savesNames;
    private String clickedSave;

    public LoadingPanelModel(GameFrameController game) {
        this.game = game;
        this.saves = new ArrayList<>();
        this.savesNames = new ArrayList<>();
        this.clickedSave = null;
    }

    public GameFrameController getGame() {
        return game;
    }

    public void setGame(GameFrameController game) {
        this.game = game;
    }

    public List<JPanel> getSaves() {
        return saves;
    }

    public void setSaves(List<JPanel> saves) {
        this.saves = saves;
    }

    public List<String> getSavesNames() {
        return savesNames;
    }

    public void setSavesNames(List<String> savesNames) {
        this.savesNames = savesNames;
    }
}
