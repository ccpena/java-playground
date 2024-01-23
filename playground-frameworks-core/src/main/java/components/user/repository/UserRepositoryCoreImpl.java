package components.user.repository;

import components.user.domain.UserEntity;
import config.SessionFactoryManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserRepositoryCoreImpl implements UserRepositoryCore {

  private final SessionFactory sessionFactory;

  public UserRepositoryCoreImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override public UserEntity save(UserEntity userEntity) {
    Session session = sessionFactory.getCurrentSession();
    SessionFactory sessionFactory1 = SessionFactoryManager.getSessionFactory();
    Session session2 = sessionFactory1.openSession();
    try {
      session.save(userEntity);
    } finally {
      session2.close();
      session.close();
    }
    sessionFactory.close();
    sessionFactory1.close();
    return userEntity;
  }
}
