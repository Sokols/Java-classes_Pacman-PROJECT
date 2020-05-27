package pl.sokol.pacman.gui.stats;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatsPanelViewModel {

    private final int BLINKING_TIME = 750;

    private StatsPanelView view;
    private StatsPanelModel model;

    private List<JLabel> lives;

    private int score;

    private Timer timer;

    private String scoreText;

    public StatsPanelViewModel() {
        this.view = new StatsPanelView();
        this.model = new StatsPanelModel();
        initStats();
    }

    private void initStats() {
        this.timer = new Timer(BLINKING_TIME, e -> {
            blink();
        });
        timer.start();
        initLives();

        // init texts
        scoreText = view.getScoreLabel().getText();
        view.getScoreLabel().setText(scoreText + model.getScore());
    }

    private void blink() {
        if (view.getSpaceLabel().getForeground() == Color.WHITE) {
            view.getSpaceLabel().setForeground(Color.BLACK);
        } else {
            view.getSpaceLabel().setForeground(Color.WHITE);
        }
        timer.restart();
    }

    private void initLives() {
        lives = new ArrayList<>();
        lives.add(view.getImageLabel1());
        lives.add(view.getImageLabel2());
        lives.add(view.getImageLabel3());
    }

    public List<JLabel> getLives() {
        return lives;
    }

    public void setLives(List<JLabel> lives) {
        this.lives = lives;
    }

    public StatsPanelView getView() {
        return view;
    }
}
