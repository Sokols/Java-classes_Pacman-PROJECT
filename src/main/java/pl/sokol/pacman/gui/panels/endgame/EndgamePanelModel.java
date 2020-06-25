package pl.sokol.pacman.gui.panels.endgame;

import pl.sokol.pacman.gui.frame.GameFrameController;

public class EndgamePanelModel {

    private GameFrameController game;
    private int score;

    public EndgamePanelModel(GameFrameController game, int score) {
        this.game = game;
        this.score = score;
    }

    public GameFrameController getGame() {
        return game;
    }

    public void setGame(GameFrameController game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
