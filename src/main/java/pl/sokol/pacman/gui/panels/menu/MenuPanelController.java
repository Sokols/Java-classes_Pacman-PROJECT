package pl.sokol.pacman.gui.panels.menu;

public class MenuPanelController {

    private MenuPanelModel model;
    private MenuPanelView view;

    public MenuPanelController() {
        this.model = new MenuPanelModel();
        this.view = new MenuPanelView();
    }

    public MenuPanelModel getModel() {
        return model;
    }

    public void setModel(MenuPanelModel model) {
        this.model = model;
    }

    public MenuPanelView getView() {
        return view;
    }

    public void setView(MenuPanelView view) {
        this.view = view;
    }
}
