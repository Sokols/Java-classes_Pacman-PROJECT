package pl.sokol.pacman.gui.panels.ranking;

import lombok.Getter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

@Getter
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
}
