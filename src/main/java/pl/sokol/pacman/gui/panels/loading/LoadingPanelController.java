package pl.sokol.pacman.gui.panels.loading;

import com.google.gson.Gson;
import pl.sokol.pacman.game.Save;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.GamePanelController;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static pl.sokol.pacman.Utils.PATH;

public class LoadingPanelController implements MouseListener {

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
        loadSave(save.getText());
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
        File folder = new File(PATH);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                model.getSavesNames().add(file.toString());
                JPanel save = new JPanel();
                save.setBackground(Color.BLACK);
                JLabel saveName = new JLabel();
                saveName.setForeground(Color.WHITE);
                String name = file.toString().substring(PATH.length());
                name = name.substring(0, name.length() - 5);
                saveName.setText(name);
                save.add(saveName);
                saveName.addMouseListener(this);
                view.getLoadingPanel().add(save);
            }
        }
    }

    private void loadSave(String saveName) {
        for (String save : model.getSavesNames()) {
            if (save.contains(saveName)) {
                Gson gson = new Gson();
                String content = null;
                try {
                    content = new String(Files.readAllBytes(Paths.get(save)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (content != null) {
                    Save gameSave = gson.fromJson(content, Save.class);
                    GamePanelController newGame = new GamePanelController(model.getGame());
                    newGame.getModel().getLevel().setPoints(gameSave.getPoints());
                    newGame.getModel().getLevel().getPlayer().setLocation(gameSave.getPlayerLocation());
                    newGame.getModel().getLevel().getPlayer().setCurrentMovement(gameSave.getPlayerCurrentMovement());
                    for (int i = 0; i < gameSave.getEnemiesLocations().size(); i++) {
                        newGame.getModel().getLevel().getEnemies().get(i).setLocation(gameSave.getEnemiesLocations().get(i));
                        newGame.getModel().getLevel().getEnemies().get(i).setCurrentMovement(gameSave.getEnemiesCurrentMovements().get(i));
                        newGame.getModel().getLevel().getEnemies().get(i).setImageOfEnemyByNumber(gameSave.getEnemiesImageNumbers().get(i));
                    }
                    newGame.getModel().getStatsPanel().setModel(gameSave.getStats());

                    int livesLeft = newGame.getModel().getStatsPanel().getModel().getNUMBER_OF_LIVES() - newGame.getModel().getStatsPanel().getModel().getLives();
                    for (int i = 0; i < livesLeft; i++) {
                        JLabel live = newGame.getModel().getStatsPanel().getLives().remove(newGame.getModel().getStatsPanel().getLives().size() - 1);
                        live.setVisible(false);
                    }
                    model.getGame().newGame(newGame);
                }
                break;
            }
        }
    }

    public LoadingPanelModel getModel() {
        return model;
    }

    public void setModel(LoadingPanelModel model) {
        this.model = model;
    }

    public LoadingPanelView getView() {
        return view;
    }

    public void setView(LoadingPanelView view) {
        this.view = view;
    }

}
