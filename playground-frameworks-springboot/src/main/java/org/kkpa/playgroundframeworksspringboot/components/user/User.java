package org.kkpa.playgroundframeworksspringboot.components.user;

import components.user.domain.UserEntity;
import lombok.Data;

@Data
public class User {

  private Long id;
  private String name;

  public User() {
    super();
  }

  public User(UserEntity userEntity) {
    this.name = userEntity.getName();
    this.id = userEntity.getId();
  }

  public UserEntity toEntity() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(this.id);
    userEntity.setName(this.name);
    return userEntity;
  }
}
