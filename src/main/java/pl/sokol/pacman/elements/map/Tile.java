package pl.sokol.pacman.elements.map;

import pl.sokol.pacman.elements.Renderable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile extends Rectangle implements Renderable {

    private final int TILE_WIDTH = 32;
    private final int TILE_HEIGHT = 32;

    public Tile(int x, int y) {
        setBounds(x, y, TILE_WIDTH, TILE_HEIGHT);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(33, 0, 127));
        graphics.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
    }
}
