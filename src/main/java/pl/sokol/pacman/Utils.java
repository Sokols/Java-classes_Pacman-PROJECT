package pl.sokol.pacman;

import org.apache.log4j.xml.DOMConfigurator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {

    public static final int FRAME_WIDTH = 640;
    public static final int FRAME_HEIGHT = 576;
    public static final int GAME_HEIGHT = 480;
    public static final int STATS_HEIGHT = 64;

    public static final String TITLE = "Pac-Man";
    public static final String GAME = "GAME_PANEL";
    public static final String MENU = "MENU_PANEL";
    public static final String LOADING = "LOADING_PANEL";
    public static final String ENDGAME = "ENDGAME_PANEL";

    public static final String SAVES_PATH = "C:\\Users\\asus1\\OneDrive\\Pulpit\\Studia\\SEM4\\JTP\\Projekt\\Project\\src\\main\\resources\\saves\\";

    public static String getFileName() {
        String pattern = "dd-MM-yyyy_HH-mm-ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date now = Calendar.getInstance().getTime();
        return df.format(now);
    }

    static void configureLogger() {
        DOMConfigurator.configure("C:\\Users\\asus1\\OneDrive\\Pulpit\\Studia\\SEM4\\JTP\\Projekt\\Project\\src\\main\\resources\\log4j2.xml");
    }

    public static Random random() {
        return new Random();
    }
}
