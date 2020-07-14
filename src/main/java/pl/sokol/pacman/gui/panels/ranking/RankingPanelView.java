package pl.sokol.pacman.gui.panels.ranking;

import javax.swing.*;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class RankingPanelView extends JPanel {

    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JPanel rankingPanel;
    private JButton backToMenuButton;
    private JTable rankingTable;

    public RankingPanelView() {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPanel.setViewportView(rankingPanel);
        scrollPanel.setBorder(null);
        scrollPanel.add(rankingPanel);
        mainPanel.add(scrollPanel);
        this.add(mainPanel);
    }

    public JTable getRankingTable() {
        return rankingTable;
    }

    public JPanel getRankingPanel() {
        return rankingPanel;
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }
}
