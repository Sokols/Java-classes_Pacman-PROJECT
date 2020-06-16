package pl.sokol.pacman;

import pl.sokol.pacman.gui.frame.GameFrameViewModel;

public class Main {

    public static void main(String[] args) {
        Thread gameThread = new Thread(new GameFrameViewModel());
        gameThread.start();
    }
}
