package pl.sokol.pacman.gui.objects;

import java.awt.*;

public class Enemy extends Rectangle {

    private final int ENEMY_WIDTH = 32;
    private final int ENEMY_HEIGHT = 32;

    public Enemy(int x, int y) {
        setBounds(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
    }

    public void timer() {

    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
    }
}
