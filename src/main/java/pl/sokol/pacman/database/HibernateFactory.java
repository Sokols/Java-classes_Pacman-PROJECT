package pl.sokol.pacman.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
