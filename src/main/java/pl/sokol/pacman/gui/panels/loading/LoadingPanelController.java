package pl.sokol.pacman.gui.panels.loading;

public class LoadingPanelController {

    private LoadingPanelModel model;
    private LoadingPanelView view;

    public LoadingPanelController() {
        this.model = new LoadingPanelModel();
        this.view = new LoadingPanelView();
    }

    public LoadingPanelModel getModel() {
        return model;
    }

    public void setModel(LoadingPanelModel model) {
        this.model = model;
    }

    public LoadingPanelView getView() {
        return view;
    }

    public void setView(LoadingPanelView view) {
        this.view = view;
    }
}
