package pl.sokol.pacman.gui.panels.game.stats;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.STATS_HEIGHT;

public class StatsPanelView extends JPanel {

    private JPanel mainPanel;
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    private JLabel imageLabel3;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel spaceLabel;

    StatsPanelView() {
        this.setLayout(new GridLayout());
        this.setBackground(Color.BLACK);
        Dimension dimension = new Dimension(FRAME_WIDTH, STATS_HEIGHT);
        mainPanel.setSize(dimension);
        mainPanel.setMinimumSize(dimension);
        mainPanel.setMaximumSize(dimension);
        setSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        this.add(mainPanel);
    }

    public JLabel getImageLabel1() {
        return imageLabel1;
    }

    public void setImageLabel1(JLabel imageLabel1) {
        this.imageLabel1 = imageLabel1;
    }

    public JLabel getImageLabel2() {
        return imageLabel2;
    }

    public void setImageLabel2(JLabel imageLabel2) {
        this.imageLabel2 = imageLabel2;
    }

    public JLabel getImageLabel3() {
        return imageLabel3;
    }

    public void setImageLabel3(JLabel imageLabel3) {
        this.imageLabel3 = imageLabel3;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public JLabel getSpaceLabel() {
        return spaceLabel;
    }

    public void setSpaceLabel(JLabel spaceLabel) {
        this.spaceLabel = spaceLabel;
    }
}
