package pl.sokol.pacman.gui.panels.game.stats;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static pl.sokol.pacman.Utils.*;

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

    public void renderStats(Graphics graphics) {
        graphics.drawImage(createImageFromPanel(), 0, GAME_HEIGHT + 32, FRAME_WIDTH, STATS_HEIGHT, null);
    }

    private BufferedImage createImageFromPanel() {
        BufferedImage newImage = new BufferedImage(FRAME_WIDTH, STATS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        view.paint(newImage.getGraphics());
        return newImage;
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
        lives.add(view.getLifeImageLabel1());
        lives.add(view.getLifeImageLabel2());
        lives.add(view.getLifeImageLabel3());
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
