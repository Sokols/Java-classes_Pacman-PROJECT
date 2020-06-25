package pl.sokol.pacman.gui.panels.game.engine;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import java.awt.Color;
import java.awt.Graphics;

import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.GAME_HEIGHT;

public class EnginePanelController {

    private EnginePanelModel model;
    private EnginePanelView view;

    public EnginePanelController(GamePanelController gameThread) {
        this.model = new EnginePanelModel(gameThread);
        this.view = new EnginePanelView();
    }

    public void renderGame(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, GAME_HEIGHT);
        model.getGamePanel().getModel().getLevel().render(g);
        g.dispose();
    }

    public EnginePanelView getView() {
        return view;
    }
}
