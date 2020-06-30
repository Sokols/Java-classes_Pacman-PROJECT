package pl.sokol.pacman.elements.map;

import java.awt.Rectangle;

public class Junction extends Rectangle {

    private final int JUNCTION_WIDTH = 32;
    private final int JUNCTION_HEIGHT = 32;

    public Junction(int x, int y) {
        setBounds(x, y, JUNCTION_WIDTH, JUNCTION_HEIGHT);
    }
}
