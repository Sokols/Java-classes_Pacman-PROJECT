package pl.sokol.pacman.gui.objects;

import java.awt.*;

public class Player extends Rectangle {

    private final int PLAYER_WIDTH = 32;
    private final int PLAYER_HEIGHT = 32;
    private final int SPEED = 4;
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    public Player(int x, int y) {
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void timer() {
        if (right) {
            x += SPEED;
        }
        if (left) {
            x -= SPEED;
        }
        if (up) {
            y -= SPEED;
        }
        if (down) {
            y += SPEED;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
