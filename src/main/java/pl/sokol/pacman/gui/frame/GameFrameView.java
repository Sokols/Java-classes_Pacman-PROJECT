package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelView;
import pl.sokol.pacman.gui.panels.game.engine.EnginePanelView;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelView;
import pl.sokol.pacman.gui.panels.menu.MenuPanelView;
import pl.sokol.pacman.gui.panels.settings.SettingsPanelView;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.CardLayout;

public class GameFrameView extends JFrame {

    private static final String TITLE = "Pac-Man";
    private final String GAME = "GAME_PANEL";
    private final String MENU = "MENU_PANEL";
    private final String SETTINGS = "SETTINGS_PANEL";

    public GameFrameView(GamePanelView game, MenuPanelView menu, SettingsPanelView settings) {
        super(TITLE);

        setSize(EnginePanelView.GAME_WIDTH, EnginePanelView.GAME_HEIGHT + StatsPanelView.STATS_HEIGHT + 32);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        CardLayout card = new CardLayout();
        this.setLayout(card);
        this.add(game, GAME);
        this.add(menu, MENU);
        this.add(settings, SETTINGS);

        //card.show(this.getContentPane(), GAME);
    }
}
