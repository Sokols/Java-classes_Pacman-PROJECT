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
    private List<Boolean> movements = new ArrayList<>();
    private int currentMovement;
    private int previousMovement;

    private Random random = new Random();

    private BufferedImage bf = null;

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

        // try to find a player in search range
        boolean findPlayerFlag = false;
        if (y - SEARCH_RANGE < player.y || x + SEARCH_RANGE > player.x || y + SEARCH_RANGE > player.y || x - SEARCH_RANGE < player.x) {
            findPlayerFlag = true;
        }

        if (findPlayerFlag) {

        } else {
            int newMovement = random.nextInt(4);

            // up
            if (movements.get(0) && canMove(0)) {
                y -= SPEED;
            }

            // right
            if (movements.get(1) && canMove(1)) {
                x += SPEED;
            }

            // down
            if (movements.get(2) && canMove(2)) {
                y += SPEED;
            }

            // left
            if (movements.get(3) && canMove(3)) {
                x -= SPEED;
            }
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

    private void initMovements() {
        for (int i = 0; i < 4; i++) {
            this.movements.add(false);
        }
    }
}
