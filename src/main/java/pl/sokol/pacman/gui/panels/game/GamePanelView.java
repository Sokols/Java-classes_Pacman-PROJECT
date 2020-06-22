package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.panels.game.engine.EnginePanelView;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelView;

import javax.swing.*;

import java.awt.*;

import static pl.sokol.pacman.Utils.*;

public class GamePanelView extends JPanel {

    private StatsPanelView statsView;
    private EnginePanelView gameView;

    public GamePanelView(EnginePanelView gameView, StatsPanelView statsView) {
        this.gameView = gameView;
        this.statsView = statsView;

        Dimension dimension = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        setSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(gameView);
        this.add(statsView);
    }
}
