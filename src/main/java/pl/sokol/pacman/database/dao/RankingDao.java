package pl.sokol.pacman.database.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import pl.sokol.pacman.database.Dao;
import pl.sokol.pacman.database.HibernateFactory;
import pl.sokol.pacman.database.domain.Ranking;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class RankingDao implements Dao<Ranking> {

    private final Logger LOG = Logger.getLogger(RankingDao.class);
    private Session session;

    public RankingDao() {
        this.session = new HibernateFactory().getSessionFactory().openSession();
    }

    @Override
    @Transactional
    public Optional<Ranking> get(int id) {
        return Optional.ofNullable(session.find(Ranking.class, id));
    }

    @Override
    @Transactional
    public List<Ranking> getAll() {
        try {
            Query query = session.createQuery("SELECT R FROM Ranking R");
            return query.getResultList();
        } catch (NullPointerException e) {
            LOG.info(e);
        }
        return null;
    }

    @Override
    public void save(Ranking ranking) {
        session.getTransaction().begin();
        session.persist(ranking);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @Transactional
    public void delete(Ranking ranking) {
        session.remove(ranking);
    }
}
