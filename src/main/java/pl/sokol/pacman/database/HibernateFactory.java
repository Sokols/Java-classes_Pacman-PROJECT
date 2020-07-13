package pl.sokol.pacman.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateFactory {

    public SessionFactory getSessionFactory() {

//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        return configuration.buildSessionFactory(standardServiceRegistryBuilder.build());

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
