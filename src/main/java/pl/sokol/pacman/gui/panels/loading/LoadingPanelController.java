package pl.sokol.pacman.gui.panels.loading;

import com.google.gson.Gson;
import lombok.Getter;
import org.apache.log4j.Logger;
import pl.sokol.pacman.elements.dynamic.enemy.Enemy;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.game.Save;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static pl.sokol.pacman.Utils.SAVES_PATH;

@Getter
public class LoadingPanelController implements MouseListener {

    private final Logger LOG = Logger.getLogger(LoadingPanelController.class);

    private LoadingPanelModel model;
    private LoadingPanelView view;

    public LoadingPanelController(GameFrameController game) {
        this.model = new LoadingPanelModel(game);
        this.view = new LoadingPanelView();
        initListeners();
        initLoadingPanel();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel save = (JLabel) e.getSource();
        try {
            loadSave(save.getText());
        } catch (IOException ex) {
            LOG.warn(ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // unused
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // unused
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // unused
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // unused
    }

    private void initListeners() {
        view.getBackToMenuButton().addActionListener(e -> model.getGame().backToMenu());
    }

    private void initLoadingPanel() {

        // get all names of save files
        File folder = new File(SAVES_PATH);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {

                // init UI element
                JPanel save = new JPanel();
                save.setBackground(Color.BLACK);
                JLabel saveName = new JLabel();
                saveName.setForeground(Color.WHITE);
                saveName.addMouseListener(this);
                saveName.setFont(new Font("Arial", Font.BOLD, 16));

                // prepare name of the save
                model.getSavesNames().add(file.toString());
                String name = file.toString().substring(SAVES_PATH.length());
                name = name.substring(0, name.length() - 5);

                // set and add UI of save to the GUI
                saveName.setText(name);
                save.add(saveName);
                view.getLoadingPanel().add(save);
            }
        }
    }

    private void loadSave(String saveName) throws IOException {

        for (String save : model.getSavesNames()) {
            if (save.contains(saveName)) {
                Save gameSave = new Gson().fromJson(new String(Files.readAllBytes(Paths.get(save))), Save.class);
                GamePanelController newGame = new GamePanelController(model.getGame());
                Level newLevel = newGame.getModel().getLevel();
                StatsPanelController newStats = newGame.getModel().getStatsPanel();
                List<Enemy> newEnemies = new ArrayList<>();

                // set player location and current movement
                newLevel.getPlayer().setLocation(gameSave.getPlayerLocation());
                newLevel.getPlayer().getPlayerEngine().setCurrentMovement(gameSave.getPlayerCurrentMovement());

                // set level points and enemies
                newLevel.setPoints(gameSave.getPoints());
                for (int i = 0; i < gameSave.getEnemiesLocations().size(); i++) {
                    newEnemies.add(new Enemy.Builder()
                            .player(newLevel.getPlayer())
                            .level(newLevel)
                            .junctions(newLevel.getJunctions())
                            .currentMovement(gameSave.getEnemiesCurrentMovements().get(i))
                            .numberOfTheImage(gameSave.getEnemiesImageNumbers().get(i))
                            .build(gameSave.getEnemiesLocations().get(i).x, gameSave.getEnemiesLocations().get(i).y));
                }
                newLevel.setEnemies(newEnemies);

                // set stats
                newStats.setModel(gameSave.getStats());
                int livesLeft = newStats.getModel().getNUMBER_OF_LIVES() - newStats.getModel().getLives();
                for (int i = 0; i < livesLeft; i++) {
                    JLabel live = newStats.getLives().remove(newStats.getLives().size() - 1);
                    live.setVisible(false);
                }
                newStats.getView().getScoreLabel().setText(newStats.getScoreTextTemp() + newStats.getModel().getScore());

                // load prepared game
                newGame.getModel().setStatsPanel(newStats);
                newGame.getModel().setLevel(newLevel);
                model.getGame().newGame(newGame);
                break;
            }
        }
    }
}
