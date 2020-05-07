package pl.sokol.pacman.gui.levels;

import pl.sokol.pacman.threads.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level {

    private int width;
    private int height;
    private int mapWidthProportion;
    private int mapHeightProportion;

    private Tile[][] tiles;


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
    }
}
