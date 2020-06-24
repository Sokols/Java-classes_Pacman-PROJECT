package pl.sokol.pacman.gui.panels.endgame;

import pl.sokol.pacman.gui.frame.GameFrameController;

public class EndgamePanelController {

    private EndgamePanelModel model;
    private EndgamePanelView view;

    public EndgamePanelController(GameFrameController game, String title, int score) {
        this.model = new EndgamePanelModel(game, title, score);
        this.view = new EndgamePanelView(title, score);
        initListeners();
    }

    private void initListeners() {
        view.getBackToMenuButton().addActionListener(e -> model.getGame().backToMenu());
    }

    public EndgamePanelView getView() {
        return view;
    }
}

