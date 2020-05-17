package pl.sokol.pacman.gui.elements.dynamic;

import pl.sokol.pacman.Game;
import pl.sokol.pacman.gui.elements.Renderable;
import pl.sokol.pacman.gui.levels.Level;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle implements Renderable, Moveable {

    private final int ENEMY_WIDTH = 32;
    private final int ENEMY_HEIGHT = 32;
    private final int ENEMY_COUNTER = 30;
    private final int SPEED = 2;
    private int SEARCH_RANGE = 20;
    private String[] IMAGES = {
            "/enemies/enemy1.png",
            "/enemies/enemy2.png",
            "/enemies/enemy3.png",
            "/enemies/enemy4.png",
            "/enemies/enemy5.png"
    };

    private Random random = new Random();

    private List<Boolean> movements = new ArrayList<>();
    private int currentMovement = random.nextInt(4);
    private int counter = 0;

    private BufferedImage bf;

    private Player player;

    public Enemy(int x, int y, Player player, int numberOfTheImage) throws IOException {
        this.player = player;
        initMovements();
        setBounds(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        bf = ImageIO.read(getClass().getResourceAsStream(IMAGES[numberOfTheImage]));
    }

    public void render(Graphics g) {
        move();
        g.drawImage(bf, x, y, width, height, null);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {

        // vector position of the enemy relative to the player
        // HERE WE USE JAVA COORDINATE SYSTEM
        int vectorX = player.x - x;
        int vectorY = player.y - y;

        // try to find a player in search range
        if (vectorX < SEARCH_RANGE || vectorY < SEARCH_RANGE) {

            // I QUARTER
            if (vectorX >= 0 && vectorY >= 0) {
                if (canMove(1)) {
                    makeMove(1);
                } else if (canMove(2)) {
                    makeMove(2);
                } else if (canMove(3)) {
                    makeMove(3);
                } else {
                    makeMove(0);
                }
                // II QUARTER
            } else if (vectorX <= 0 && vectorY >= 0) {
                if (canMove(2)) {
                    makeMove(2);
                } else if (canMove(3)) {
                    makeMove(3);
                } else if (canMove(1)) {
                    makeMove(1);
                } else {
                    makeMove(0);
                }
            }
            // III QUARTER
            else if (vectorX <= 0 && vectorY <= 0) {
                if (canMove(3)) {
                    makeMove(3);
                } else if (canMove(0)) {
                    makeMove(0);
                } else if (canMove(1)) {
                    makeMove(1);
                } else {
                    makeMove(2);
                }
            }
            // IV QUARTER
            else {
                if (canMove(0)) {
                    makeMove(0);
                } else if (canMove(1)) {
                    makeMove(1);
                } else if (canMove(3)) {
                    makeMove(3);
                } else {
                    makeMove(2);
                }
            }
        }

        // if an enemy didn't find a player
        else {
            int newMovement = random.nextInt(4);

            if (counter > ENEMY_COUNTER && canMove(newMovement)) {
                makeMove(newMovement);
                currentMovement = newMovement;
                counter = 0;

            } else if (canMove(currentMovement)) {
                makeMove(currentMovement);

            } else {
                do {
                    newMovement = random.nextInt(4);
                } while (!canMove(newMovement));
                makeMove(newMovement);
                currentMovement = newMovement;
            }
            if (counter > ENEMY_COUNTER) {
                counter = 0;
            }
            counter++;
        }
    }

    @Override
    public boolean canMove(int movement) {
        int toX = 0, toY = 0;
        switch (movement) {
            case 0:
                toX = x;
                toY = y - SPEED;
                break;

            case 1:
                toX = x + SPEED;
                toY = y;
                break;

            case 2:
                toX = x;
                toY = y + SPEED;
                break;

            case 3:
                toX = x - SPEED;
                toY = y;
                break;

            default:
                break;
        }

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

    private void makeMove(int newMovement) {
        switch (newMovement) {
            // up
            case 0:
                y -= SPEED;
                break;

            // right
            case 1:
                x += SPEED;
                break;

            // down
            case 2:
                y += SPEED;
                break;

            // left
            case 3:
                x -= SPEED;
                break;

            default:
                break;
        }
    }

    private void initMovements() {
        for (int i = 0; i < 4; i++) {
            this.movements.add(false);
        }
    }
}
