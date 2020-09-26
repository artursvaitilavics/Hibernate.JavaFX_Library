package configuration;

import entity.Book;
import entity.Review;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import entity.Author;

import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;

public class DbSessionHolder {

    private static final List<Class> ENTITIES = asList(Author.class, Book.class, Review.class);

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "A0B1C2_ZZ_$aatttfff";

    //H2 - in memory database
//    private static final String DB_URL = "jdbc:h2:~/test";
//    private static final String DB_DRIVER = "org.h2.Driver";
//    private static final String DB_DIALECT = "org.hibernate.dialect.H2Dialect";

    //MySql

     private static final String DB_URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
     private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
     private static final String DB_DIALECT = "org.hibernate.dialect.MySQL5Dialect";

    private static SessionFactory sessionFactory;

    private DbSessionHolder() {
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getInstance().close();
    }

    private static class LazyHolder {
        static final SessionFactory INSTANCE = initializeSessionFactory();
    }

    private static SessionFactory initializeSessionFactory() {
        try {
            Configuration configuration = configuration(properties());
            sessionFactory = configuration.buildSessionFactory(serviceRegistry(configuration));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    private static Configuration configuration(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        ENTITIES.forEach(configuration::addAnnotatedClass);
        return configuration;
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DB_DRIVER);
        properties.put(Environment.URL, DB_URL);
        properties.put(Environment.USER, DB_USERNAME);
        properties.put(Environment.PASS, DB_PASSWORD);
        properties.put(Environment.DIALECT, DB_DIALECT);
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        return properties;
    }

    private static ServiceRegistry serviceRegistry(Configuration configuration) {
        return new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
    }
}
