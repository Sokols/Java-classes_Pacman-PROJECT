package pl.sokol.pacman.elements.dynamic;

import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.game.GameThread;
import pl.sokol.pacman.gui.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Rectangle implements Renderable, Moveable {

    private final int PLAYER_WIDTH = 24;
    private final int PLAYER_HEIGHT = 24;
    private final int SPEED = 4;

    private GameThread gameThread;

    private int currentMovement;

    public Player(int x, int y, GameThread gameThread) {
        this.gameThread = gameThread;
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        currentMovement = 0;
    }

    @Override
    public void move() {

        if (canMove(currentMovement, SPEED, this)) {
            makeMove(currentMovement, SPEED, this);

            for (int i = 0; i < gameThread.getLevel().getPoints().size(); i++) {
                if (this.intersects(gameThread.getLevel().getPoints().get(i))) {
                    gameThread.getLevel().getPoints().remove(i);
                    break;
                }
            }

            if (gameThread.getLevel().getPoints().size() == 0) {
                // WIN THE GAME
            }
        }

        for (Enemy enemy : gameThread.getLevel().getEnemies()) {
            if (this.intersects(enemy)) {
                gameThread.setEndedFlag(false);
                new GameFrame(new GameThread());
            }
        }
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

    public void setCurrentMovement(int currentMovement) {
        this.currentMovement = currentMovement;
    }
}
