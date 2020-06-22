package pl.sokol.pacman.elements.dynamic;

import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Rectangle implements Renderable, Moveable {

    private final int PLAYER_WIDTH = 24;
    private final int PLAYER_HEIGHT = 24;
    private final int PLAYER_SPEED = 2;

    private GamePanelController game;
    private StatsPanelController stats;

    private int currentMovement;

    private BufferedImage imageOfPlayer;

    public Player(int x, int y, GamePanelController game, StatsPanelController stats) {
        this.game = game;
        this.stats = stats;
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.currentMovement = 0;
        this.imageOfPlayer = null;
        try {
            this.imageOfPlayer = ImageIO.read(getClass().getResourceAsStream("/graphics/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {

        Level level = game.getModel().getLevel();
        if (canMove(currentMovement, PLAYER_SPEED, this, game.getModel().getLevel())) {
            makeMove(currentMovement, PLAYER_SPEED, this);

            // WIN THE GAME
            if (level.getPoints().size() == 0) {
                game.getModel().setEndedFlag(true);
            }

            // GET POINT
            for (int i = 0; i < level.getPoints().size(); i++) {
                if (this.intersects(level.getPoints().get(i))) {
                    level.getPoints().remove(i);
                    stats.updateScore();
                    break;
                }
            }
        }

        for (Enemy enemy : level.getEnemies()) {
            // CONTACT WITH ENEMY
            if (this.intersects(enemy)) {
                stats.updateLives();
                if (stats.getLives().size() > 0) {
                    game.restartLevel();
                } else {
                    game.getModel().setEndedFlag(true);
                    game.getModel().setStoppedFlag(true);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        move();
        g.drawImage(imageOfPlayer, x, y, width, height, null);
    }

    public void setCurrentMovement(int currentMovement) {
        this.currentMovement = currentMovement;
    }
}
