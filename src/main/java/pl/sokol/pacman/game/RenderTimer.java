package pl.sokol.pacman.game;

import org.apache.log4j.Logger;

public class RenderTimer {

    private final double TARGET_FPS = 60.0;
    private final double NS = 1e9 / TARGET_FPS;

    private Logger LOG;
    private int fps;
    private double timer;
    private long lastTime;
    private long actualTime;
    private double delta;

    public static final class Builder {
        private int fps;
        private double timer;
        private long lastTime;
        private long actualTime;
        private double delta;

        public Builder fps(int fps) {
            this.fps = fps;
            return this;
        }

        public Builder timer(double timer) {
            this.timer = timer;
            return this;
        }

        public Builder lastTime(long lastTime) {
            this.lastTime = lastTime;
            return this;
        }

        public Builder actualTime(long actualTime) {
            this.actualTime = actualTime;
            return this;
        }

        public Builder delta(double delta) {
            this.delta = delta;
            return this;
        }

        public RenderTimer build() {
            RenderTimer renderTimer = new RenderTimer();
            renderTimer.LOG = Logger.getLogger(RenderTimer.class.getName());
            renderTimer.fps = this.fps;
            renderTimer.timer = this.timer;
            renderTimer.lastTime = this.lastTime;
            renderTimer.actualTime = this.actualTime;
            renderTimer.delta = this.delta;
            return renderTimer;
        }
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
