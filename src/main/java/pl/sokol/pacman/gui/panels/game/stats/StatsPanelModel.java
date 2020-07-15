package pl.sokol.pacman.gui.panels.game.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsPanelModel {

    private final int NUMBER_OF_LIVES = 3;
    private int score;
    private int lives;

    StatsPanelModel() {
        this.score = 0;
        this.lives = NUMBER_OF_LIVES;
    }
}
