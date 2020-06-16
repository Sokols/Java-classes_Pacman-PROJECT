package pl.sokol.pacman.gui.panels.game;

import javax.swing.JPanel;
import java.awt.Dimension;

public class GamePanelView extends JPanel {

    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;

    public GamePanelView() {
        Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }
}
