package org.kkpa.pgutil.builders;

import org.kkpa.pgutil.models.user.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserBuilder implements UniversalModelBuilder<User> {

  public static final String EMAIL_EXT = "@xxx.com";
  private final AddressBuilder addressBuilder;

  public UserBuilder(AddressBuilder addressBuilder) {
    this.addressBuilder = addressBuilder;
  }

  @Override public List<User> randomData(int n) {
    return IntStream.iterate(1, index -> index + 1)
            .parallel()
            .mapToObj(id -> singleModel(id))
            .limit(n)
            .collect(Collectors.toList());
  }

  @Override public User singleModel(long id) {
    Random random = new Random();
    User user = User.builder()
            .id(id)
            .firstName(generateRandomText(4))
            .age(random.nextInt(100))
            .salary(id * 100)
            .lastName(generateRandomText(7))
            .email(generateRandomText(6).concat(EMAIL_EXT))
            .address(addressBuilder.singleModel(id))
            .build();

    return user;
  }
}
