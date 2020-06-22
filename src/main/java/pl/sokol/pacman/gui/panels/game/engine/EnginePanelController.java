package pl.sokol.pacman.gui.panels.game.engine;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

import java.awt.*;

public class EnginePanelController {

    private EnginePanelView view;
    private EnginePanelModel model;

    public EnginePanelController(GamePanelController gameThread, StatsPanelController statsPanelController) {
        this.model = new EnginePanelModel(gameThread, statsPanelController);
        this.view = new EnginePanelView();
    }

    public void renderGame(Graphics g) {
        // fill the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, EnginePanelView.GAME_WIDTH, EnginePanelView.GAME_HEIGHT);
        model.getGameThread().getModel().getLevel().render(g);
        g.dispose();
    }

    public EnginePanelView getView() {
        return view;
    }
}
