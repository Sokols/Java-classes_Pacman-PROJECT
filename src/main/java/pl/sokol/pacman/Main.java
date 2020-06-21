package pl.sokol.pacman;

import pl.sokol.pacman.gui.frames.game.GameFrameController;

public class Main {

    public static void main(String[] args) {
        Thread gameThread = new Thread(new GameFrameController());
        gameThread.start();
    }
}
