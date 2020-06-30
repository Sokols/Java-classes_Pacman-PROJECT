package pl.sokol.pacman.gui.panels.game.stats;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.STATS_HEIGHT;

public class StatsPanelView extends JPanel {

    private JPanel mainPanel;
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    private JLabel imageLabel3;
    private JLabel scoreLabel;
    private JLabel spaceLabel;

    StatsPanelView() {
        this.setLayout(new GridLayout());
        this.setBackground(Color.BLACK);
        setMaximumSize(new Dimension(FRAME_WIDTH, STATS_HEIGHT));
        this.add(mainPanel);
    }

    public JLabel getImageLabel1() {
        return imageLabel1;
    }

    public JLabel getImageLabel2() {
        return imageLabel2;
    }

    public JLabel getImageLabel3() {
        return imageLabel3;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getSpaceLabel() {
        return spaceLabel;
    }
}
