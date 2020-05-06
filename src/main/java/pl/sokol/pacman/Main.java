package pl.sokol.pacman;

import pl.sokol.pacman.gui.frames.GameFrame;
import pl.sokol.pacman.threads.Game;

public class Main {

    public static void main(String[] args) {

        new GameFrame(new Game());
    }
}
