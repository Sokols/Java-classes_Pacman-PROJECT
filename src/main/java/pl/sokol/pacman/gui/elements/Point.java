package pl.sokol.pacman.gui.elements;

import java.awt.*;

public class Point extends Rectangle implements Renderable {

    private final int POINT_WIDTH = 8;
    private final int POINT_HEIGHT = 8;

    public Point(int x, int y) {
        setBounds(x + 10, y + 10, POINT_WIDTH, POINT_HEIGHT);
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, POINT_WIDTH, POINT_HEIGHT);
    }
}
