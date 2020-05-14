package pl.sokol.pacman.gui.elements.dynamic;

import pl.sokol.pacman.gui.elements.Renderable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends Rectangle implements Renderable, Moveable {

    private final int ENEMY_WIDTH = 32;
    private final int ENEMY_HEIGHT = 32;

    public Enemy(int x, int y) {
        setBounds(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
    }

    public void render(Graphics g) {
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(getClass().getResourceAsStream("/enemies/enemy1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bf, x, y, width, height, null);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean canMove(int toX, int toY) {
        return false;
    }
}
