package pl.sokol.pacman;

import org.apache.log4j.xml.DOMConfigurator;

public class Utils {

    // static app sizes
    public static final int FRAME_WIDTH = 640;
    public static final int FRAME_HEIGHT = 576;
    public static final int GAME_HEIGHT = 480;
    public static final int STATS_HEIGHT = 64;

    // static panel names
    public static final String TITLE = "Pac-Man";
    public static final String GAME = "GAME_PANEL";
    public static final String MENU = "MENU_PANEL";
    public static final String LOADING = "LOADING_PANEL";
    public static final String ENDGAME = "ENDGAME_PANEL";

    public static final String SAVES_PATH = "C:\\Users\\asus1\\OneDrive\\Pulpit\\Studia\\SEM4\\JTP\\Projekt\\Project\\src\\main\\resources\\saves\\";

    static void configureLogger() {
        DOMConfigurator.configure("C:\\Users\\asus1\\OneDrive\\Pulpit\\Studia\\SEM4\\JTP\\Projekt\\Project\\src\\main\\resources\\log4j2.xml");
    }
}
