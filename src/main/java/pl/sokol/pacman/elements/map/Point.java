package pl.sokol.pacman.elements.map;

import pl.sokol.pacman.elements.Renderable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Point extends Rectangle implements Renderable {

    private final int POINT_WIDTH = 8;
    private final int POINT_HEIGHT = 8;

    public Point(int x, int y) {
        setBounds(x + 10, y + 10, POINT_WIDTH, POINT_HEIGHT);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x, y, POINT_WIDTH, POINT_HEIGHT);
    }
}
