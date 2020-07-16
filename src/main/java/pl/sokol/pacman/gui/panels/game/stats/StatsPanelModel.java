package pl.sokol.pacman.gui.panels.game.stats;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

// Lombok annotations
@Getter
@Setter
// Hibernate annotations
@Embeddable
public class StatsPanelModel {

    private final int NUMBER_OF_LIVES = 3;

    private int score;
    private int lives;

    StatsPanelModel() {
        this.score = 0;
        this.lives = NUMBER_OF_LIVES;
    }
}
