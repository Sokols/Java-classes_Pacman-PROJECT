package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.game.GamePanelController;
import pl.sokol.pacman.gui.panels.menu.MenuPanelController;
import pl.sokol.pacman.gui.panels.settings.SettingsPanelController;

public class GameFrameModel {

    private GamePanelController game;
    private MenuPanelController menu;
    private SettingsPanelController settings;

    public GameFrameModel(GamePanelController game, MenuPanelController menu, SettingsPanelController settings) {
        this.game = game;
        this.menu = menu;
        this.settings = settings;
    }

    public GamePanelController getGame() {
        return game;
    }

    public void setGame(GamePanelController game) {
        this.game = game;
    }

    public MenuPanelController getMenu() {
        return menu;
    }

    public void setMenu(MenuPanelController menu) {
        this.menu = menu;
    }

    public SettingsPanelController getSettings() {
        return settings;
    }

    public void setSettings(SettingsPanelController settings) {
        this.settings = settings;
    }
}
