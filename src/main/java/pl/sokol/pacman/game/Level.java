package pl.sokol.pacman.game;

import org.apache.log4j.Logger;
import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.elements.dynamic.Enemy;
import pl.sokol.pacman.elements.dynamic.Player;
import pl.sokol.pacman.elements.map.Junction;
import pl.sokol.pacman.elements.map.Point;
import pl.sokol.pacman.elements.map.Tile;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.GAME_HEIGHT;

public class Level implements Renderable {

    private Logger LOG;
    private List<Point> points;
    private List<Enemy> enemies;
    private List<java.awt.Point> enemiesPoints;
    private List<Junction> junctions;
    private List<Tile> tiles;
    private BufferedImage mapForLevel;
    private Player player;

    public Level(String path, GamePanelController gameThread, StatsPanelController stats) {
        this.LOG = Logger.getLogger(Level.class.getName());
        this.points = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.enemiesPoints = new ArrayList<>();
        this.junctions = new ArrayList<>();
        this.tiles = new ArrayList<>();
        try {
            this.mapForLevel = ImageIO.read(getClass().getResourceAsStream(path));
            this.player = new Player(0, 0, gameThread, stats);
            setElements();
        } catch (IOException e) {
            LOG.warn(e);
        }
    }

    @Override
    public void render(Graphics graphics) {

        // render tiles
        for (Tile tile : tiles) {
            tile.render(graphics);
        }

        // render points
        for (Point point : points) {
            point.render(graphics);
        }

        // render enemies
        // noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(graphics);
        }

        // render player
        player.render(graphics);
    }

    public void addEnemy() {
        try {
            enemies.add(new Enemy.Builder()
                    .player(player)
                    .level(this)
                    .junctions(junctions)
                    .numberOfTheImage(new Random().nextInt(5))
                    .currentMovement(0)
                    .previousMovement(0)
                    .build(enemiesPoints.get(0).getLocation().x, enemiesPoints.get(0).getLocation().y));
        } catch (IOException e) {
            LOG.warn(e);
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public List<java.awt.Point> getEnemiesPoints() {
        return enemiesPoints;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Junction> getJunctions() {
        return junctions;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    private void setElements() throws IOException, ExceptionInInitializerError {
        int mapWidth = mapForLevel.getWidth();
        int mapHeight = mapForLevel.getHeight();

        int mapWidthProportion = FRAME_WIDTH / mapWidth;
        int mapHeightProportion = GAME_HEIGHT / mapHeight;

        for (int xx = 0; xx < mapWidth; xx++) {
            for (int yy = 0; yy < mapHeight; yy++) {

                int x = xx * mapWidthProportion;
                int y = yy * mapHeightProportion + 32;

                // switch by RGB value
                switch (mapForLevel.getRGB(xx, yy)) {

                    // Black - Tile
                    case 0xFF000000:
                        tiles.add(new Tile(x, y));
                        break;

                    // Blue - Player & Junction
                    case 0xFF0000FF:
                        player.setLocation(x, y);
                        junctions.add(new Junction(x, y));
                        break;

                    // Green - Junction & Point
                    case 0xFF00FF00:
                        points.add(new Point(x, y));
                        junctions.add(new Junction(x, y));
                        break;

                    // Red - Enemy
                    case 0xFFFF0000:
                        enemies.add(new Enemy.Builder()
                                .player(player)
                                .level(this)
                                .junctions(null)
                                .numberOfTheImage(new Random().nextInt(5))
                                .currentMovement(0)
                                .previousMovement(0)
                                .build(x, y));

                        enemiesPoints.add(new java.awt.Point(x, y));
                        break;

                    // White - Point
                    case 0xFFFFFFFF:
                        points.add(new Point(x, y));
                        break;

                    default:
                        throw new ExceptionInInitializerError("Error of image loading!");
                }
            }
        }

        // set prepared junctions for every enemy
        for (Enemy enemy : enemies) {
            enemy.setJunctions(junctions);
        }
    }
}
