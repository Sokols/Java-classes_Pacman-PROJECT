package pl.sokol.pacman;

public class Utils {

    public static final int FRAME_WIDTH = 640;
    public static final int FRAME_HEIGHT = 576;
    public static final int GAME_HEIGHT = 480;
    public static final int STATS_HEIGHT = 64;

    public static final String TITLE = "Pac-Man";
    public static final String GAME = "GAME_PANEL";
    public static final String MENU = "MENU_PANEL";
    public static final String SETTINGS = "SETTINGS_PANEL";

    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
