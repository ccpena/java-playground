package org.kkpa.streams_api;

import org.kkpa.pgutil.models.user.User;

import java.util.List;

public class ReducerOperator {
  public User findOldestUser(List<User> allUsers) {
    return allUsers.stream()
            .reduce((user1, user2) -> user1.getAge() > user2.getAge() ? user1 : user2)
            .get();
  }

}
