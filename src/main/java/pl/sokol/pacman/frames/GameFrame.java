package pl.sokol.pacman.frames;

import pl.sokol.pacman.game.GameThread;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {

    public GameFrame(GameThread gameThread) {
        super(GameThread.TITLE);
        this.add(gameThread);

        setSize(640, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        gameThread.startNewGame();
    }
}
