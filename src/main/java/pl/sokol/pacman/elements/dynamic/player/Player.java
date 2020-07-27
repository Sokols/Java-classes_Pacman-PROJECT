package pl.sokol.pacman.elements.dynamic.player;

import lombok.Getter;
import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.elements.dynamic.Moveable;
import pl.sokol.pacman.elements.dynamic.enemy.Enemy;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
public class Player extends Rectangle implements Renderable, Moveable {

    private final int PLAYER_SPEED = 2;
    private final int PLAYER_WIDTH = 24;
    private final int PLAYER_HEIGHT = 24;

    private final Logger LOG = Logger.getLogger(Player.class);

    private GamePanelController game;
    private StatsPanelController stats;
    private BufferedImage[][] playerImages;
    private PlayerEngine playerEngine;

    public Player(int x, int y, GamePanelController game, StatsPanelController stats) {
        this.game = game;
        this.stats = stats;
        this.playerEngine = new PlayerEngine();
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        setPlayerImages();
    }

    @Override
    public void move() {
        Level level = game.getModel().getLevel();
        if (canMove(playerEngine.getCurrentMovement(), PLAYER_SPEED, this, game.getModel().getLevel())) {
            makeMove(playerEngine.getCurrentMovement(), PLAYER_SPEED, this);
            playerEngine.changePlayerImage();

            // FINISH LEVEL - out of points to collect
            if (level.getPoints().size() == 0) {
                // VICTORY - out of points to collect & no more levels
                if (stats.getModel().getLevel() == level.getNUMBER_OF_MAPS()) {
                    game.getModel().getGameFrame().endGame("VICTORY", stats.getModel().getScore());
                }
                // NEXT LEVEL - there is more levels to win!
                else {
                    stats.getModel().setLevel(stats.getModel().getLevel() + 1);
                    game.getModel().setLevel(new Level(game, stats));
                }
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
                playerEngine.setNumberOfTheImage(0);
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
        graphics.drawImage(playerImages[playerEngine.getCurrentMovement()][playerEngine.getNumberOfTheImage()], x, y, width, height, null);
    }

    private void setPlayerImages() {
        int DIRECTIONS = playerEngine.getDIRECTIONS();
        int IMAGES = playerEngine.getIMAGES();

        this.playerImages = new BufferedImage[DIRECTIONS][IMAGES];
        try {
            for (int direction = 0; direction < DIRECTIONS; direction++) {
                for (int image = 0; image < IMAGES; image++) {
                    if (image == 0) {
                        playerImages[direction][image] = ImageIO.read(getClass().getResourceAsStream("/graphics/player/player.png"));
                    } else {
                        String path = "/graphics/player/" + direction + "/" + image + ".png";
                        playerImages[direction][image] = ImageIO.read(getClass().getResourceAsStream(path));
                    }
                }
            }
        } catch (IOException e) {
            LOG.warn(e);
        }
    }
}
