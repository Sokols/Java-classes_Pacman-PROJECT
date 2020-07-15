package pl.sokol.pacman.database.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sokol.pacman.database.Dao;
import pl.sokol.pacman.database.HibernateFactory;
import pl.sokol.pacman.database.domain.Ranking;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public class RankingDao implements Dao<Ranking> {

    private final Logger LOG = Logger.getLogger(RankingDao.class);
    private Session session;

    public RankingDao() {
        this.session = new HibernateFactory().getSessionFactory().openSession();
    }

    public void shutdown() {
        session.close();
    }

    @Override
    public Optional<Ranking> get(int id) {
        return Optional.ofNullable(session.find(Ranking.class, id));
    }

    @Override
    public ArrayList<Ranking> getAll() {
        Query query = session.createNativeQuery("SELECT * FROM Ranking", Ranking.class);
        return (ArrayList<Ranking>) query.getResultList();
    }

    @Override
    public void save(Ranking ranking) {
        executeInsideTransaction(session -> session.persist(ranking));
    }

    @Override
    public void delete(Ranking ranking) {
        executeInsideTransaction(session -> session.remove(ranking));
    }

    private void executeInsideTransaction(Consumer<Session> action) {
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            action.accept(session);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            LOG.warn(e);
        }
    }
}
