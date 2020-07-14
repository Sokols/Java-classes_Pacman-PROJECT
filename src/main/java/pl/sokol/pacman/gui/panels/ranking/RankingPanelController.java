package pl.sokol.pacman.gui.panels.ranking;

import pl.sokol.pacman.database.dao.RankingDao;
import pl.sokol.pacman.database.domain.Ranking;
import pl.sokol.pacman.gui.frame.GameFrameController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RankingPanelController {

    private RankingPanelModel model;
    private RankingPanelView view;

    public RankingPanelController(GameFrameController game) {
        this.model = new RankingPanelModel();
        this.view = new RankingPanelView();
        initListeners(game);
        initRankingPanel();
    }

    private void initListeners(GameFrameController game) {
        view.getBackToMenuButton().addActionListener(e -> game.backToMenu());
    }

    private void initRankingPanel() {
        RankingDao rankingDao = new RankingDao();
        List<Ranking> rankings = rankingDao.getAll();
        if (rankings == null) {
            JLabel emptyLabel = new JLabel("Empty");
            emptyLabel.setForeground(Color.WHITE);
            view.remove(view.getRankingTable());
            view.getRankingPanel().add(emptyLabel);
        } else {
            DefaultTableModel model = initRankingTable();
            // sort list by highest score
            rankings.sort((o1, o2) -> Integer.compare(o2.getPoints(), o1.getPoints()));
            for (int i = 0; i < rankings.size(); i++) {
                model.addRow(
                        new Object[]{
                                i + 1,
                                rankings.get(i).getNick(),
                                rankings.get(i).getPoints(),
                                rankings.get(i).getDate()
                        }
                );
            }
        }
    }

    private DefaultTableModel initRankingTable() {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{
                        "Position", "Nick", "Score", "Date"
                }, 0);
        view.getRankingTable().setModel(model);
        return model;
    }

    public RankingPanelView getView() {
        return view;
    }
}
