package org.kkpa.pgutil.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private Optional<Long> id;
  private Optional<String> address1;
  private Optional<String> city;
}
