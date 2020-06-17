package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.frame.GameFrameViewModel;

import java.awt.Color;
import java.awt.Graphics;

import static pl.sokol.pacman.gui.panels.game.GamePanelView.GAME_HEIGHT;
import static pl.sokol.pacman.gui.panels.game.GamePanelView.GAME_WIDTH;

public class GamePanelViewModel {

    private GamePanelView view;
    private GamePanelModel model;

    public GamePanelViewModel(GameFrameViewModel gameThread) {
        this.model = new GamePanelModel(gameThread);
        this.view = new GamePanelView();
    }

    public void renderGame(Graphics g) {
        // fill the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        model.getGameThread().getModel().getPlayer().render(g);
        model.getGameThread().getModel().getLevel().render(g);
        g.dispose();
    }

    public GamePanelView getView() {
        return view;
    }
}
