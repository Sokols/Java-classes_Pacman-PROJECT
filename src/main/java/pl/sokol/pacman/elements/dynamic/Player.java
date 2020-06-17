package pl.sokol.pacman.elements.dynamic;

import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.frame.GameFrameViewModel;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Rectangle implements Renderable, Moveable {

    private final int PLAYER_WIDTH = 24;
    private final int PLAYER_HEIGHT = 24;
    private final int SPEED = 2;

    private GameFrameViewModel gameThread;

    private int currentMovement;

    private BufferedImage bf;

    public Player(int x, int y, GameFrameViewModel gameThread) {
        this.gameThread = gameThread;
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.currentMovement = 0;
        this.bf = null;
        try {
            this.bf = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {

        if (canMove(currentMovement, SPEED, this)) {
            makeMove(currentMovement, SPEED, this);

            Level level = gameThread.getModel().getLevel();
            for (int i = 0; i < level.getPoints().size(); i++) {
                if (this.intersects(level.getPoints().get(i))) {
                    level.getPoints().remove(i);
                    break;
                }
            }

            // WIN THE GAME
            if (level.getPoints().size() == 0) {
                gameThread.getModel().setEndedFlag(true);
            }
        }

        for (Enemy enemy : gameThread.getModel().getLevel().getEnemies()) {

            // LOSE THE GAME
            if (this.intersects(enemy)) {
                gameThread.getModel().setEndedFlag(true);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        move();
        g.drawImage(bf, x, y, width, height, null);
    }

    public void setCurrentMovement(int currentMovement) {
        this.currentMovement = currentMovement;
    }
}
