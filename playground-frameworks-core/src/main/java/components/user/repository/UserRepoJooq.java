package components.user.repository;

import components.user.domain.UserEntity;
import config.JooqConfig;
import org.jooq.DSLContext;

public class UserRepoJooq implements UserRepositoryCore {

  private final DSLContext dslContext;

  public UserRepoJooq(JooqConfig jooqConfig) {
    this.dslContext = jooqConfig.createDSLContext();
  }

  @Override public UserEntity save(UserEntity userEntity) {
    System.out.println("Saving User with Jooq..." + userEntity);
    return null;
  }
}
