package components.user.service;

import components.CrudOperationsCore;
import components.user.domain.UserEntity;
import components.user.repository.UserRepositoryCore;

import javax.transaction.Transactional;

public class UserServiceCore implements CrudOperationsCore<UserEntity> {

  private final UserRepositoryCore userRepositoryCore;

  public UserServiceCore(UserRepositoryCore userRepositoryCore) {
    this.userRepositoryCore = userRepositoryCore;
  }

  @Transactional
  @Override public UserEntity create(UserEntity input) {
    System.out.println("Saving User .. " + input);
    return this.userRepositoryCore.save(input);
  }

  @Override public UserEntity update(UserEntity input) {
    return null;
  }

  @Override public void delete(Long id) {

  }

  @Override public UserEntity read(Long id) {
    return null;
  }
}
