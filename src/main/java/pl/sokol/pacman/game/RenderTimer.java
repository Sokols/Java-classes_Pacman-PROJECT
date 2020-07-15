package pl.sokol.pacman.game;

import lombok.Builder;
import lombok.Data;
import org.apache.log4j.Logger;

@Builder
public class RenderTimer {

    private final double TARGET_FPS = 60.0;
    private final double NS = 1e9 / TARGET_FPS;

    private final Logger LOG = Logger.getLogger(RenderTimer.class);
    private int fps;
    private double timer;
    private long lastTime;
    private long actualTime;
    private double delta;

    public void startCounting() {
        actualTime = System.nanoTime();
        delta += (actualTime - lastTime) / NS;
        lastTime = actualTime;
    }

    public void updateValues() {
        fps++;
        delta--;
    }

    public void finishCounting() {
        if (System.currentTimeMillis() - timer >= 1000) {
            LOG.info("Current FPS: " + fps);
            fps = 0;
            timer += 1000;
        }
    }

    public double getDelta() {
        return delta;
    }
}
