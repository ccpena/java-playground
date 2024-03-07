package org.kkpa.streams_api;

import org.kkpa.pgutil.models.user.User;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReducerOperator {
  public static BinaryOperator<User> oldestComparison() {
    return (user1, user2) -> user1.getAge() > user2.getAge() ? user1 : user2;
  }

  private static BinaryOperator<String> nameComparison() {
    return (name1, name2) -> name1.compareTo(name2) <= 1 ? name1 : name2;
  }

  public User findOldestUser(List<User> allUsers) {
    return allUsers.stream()
            .reduce(oldestComparison())
            .get();
  }

  public String firstNameOf(Stream<String> names) {
    return names.reduce(nameComparison()).get();
  }
}
