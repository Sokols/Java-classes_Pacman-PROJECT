package pl.sokol.pacman.gui.levels;

import pl.sokol.pacman.gui.elements.Junction;
import pl.sokol.pacman.gui.elements.dynamic.Enemy;
import pl.sokol.pacman.gui.elements.Point;
import pl.sokol.pacman.gui.elements.Tile;
import pl.sokol.pacman.Game;
import pl.sokol.pacman.gui.elements.dynamic.Player;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {

    private int width;
    private int height;
    private int mapWidthProportion;
    private int mapHeightProportion;

    private Tile[][] tiles;
    private List<Point> points = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Junction> junctions = new ArrayList<>();

    private Game game;
    private Player player;

    public Level(String path, Player player, Game game) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResourceAsStream(path));
            this.game = game;
            this.player = player;
            this.width = map.getWidth();
            this.height = map.getHeight();
            this.mapWidthProportion = Game.WIDTH / this.width;
            this.mapHeightProportion = Game.HEIGHT / this.height;
            int[] pixels = new int[width * height];
            map.getRGB(0, 0, width, height, pixels, 0, width);
            tiles = new Tile[width][height];

            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int val = pixels[xx + (yy * width)];
                    if (val == 0xFF000000) {
                        // Black - Tile
                        tiles[xx][yy] = new Tile(xx * this.mapWidthProportion, yy * this.mapHeightProportion);
                    } else if (val == 0xFF0000FF) {
                        // Blue - Player
                        player.x = xx * this.mapWidthProportion;
                        player.y = yy * this.mapHeightProportion;
                    }  else if (val == 0xFF00FF00) {
                        // Green - Junction & Points
                        points.add(new Point(xx * this.mapWidthProportion, yy * mapHeightProportion));
                        junctions.add(new Junction(xx * this.mapWidthProportion, yy * mapHeightProportion));
                    } else if (val == 0xFFFF0000) {
                        // Red - Enemy
                        enemies.add(new Enemy(xx * mapWidthProportion, yy * mapHeightProportion, player, null, new Random().nextInt(5)));
                    } else {
                        // White - Points
                        points.add(new Point(xx * this.mapWidthProportion, yy*mapHeightProportion));
                    }
                }
            }

            for (Enemy enemy : enemies) {
                enemy.setJunctions(junctions);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y] != null) {
                    tiles[x][y].render(g);
                }
            }
        }
        for (Point point : points) {
            point.render(g);
        }
        for (Enemy enemy : enemies) {
            enemy.render(g);
            if (enemy.intersects(player)) {
                game.setEndedFlag(false);
                new Game();
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
