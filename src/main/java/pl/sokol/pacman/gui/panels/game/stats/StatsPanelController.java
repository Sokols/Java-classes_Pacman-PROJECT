package pl.sokol.pacman.gui.panels.game.stats;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.STATS_HEIGHT;

public class StatsPanelController {

    private final int BLINKING_TIME = 750;

    private StatsPanelView view;
    private StatsPanelModel model;

    private List<JLabel> lives;

    private int score;

    private Timer timer;

    private String scoreText;

    public StatsPanelController() {
        this.view = new StatsPanelView();
        this.model = new StatsPanelModel();
        initStats();
    }

    public void renderStats(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, STATS_HEIGHT);
        view.repaint();
        g.dispose();
    }

    public void updateScore() {
        model.setScore(model.getScore() + 10);
        view.getScoreLabel().setText(scoreText + model.getScore());
    }

    public void updateLives() {
        if (!lives.isEmpty()) {
            model.setLives(model.getLives() - 1);
            JLabel live = lives.remove(model.getLives());
            live.setVisible(false);
        }
    }

    private void initStats() {
        this.timer = new Timer(BLINKING_TIME, e -> blink());
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
