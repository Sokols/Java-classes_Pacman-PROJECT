package pl.sokol.pacman.gui.panels.loading;

import lombok.Getter;
import lombok.Setter;
import pl.sokol.pacman.game.Save;
import pl.sokol.pacman.gui.frame.GameFrameController;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoadingPanelModel {

    private GameFrameController game;
    private List<String> savesNames;
    private List<Save> saves;

    public LoadingPanelModel(GameFrameController game) {
        this.game = game;
        this.savesNames = new ArrayList<>();
        this.saves = new ArrayList<>();
    }
}
