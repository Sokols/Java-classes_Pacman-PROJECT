package pl.sokol.pacman.game;

import org.apache.log4j.Logger;

public class RenderTimer {

    private final Logger LOG;

    private final double TARGET_FPS = 60.0;
    private final double NS = 1e9 / TARGET_FPS;

    private int fps;
    private double timer;
    private long lastTime;
    private long actualTime;
    private double delta;

    public RenderTimer(int fps, double timer, long lastTime, long actualTime, double delta) {
        this.LOG = Logger.getLogger(RenderTimer.class.getName());
        this.fps = fps;
        this.timer = timer;
        this.lastTime = lastTime;
        this.actualTime = actualTime;
        this.delta = delta;
    }

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
