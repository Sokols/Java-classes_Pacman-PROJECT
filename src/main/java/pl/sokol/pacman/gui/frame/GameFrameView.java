package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelView;
import pl.sokol.pacman.gui.panels.stats.StatsPanelView;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrameView extends JFrame {

    private static final String TITLE = "Pac-Man";

    private StatsPanelView statsView;
    private GamePanelView gameView;

    public GameFrameView(GamePanelView gameView, StatsPanelView statsView) {
        super(TITLE);
        this.gameView = gameView;
        this.statsView = statsView;

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(gameView);
        this.add(statsView);
        setSize(GamePanelView.GAME_WIDTH, GamePanelView.GAME_HEIGHT + StatsPanelView.STATS_HEIGHT + 32);
        setFocusable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
