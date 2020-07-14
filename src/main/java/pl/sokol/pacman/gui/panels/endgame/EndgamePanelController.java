package pl.sokol.pacman.gui.panels.endgame;

import pl.sokol.pacman.database.dao.RankingDao;
import pl.sokol.pacman.database.domain.Ranking;
import pl.sokol.pacman.gui.frame.GameFrameController;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class EndgamePanelController {

    private EndgamePanelModel model;
    private EndgamePanelView view;

    public EndgamePanelController(GameFrameController game, String title, int score) {
        this.model = new EndgamePanelModel(game, score);
        this.view = new EndgamePanelView(title, score);
        initListeners();
    }

    public EndgamePanelView getView() {
        return view;
    }

    private void initListeners() {
        view.getApplyButton().addActionListener(e -> goToRanking());
    }

    private void goToRanking() {
        if (view.getNickTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Nick cannot be empty!", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else {
            Ranking ranking = Ranking.builder()
                    .nick(view.getNickTextField().getText())
                    .points(model.getScore())
                    .date(Date.from(Instant.now())).build();
            RankingDao rankingDao = new RankingDao();
            rankingDao.save(ranking);
            model.getGame().showRanking();
        }
    }
}

