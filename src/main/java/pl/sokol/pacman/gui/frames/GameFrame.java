package pl.sokol.pacman.gui.frames;

import pl.sokol.pacman.threads.Game;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(Game game) {
        super(Game.TITLE);
        this.add(game);

        setSize(640, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
//        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        game.start();
    }
}
