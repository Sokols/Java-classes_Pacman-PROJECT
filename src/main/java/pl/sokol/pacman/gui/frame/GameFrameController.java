package pl.sokol.pacman.gui.frame;

import com.google.gson.Gson;
import pl.sokol.pacman.Utils;
import pl.sokol.pacman.elements.dynamic.Enemy;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static pl.sokol.pacman.Utils.*;

public class GameFrameController {

    private GameFrameModel model;
    private GameFrameView view;

    public GameFrameController() {
        this.model = new GameFrameModel(
                new MenuPanelController(this)
        );

        this.view = new GameFrameView(
                model.getMenuPanel().getMenuPanelView()
        );
    }

    public void newGame() {
        model.setGamePanel(new GamePanelController(this));
        view.getMainPanel().add(GAME, model.getGamePanel().getView());
        view.getCard().show(view.getMainPanel(), GAME);
        model.getMenuPanel().getMenuPanelView().getSaveButton().setVisible(true);
        model.getMenuPanel().getMenuPanelView().getBackToGameButton().setVisible(true);
        view.addKeyListener(model.getGamePanel());
        Thread thread = new Thread(model.getGamePanel());
        thread.start();
    }

    public void backToMenu() {
        view.removeKeyListener(model.getGamePanel());
        model.getGamePanel().pause();
        view.getCard().show(view.getMainPanel(), MENU);
    }

    public void saveGame() {
        String fileName = Utils.getFileName();
        String folderPath = PATH + fileName + "\\";
        String filePath = folderPath + fileName;
        new File(folderPath).mkdirs();

        // SAVE REMAINING POINTS
        Utils.writeToJSON(filePath, "points", model.getGamePanel().getModel().getLevel().getPoints());

        // SAVE PLAYER LOCATION
        Utils.writeToJSON(filePath, "playerLocation", model.getGamePanel().getModel().getLevel().getPlayer().getLocation());

        // SAVE ENEMIES POSITIONS
        List<Point> enemiesPoints = new ArrayList<>();
        for (Enemy enemy : model.getGamePanel().getModel().getLevel().getEnemies()) {
            enemiesPoints.add(enemy.getLocation());
        }
        Utils.writeToJSON(filePath, "enemiesLocations", enemiesPoints);

        // SAVE STATISTICS
        Utils.writeToJSON(filePath, "stats", model.getGamePanel().getModel().getStatsPanel().getModel());
        backToGame();
    }

    public void backToGame() {
        view.addKeyListener(model.getGamePanel());
        view.getCard().show(view.getMainPanel(), GAME);
        model.getGamePanel().resume();
    }

    public GameFrameView getView() {
        return view;
    }
}
