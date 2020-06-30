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

    public Player(int x, int y, GamePanelController game, StatsPanelController stats) throws IOException {
        this.game = game;
        this.stats = stats;
        this.currentMovement = 0;
        this.imageOfPlayer = ImageIO.read(getClass().getResourceAsStream("/graphics/player/player.png"));
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public void move() {
        Level level = game.getModel().getLevel();
        if (canMove(currentMovement, PLAYER_SPEED, this, game.getModel().getLevel())) {
            makeMove(currentMovement, PLAYER_SPEED, this);

            // VICTORY - out of points to collect
            if (level.getPoints().size() == 0) {
                game.getModel().getGameFrame().endGame("VICTORY", stats.getModel().getScore());
            }

            // GET THE POINT
            for (int i = 0; i < level.getPoints().size(); i++) {
                if (this.intersects(level.getPoints().get(i))) {
                    level.getPoints().remove(i);
                    stats.updateScore();
                    break;
                }
            }
        }

        // CONTACT WITH THE ENEMY
        for (Enemy enemy : level.getEnemies()) {
            if (this.intersects(enemy)) {
                // lose 1 life
                stats.updateLives();
                // RESTART LEVEL - player and enemies go to their start positions
                if (stats.getModel().getLives() > 0) {
                    game.restartLevel();
                }
                // GAME OVER - last life was lost
                else {
                    game.getModel().getGameFrame().endGame("GAME OVER", stats.getModel().getScore());
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        move();
        graphics.drawImage(imageOfPlayer, x, y, width, height, null);
    }

    public void setCurrentMovement(int currentMovement) {
        this.currentMovement = currentMovement;
    }

    public int getCurrentMovement() {
        return currentMovement;
    }
}
