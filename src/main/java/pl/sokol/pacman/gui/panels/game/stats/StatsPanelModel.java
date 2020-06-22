package pl.sokol.pacman.gui.panels.game.stats;

public class StatsPanelModel {

    private final int NUMBER_OF_LIVES = 3;

    private int score;
    private int lives;

    StatsPanelModel() {
        this.score = 0;
        this.lives = NUMBER_OF_LIVES;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }


}
