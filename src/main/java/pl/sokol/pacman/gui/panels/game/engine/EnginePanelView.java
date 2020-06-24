package pl.sokol.pacman.gui.panels.game.engine;

import javax.swing.JPanel;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.GAME_HEIGHT;

public class EnginePanelView extends JPanel {

    public EnginePanelView() {
        setMaximumSize(new Dimension(FRAME_WIDTH, GAME_HEIGHT));
    }
}
