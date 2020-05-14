package pl.sokol.pacman.gui.elements.dynamic;

import pl.sokol.pacman.gui.elements.Renderable;
import pl.sokol.pacman.gui.elements.dynamic.Moveable;
import pl.sokol.pacman.gui.levels.Level;
import pl.sokol.pacman.threads.Game;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Rectangle implements Renderable, Moveable {

    private final int PLAYER_WIDTH = 24;
    private final int PLAYER_HEIGHT = 24;
    private final int SPEED = 2;
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    public Player(int x, int y) {
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

//    @Override
//    public void run() {
//        move();
//        // this.render();
//    }

    @Override
    public void move() {
        if (right && canMove(x + SPEED, y)) {
            x += SPEED;
        }
        if (left && canMove(x - SPEED, y)) {
            x -= SPEED;
        }
        if (up && canMove(x, y - SPEED)) {
            y -= SPEED;
        }
        if (down && canMove(x, y + SPEED)) {
            y += SPEED;
        }

        for (int i = 0; i < Game.level.getPoints().size(); i++) {
            if (this.intersects(Game.level.getPoints().get(i))) {
                Game.level.getPoints().remove(i);
                break;
            }
            if (Game.level.getPoints().size() == 0) {
                // WIN THE GAME
            }
        }

    }

    @Override
    public boolean canMove(int toX, int toY) {
        Rectangle bounds = new Rectangle(toX, toY, width, height);
        Level level = Game.level;
        for (int xx = 0; xx < level.getTiles().length; xx++) {
            for (int yy = 0; yy < level.getTiles()[0].length; yy++) {
                if (level.getTiles()[xx][yy] != null) {
                    if (bounds.intersects(level.getTiles()[xx][yy])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void render(Graphics g) {
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bf, x, y, width, height, null);
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
