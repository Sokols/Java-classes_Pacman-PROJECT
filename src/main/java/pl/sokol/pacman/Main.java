package pl.sokol.pacman;

import pl.sokol.pacman.gui.frame.GameFrameController;

public class Main {

    public static void main(String[] args) {
        Utils.configureLogger();

        new GameFrameController();
    }
}
