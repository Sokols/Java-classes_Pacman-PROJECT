package pl.sokol.pacman.gui.panels.menu;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class MenuPanelView extends JPanel {

    private JPanel mainPanel;
    private JButton newGameButton;
    private JButton loadButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton backToGameButton;

    public MenuPanelView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        saveButton.setVisible(false);
        backToGameButton.setVisible(false);
        this.add(mainPanel);
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getBackToGameButton() {
        return backToGameButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
