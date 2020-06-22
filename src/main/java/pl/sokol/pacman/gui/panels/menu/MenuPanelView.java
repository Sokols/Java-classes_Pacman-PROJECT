package pl.sokol.pacman.gui.panels.menu;

import javax.swing.*;
import java.awt.*;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class MenuPanelView extends JPanel {

    private JPanel mainPanel;

    private JButton newGameButton;
    private JButton LOADGAMEButton;
    private JButton exitButton;

    public MenuPanelView() {
        Dimension dimension = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        this.add(mainPanel);
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
