package pl.sokol.pacman.gui.panels.game.engine;

import pl.sokol.pacman.gui.panels.game.GamePanelController;

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

    public void renderGame(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, FRAME_WIDTH, GAME_HEIGHT);
        model.getGamePanel().getModel().getLevel().render(graphics);
    }

    public EnginePanelView getView() {
        return view;
    }
}
