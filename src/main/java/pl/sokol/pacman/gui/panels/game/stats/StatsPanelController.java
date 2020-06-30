package pl.sokol.pacman.gui.panels.game.stats;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class StatsPanelController {

    private final int BLINKING_TIME = 750;
    private final String scoreTextTemp;

    private StatsPanelModel model;
    private StatsPanelView view;
    private List<JLabel> lives;
    private Timer timer;

    public StatsPanelController() {
        this.view = new StatsPanelView();
        this.model = new StatsPanelModel();
        this.scoreTextTemp = view.getScoreLabel().getText();
        initStats();
    }

    public void updateScore() {
        model.setScore(model.getScore() + 10);
        view.getScoreLabel().setText(scoreTextTemp + model.getScore());
    }

    public void updateLives() {
        if (!lives.isEmpty()) {
            model.setLives(model.getLives() - 1);
            lives.remove(model.getLives()).setVisible(false);
        }
    }

    public void renderStats() {
        view.repaint();
    }

    private void initStats() {
        this.timer = new Timer(BLINKING_TIME, e -> blink());
        timer.start();
        initLives();
        view.getScoreLabel().setText(scoreTextTemp + model.getScore());
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

    public String getScoreTextTemp() {
        return scoreTextTemp;
    }

    public StatsPanelView getView() {
        return view;
    }

    public StatsPanelModel getModel() {
        return model;
    }

    public void setModel(StatsPanelModel model) {
        this.model = model;
    }
}
