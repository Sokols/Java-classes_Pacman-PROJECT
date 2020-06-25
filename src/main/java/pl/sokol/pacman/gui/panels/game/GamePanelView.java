package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.panels.game.engine.EnginePanelView;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelView;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class GamePanelView extends JPanel {

    public GamePanelView(EnginePanelView gameView, StatsPanelView statsView) {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(gameView);
        this.add(statsView);
    }
}
