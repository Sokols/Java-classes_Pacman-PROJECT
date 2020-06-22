package pl.sokol.pacman.gui.panels.settings;

public class SettingsPanelController {

    private SettingsPanelModel model;
    private SettingsPanelView view;

    public SettingsPanelController() {
        this.model = new SettingsPanelModel();
        this.view = new SettingsPanelView();
    }

    public SettingsPanelModel getModel() {
        return model;
    }

    public void setModel(SettingsPanelModel model) {
        this.model = model;
    }

    public SettingsPanelView getView() {
        return view;
    }

    public void setView(SettingsPanelView view) {
        this.view = view;
    }
}
