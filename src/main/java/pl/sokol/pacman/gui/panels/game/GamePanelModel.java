package pl.sokol.pacman.gui.panels.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.frame.GameFrameController;
import pl.sokol.pacman.gui.panels.game.engine.EnginePanelController;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelController;

@Builder
@Getter
@Setter
public class GamePanelModel {

    private GameFrameController gameFrame;
    private Level level;
    private boolean isStoppedFlag;
    private boolean isEndedFlag;
    private EnginePanelController enginePanel;
    private StatsPanelController statsPanel;
    private boolean isPermissionForNewClick;
}
