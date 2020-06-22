package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.panels.game.engine.EnginePanelView;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelView;

import javax.swing.*;

public class GamePanelView extends JPanel {

    private StatsPanelView statsView;
    private EnginePanelView gameView;

    public GamePanelView(EnginePanelView gameView, StatsPanelView statsView) {
        this.gameView = gameView;
        this.statsView = statsView;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(gameView);
        this.add(statsView);
        setSize(EnginePanelView.GAME_WIDTH, EnginePanelView.GAME_HEIGHT + StatsPanelView.STATS_HEIGHT + 32);
        setFocusable(true);
        setVisible(true);
    }
}
