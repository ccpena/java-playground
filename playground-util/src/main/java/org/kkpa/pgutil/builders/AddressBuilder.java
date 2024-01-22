package org.kkpa.pgutil.builders;

import org.kkpa.pgutil.models.user.Address;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AddressBuilder implements UniversalModelBuilder<Address> {
  private Random random = new Random();

  @Override public List<Address> randomData(int n) {
    return null;
  }

  @Override public Address singleModel(long id) {
    return Address.builder()
            .id(Optional.of(id))
            .city(Optional.of(generateRandomText(10)))
            .build();
  }
}
