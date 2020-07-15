package pl.sokol.pacman.gui.panels.endgame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sokol.pacman.gui.frame.GameFrameController;

@Getter
@Setter
@AllArgsConstructor
public class EndgamePanelModel {

    private GameFrameController game;
    private int score;
}
