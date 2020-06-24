package pl.sokol.pacman;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;

public class Utils {

    public static final int FRAME_WIDTH = 640;
    public static final int FRAME_HEIGHT = 576;
    public static final int GAME_HEIGHT = 480;
    public static final int STATS_HEIGHT = 64;

    public static final String TITLE = "Pac-Man";
    public static final String GAME = "GAME_PANEL";
    public static final String MENU = "MENU_PANEL";
    public static final String LOADING = "LOADING_PANEL";

    public static final String PATH = "C:\\Users\\asus1\\OneDrive\\Pulpit\\Studia\\SEM4\\JTP\\Projekt\\Project\\src\\main\\resources\\saves\\";

    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName() {
        return LocalDate.now().toString() + "_"
                + LocalTime.now().getHour() + "-"
                + LocalTime.now().getMinute() + "-"
                + LocalTime.now().getSecond();
    }

    public static void writeToJSON(String filePath, Object object) {
        try (Writer writer = new FileWriter(filePath + ".json")) {
            Gson gson = new Gson();
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
