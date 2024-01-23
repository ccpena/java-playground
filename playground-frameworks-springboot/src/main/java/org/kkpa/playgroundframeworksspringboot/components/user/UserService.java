package org.kkpa.playgroundframeworksspringboot.components.user;

import components.UserFacade;
import components.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private final UserFacade userFacade;

  @Autowired UserService(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  public Optional<User> save(User user) {
    UserEntity savedUser = userFacade.saveUser(user.toEntity());
    return Optional.of(new User(savedUser));
  }
}
