package pl.sokol.pacman;

import org.hibernate.Session;
import pl.sokol.pacman.database.HibernateFactory;
import pl.sokol.pacman.database.domain.Ranking;
import pl.sokol.pacman.gui.frame.GameFrameController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Utils.configureLogger();
        new GameFrameController();

        Session session = new HibernateFactory().getSessionFactory().openSession();

        Ranking ranking = Ranking.builder()
                .nick("nice")
                .points(3)
                .build();

        session.getTransaction().begin();
        session.persist(ranking);
        session.getTransaction().commit();

        session.close();
    }
}
