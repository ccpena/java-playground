package config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceUnit;
import java.io.File;

@PersistenceUnit
public class SessionFactoryManager {
  private static final SessionFactory sessionFactory = new Configuration()
          .configure(new File("playground-frameworks-core/src/main/resources/hibernate.cfg.xml"))
          .buildSessionFactory();

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}

