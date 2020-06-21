package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.frames.game.GameFrameController;
import pl.sokol.pacman.gui.panels.stats.StatsPanelController;

import java.awt.Color;
import java.awt.Graphics;

import static pl.sokol.pacman.gui.panels.game.GamePanelView.GAME_HEIGHT;
import static pl.sokol.pacman.gui.panels.game.GamePanelView.GAME_WIDTH;

public class GamePanelController {

    private GamePanelView view;
    private GamePanelModel model;

    public GamePanelController(GameFrameController gameThread, StatsPanelController statsPanelController) {
        this.model = new GamePanelModel(gameThread, statsPanelController);
        this.view = new GamePanelView();
    }

    public void renderGame(Graphics g) {
        // fill the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        model.getGameThread().getModel().getLevel().render(g);
        g.dispose();
    }

    public GamePanelView getView() {
        return view;
    }
}
