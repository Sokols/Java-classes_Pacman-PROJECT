package pl.sokol.pacman;

import org.hibernate.Session;
import pl.sokol.pacman.database.HibernateFactory;
import pl.sokol.pacman.database.domain.Ranking;
import pl.sokol.pacman.gui.frame.GameFrameController;

public class Main {

    public static void main(String[] args) {
        Utils.configureLogger();
//        Session session = new HibernateFactory().getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.persist(Ranking.builder()
//                .nick("nf")
//                .points(42).build());
//        session.getTransaction().commit();
//        session.close();
        new GameFrameController();
    }
}
