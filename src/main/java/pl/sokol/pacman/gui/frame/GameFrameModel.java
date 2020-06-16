package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.elements.dynamic.Player;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.panels.game.GamePanelViewModel;
import pl.sokol.pacman.gui.panels.stats.StatsPanelViewModel;

public class GameFrameModel {

    private Player player;
    public static Level level;

    private boolean isStoppedFlag;
    private boolean isEndedFlag;

    private GamePanelViewModel gamePanelViewModel;
    private StatsPanelViewModel statsPanelViewModel;

    public GameFrameModel(Player player, Level level, boolean isStoppedFlag, boolean isEndedFlag, GamePanelViewModel gamePanelViewModel, StatsPanelViewModel statsPanelViewModel) {
        this.player = player;
        this.level = level;
        this.isStoppedFlag = isStoppedFlag;
        this.isEndedFlag = isEndedFlag;
        this.gamePanelViewModel = gamePanelViewModel;
        this.statsPanelViewModel = statsPanelViewModel;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static Level getLevel() {
        return level;
    }

    public static void setLevel(Level level) {
        GameFrameModel.level = level;
    }

    public boolean isStoppedFlag() {
        return isStoppedFlag;
    }

    public void setStoppedFlag(boolean stoppedFlag) {
        isStoppedFlag = stoppedFlag;
    }

    public boolean isEndedFlag() {
        return isEndedFlag;
    }

    public void setEndedFlag(boolean endedFlag) {
        isEndedFlag = endedFlag;
    }

    public GamePanelViewModel getGamePanelViewModel() {
        return gamePanelViewModel;
    }

    public void setGamePanelViewModel(GamePanelViewModel gamePanelViewModel) {
        this.gamePanelViewModel = gamePanelViewModel;
    }

    public StatsPanelViewModel getStatsPanelViewModel() {
        return statsPanelViewModel;
    }

    public void setStatsPanelViewModel(StatsPanelViewModel statsPanelViewModel) {
        this.statsPanelViewModel = statsPanelViewModel;
    }
}
