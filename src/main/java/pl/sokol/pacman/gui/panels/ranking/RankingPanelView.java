package pl.sokol.pacman.gui.panels.ranking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class RankingPanelView extends JPanel {

    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JButton backToMenuButton;
    private JTable rankingTable;

    public RankingPanelView() {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPanel.setViewportView(rankingTable);
        scrollPanel.setBorder(null);
        this.add(mainPanel);
    }

    public JTable getRankingTable() {
        return rankingTable;
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }
}
