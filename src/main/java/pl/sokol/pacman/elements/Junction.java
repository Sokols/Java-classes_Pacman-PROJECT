package pl.sokol.pacman.elements;

import java.awt.*;

public class Junction extends Rectangle implements Renderable {

    private final int JUNCTION_WIDTH = 32;
    private final int JUNCTION_HEIGHT = 32;

    public Junction(int x, int y) {
        setBounds(x, y, JUNCTION_WIDTH, JUNCTION_HEIGHT);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }
}
