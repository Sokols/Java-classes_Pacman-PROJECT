package pl.sokol.pacman.gui.levels;

import pl.sokol.pacman.gui.elements.dynamic.Enemy;
import pl.sokol.pacman.gui.elements.Point;
import pl.sokol.pacman.gui.elements.Tile;
import pl.sokol.pacman.threads.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {

    private int width;
    private int height;
    private int mapWidthProportion;
    private int mapHeightProportion;

    private Tile[][] tiles;
    private List<pl.sokol.pacman.gui.elements.Point> points = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    public Level(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResourceAsStream(path));
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
                        // Tile
                        tiles[xx][yy] = new Tile(xx * this.mapWidthProportion, yy * this.mapHeightProportion);
                    } else if (val == 0xFF0000FF) {
                        // Player
                        Game.player.x = xx * this.mapWidthProportion;
                        Game.player.y = yy * this.mapHeightProportion;
                    } else if (val == 0xFFFF0000) {
                        // Enemy
                        enemies.add(new Enemy(xx * mapWidthProportion, yy * mapHeightProportion));

                    } else {
                        points.add(new pl.sokol.pacman.gui.elements.Point(xx * this.mapWidthProportion, yy*mapHeightProportion));
                    }
                }
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
        for (int i = 0; i < points.size(); i++) {
            points.get(i).render(g);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public List<pl.sokol.pacman.gui.elements.Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
