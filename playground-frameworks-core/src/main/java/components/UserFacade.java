package components;

import components.user.domain.UserEntity;
import components.user.repository.UserRepoJooq;
import components.user.service.UserServiceCore;
import config.JooqConfig;

public class UserFacade {

  public UserEntity saveUser(UserEntity input) {

    try {
      String url = "jdbc:postgresql://localhost:5432/framework_test";

//    SessionFactory sessionFactory = SessionFactoryManager.getSessionFactory();
      //UserServiceCore userServiceCore = new UserServiceCore(new UserRepositoryCoreImpl(sessionFactory));
      UserServiceCore userServiceCore = new UserServiceCore(new UserRepoJooq(new JooqConfig(url, "postgres", "postgres")));
      return userServiceCore.create(input);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
