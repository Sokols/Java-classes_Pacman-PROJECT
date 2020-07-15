package pl.sokol.pacman.gui.frame;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import pl.sokol.pacman.elements.dynamic.Enemy;
import pl.sokol.pacman.game.Save;
import pl.sokol.pacman.gui.panels.endgame.EndgamePanelController;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.GamePanelModel;
import pl.sokol.pacman.gui.panels.loading.LoadingPanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;
import pl.sokol.pacman.gui.panels.ranking.RankingPanelController;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static pl.sokol.pacman.Utils.ENDGAME;
import static pl.sokol.pacman.Utils.GAME;
import static pl.sokol.pacman.Utils.LOADING;
import static pl.sokol.pacman.Utils.MENU;
import static pl.sokol.pacman.Utils.RANKING;
import static pl.sokol.pacman.Utils.SAVES_PATH;

public class GameFrameController {

    private final Logger LOG = Logger.getLogger(GameFrameController.class);
    private GameFrameModel model;
    private GameFrameView view;

    public GameFrameController() {
        this.model = new GameFrameModel(
                null,
                new MenuPanelController(this)
        );

        this.view = new GameFrameView(
                model.getMenuPanel().getMenuPanelView()
        );
    }

    public void backToMenu() {
        view.getCard().show(view.getMainPanel(), MENU);
    }

    public void goToMenu() {
        view.removeKeyListener(model.getGamePanel());
        model.getGamePanel().pause();
        view.getCard().show(view.getMainPanel(), MENU);
    }

    public void backToGame() {
        view.addKeyListener(model.getGamePanel());
        view.getCard().show(view.getMainPanel(), GAME);
        model.getGamePanel().resume();
    }

    public void newGame(GamePanelController newGame) {
        if (model.getGamePanel() != null) {
            model.getGamePanel().getModel().setEndedFlag(true);
        }
        model.setGamePanel(newGame);
        view.getMainPanel().add(GAME, model.getGamePanel().getView());
        view.getCard().show(view.getMainPanel(), GAME);
        model.getMenuPanel().getMenuPanelView().getSaveButton().setVisible(true);
        model.getMenuPanel().getMenuPanelView().getBackToGameButton().setVisible(true);
        view.addKeyListener(model.getGamePanel());
        Thread thread = new Thread(model.getGamePanel());
        thread.start();
    }

    public void saveGame() {
        String fileName = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());
        String filePath = SAVES_PATH + fileName;
        List<Point> enemiesPoints = new ArrayList<>();
        List<Integer> enemiesCurrentMovements = new ArrayList<>();
        List<Integer> enemiesNumberOfTheImages = new ArrayList<>();
        GamePanelModel gameModel = model.getGamePanel().getModel();

        // SAVE ENEMIES DATA
        for (Enemy enemy : gameModel.getLevel().getEnemies()) {
            enemiesPoints.add(enemy.getLocation());
            enemiesCurrentMovements.add(enemy.getCurrentMovement());
            enemiesNumberOfTheImages.add(enemy.getNumberOfTheImage());
        }

        // CREATE SAVE
        Save save = Save.builder()
                .points(gameModel.getLevel().getPoints())
                .playerLocation(gameModel.getLevel().getPlayer().getLocation())
                .playerCurrentMovement(gameModel.getLevel().getPlayer().getCurrentMovement())
                .enemiesLocations(enemiesPoints)
                .enemiesCurrentMovements(enemiesCurrentMovements)
                .enemiesImageNumbers(enemiesNumberOfTheImages)
                .stats(gameModel.getStatsPanel().getModel())
                .build();

        // SAVE TO FILE
        try (Writer writer = new FileWriter(filePath + ".json")) {
            new Gson().toJson(save, writer);
        } catch (IOException e) {
            LOG.warn(e);
        }
        backToGame();
    }

    public void loadGame() {
        model.setLoadingPanel(new LoadingPanelController(this));
        view.getMainPanel().add(LOADING, model.getLoadingPanel().getView());
        view.getCard().show(view.getMainPanel(), LOADING);
    }

    public void endGame(String title, int score) {
        model.getMenuPanel().getMenuPanelView().getSaveButton().setVisible(false);
        model.getMenuPanel().getMenuPanelView().getBackToGameButton().setVisible(false);
        model.getGamePanel().getModel().setEndedFlag(true);
        view.getMainPanel().add(ENDGAME, new EndgamePanelController(this, title, score).getView());
        view.getCard().show(view.getMainPanel(), ENDGAME);
    }

    public void showRanking() {
        model.setRankingPanel(new RankingPanelController(this));
        view.getMainPanel().add(RANKING, model.getRankingPanel().getView());
        view.getCard().show(view.getMainPanel(), RANKING);
    }

    public GameFrameView getView() {
        return view;
    }
}
