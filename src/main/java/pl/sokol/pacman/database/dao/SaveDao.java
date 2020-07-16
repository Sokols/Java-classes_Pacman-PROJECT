package pl.sokol.pacman.database.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sokol.pacman.database.Dao;
import pl.sokol.pacman.database.HibernateFactory;
import pl.sokol.pacman.database.domain.Save;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SaveDao implements Dao<Save> {

    private Logger LOG = Logger.getLogger(SaveDao.class);
    private Session session;

    public SaveDao() {
        this.session = new HibernateFactory().getSessionFactory().openSession();
    }

    public void shutdown() {
        session.close();
    }

    @Override
    public Save get(int id) {
        Save save = session.find(Save.class, id);
        if (save != null) {
            return session.find(Save.class, id);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Save> getAll() {
        Query query = session.createNativeQuery("SELECT * FROM Save", Save.class);
        return (ArrayList<Save>) query.getResultList();
    }

    @Override
    public void save(Save save) {
        executeInsideTransaction(session -> session.persist(save));
    }

    @Override
    public void delete(Save save) {
        executeInsideTransaction(session -> session.remove(save));
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
