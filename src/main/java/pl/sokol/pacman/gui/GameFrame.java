package pl.sokol.pacman.gui;

import pl.sokol.pacman.game.GameThread;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {

    private static final String TITLE = "Pac-Man";

    private final int MENU_HEIGHT = 200;

    public GameFrame(GameThread gameThread) {
        super(TITLE);
        this.add(gameThread.getGamePanel());

        setSize(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT + MENU_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        gameThread.startNewGame();
    }
}
