package pl.sokol.pacman.gui.panels.endgame;

import lombok.Getter;
import org.hibernate.Session;
import pl.sokol.pacman.database.HibernateFactory;
import pl.sokol.pacman.database.domain.Ranking;
import pl.sokol.pacman.gui.frame.GameFrameController;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.time.Instant;
import java.util.Date;

@Getter
public class EndgamePanelController {

    private EndgamePanelModel model;
    private EndgamePanelView view;

    public EndgamePanelController(GameFrameController game, String title, int score) {
        this.model = new EndgamePanelModel(game, score);
        this.view = new EndgamePanelView(title, score);
        initListeners();
    }

    private void initListeners() {
        view.getApplyButton().addActionListener(e -> goToRanking());
    }

    private void goToRanking() {
        if (view.getNickTextField().getText().isEmpty()) {
            displayWarningMessage();
        } else {
            saveRanking();
            // go to ranking view
            model.getGame().showRanking();
        }
    }

    private void saveRanking() {
        Ranking ranking = Ranking.builder()
                .nick(view.getNickTextField().getText())
                .points(model.getScore())
                .date(Date.from(Instant.now())).build();
        Session session = new HibernateFactory().getSessionFactory().openSession();
        session.getTransaction().begin();
        session.persist(ranking);
        session.getTransaction().commit();
        session.close();
    }

    private void displayWarningMessage() {
        JFrame warning = new JFrame();
        warning.setBackground(Color.BLACK);
        warning.setForeground(Color.WHITE);
        JOptionPane.showMessageDialog(warning, "Nick cannot be empty!", "WARNING", JOptionPane.WARNING_MESSAGE);
    }
}

