package pl.sokol.pacman.gui.elements.dynamic;

import pl.sokol.pacman.Game;
import pl.sokol.pacman.gui.elements.Renderable;
import pl.sokol.pacman.gui.levels.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle implements Renderable, Moveable {

    private final int ENEMY_WIDTH = 32;
    private final int ENEMY_HEIGHT = 32;
    private final int SPEED = 2;
    private int SEARCH_RANGE = 50;
    private String[] IMAGES = {
            "/enemies/enemy1.png",
            "/enemies/enemy2.png",
            "/enemies/enemy3.png",
            "/enemies/enemy4.png",
            "/enemies/enemy5.png"
    };

    //    0 - up
    //    1 - right
    //    2 - down
    //    3 - left

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

//        // try to find a player in search range
//        boolean findPlayerFlag = false;
//        if (y - SEARCH_RANGE < player.y || x + SEARCH_RANGE > player.x || y + SEARCH_RANGE > player.y || x - SEARCH_RANGE < player.x) {
//            findPlayerFlag = true;
//        }

        int newMovement = random.nextInt(4);

        if (counter > 50 && canMove(newMovement)) {
            System.out.println(counter);
            makeMove(newMovement);
            currentMovement = newMovement;
            counter = 0;

        } else if (canMove(currentMovement)) {
            makeMove(currentMovement);

        } else {
            do {
                newMovement = random.nextInt(4);
            } while (canMove(newMovement));
            makeMove(newMovement);
            currentMovement = newMovement;
        }
        counter++;
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
