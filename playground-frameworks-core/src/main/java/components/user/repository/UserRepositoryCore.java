package components.user.repository;

import components.user.domain.UserEntity;

public interface UserRepositoryCore {
  UserEntity save(UserEntity userEntity);
}
