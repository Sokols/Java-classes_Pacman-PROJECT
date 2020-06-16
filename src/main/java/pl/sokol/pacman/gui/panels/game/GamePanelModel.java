package pl.sokol.pacman.gui.panels.game;

import pl.sokol.pacman.gui.frame.GameFrameViewModel;

public class GamePanelModel {

    private GameFrameViewModel gameThread;

    public GamePanelModel(GameFrameViewModel gameThread) {
        this.gameThread = gameThread;
    }

    public GameFrameViewModel getGameThread() {
        return gameThread;
    }

    public void setGameThread(GameFrameViewModel gameThread) {
        this.gameThread = gameThread;
    }
}
