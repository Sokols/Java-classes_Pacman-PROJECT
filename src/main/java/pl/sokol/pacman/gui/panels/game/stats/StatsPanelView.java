package pl.sokol.pacman.gui.panels.game.stats;

import lombok.Getter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.STATS_HEIGHT;

@Getter
public class StatsPanelView extends JPanel {

    private JPanel mainPanel;
    private JLabel lifeImageLabel1;
    private JLabel lifeImageLabel2;
    private JLabel lifeImageLabel3;
    private JLabel scoreLabel;
    private JLabel spaceLabel;

    StatsPanelView() {
        this.setLayout(new GridLayout());
        this.setBackground(Color.BLACK);
        setMaximumSize(new Dimension(FRAME_WIDTH, STATS_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, STATS_HEIGHT));
        this.add(mainPanel);
    }
}
