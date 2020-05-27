package pl.sokol.pacman.gui;

import pl.sokol.pacman.game.GameThread;
import pl.sokol.pacman.gui.stats.StatsPanelView;
import pl.sokol.pacman.gui.stats.StatsPanelViewModel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {

    private static final String TITLE = "Pac-Man";

    private StatsPanelViewModel statsViewModel;

    public GameFrame(GameThread gameThread) {
        super(TITLE);
        this.statsViewModel = new StatsPanelViewModel();

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(gameThread.getGamePanel());
        this.add(statsViewModel.getView());

        setSize(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT + StatsPanelView.STATS_HEIGHT + 32);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        gameThread.startNewGame();
    }
}
