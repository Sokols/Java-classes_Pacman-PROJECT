package pl.sokol.pacman.game;

public class RenderTimer {

    private final double TARGET_FPS = 60.0;
    private final double NS = 1e9 / TARGET_FPS;

    private int fps;
    private double timer;
    private long lastTime;
    private long actualTime;
    private double delta;

    public RenderTimer(int fps, double timer, long lastTime, long actualTime, double delta) {
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
            System.out.println(fps);
            fps = 0;
            timer += 1000;
        }
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public long getActualTime() {
        return actualTime;
    }

    public void setActualTime(long actualTime) {
        this.actualTime = actualTime;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
