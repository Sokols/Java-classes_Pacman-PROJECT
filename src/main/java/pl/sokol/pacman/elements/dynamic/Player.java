package pl.sokol.pacman.elements.dynamic;

import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.game.GameThread;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle implements Renderable, Moveable {

    private final int PLAYER_WIDTH = 24;
    private final int PLAYER_HEIGHT = 24;
    private final int SPEED = 4;
    //    0 - up
    //    1 - right
    //    2 - down
    //    3 - left
    private List<Boolean> movements = new ArrayList<>();

    public Player(int x, int y) {
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        initMovements();
    }


//    @Override
//    public void run() {
//        move();
//        // this.render();
//    }

    @Override
    public void move() {
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

        for (int i = 0; i < GameThread.level.getPoints().size(); i++) {
            if (this.intersects(GameThread.level.getPoints().get(i))) {
                GameThread.level.getPoints().remove(i);
                break;
            }
            if (GameThread.level.getPoints().size() == 0) {
                // WIN THE GAME
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
        Level level = GameThread.level;
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
        move();
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bf, x, y, width, height, null);
    }

    private void initMovements() {
        for (int i = 0; i < 4; i++) {
            this.movements.add(false);
        }
    }

    public List<Boolean> getMovements() {
        return movements;
    }

    public void setMovements(List<Boolean> movements) {
        this.movements = movements;
    }
}
