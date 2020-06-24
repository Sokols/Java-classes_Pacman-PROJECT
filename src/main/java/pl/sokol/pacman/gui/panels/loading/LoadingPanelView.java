package pl.sokol.pacman.gui.panels.loading;

import javax.swing.*;
import java.awt.*;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class LoadingPanelView extends JPanel {

    private JPanel mainPanel;
    private JPanel loadingPanel;
    private JButton backToMenuButton;
    private JScrollPane scrollPanel;

    public LoadingPanelView() {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPanel.setViewportView(loadingPanel);
        scrollPanel.setBorder(null);
        this.add(mainPanel);
    }

    public JPanel getLoadingPanel() {
        return loadingPanel;
    }

    public void setLoadingPanel(JPanel loadingPanel) {
        this.loadingPanel = loadingPanel;
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public void setBackToMenuButton(JButton backToMenuButton) {
        this.backToMenuButton = backToMenuButton;
    }
}
