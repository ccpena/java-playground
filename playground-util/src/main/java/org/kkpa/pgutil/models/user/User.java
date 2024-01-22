package org.kkpa.pgutil.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String role;
  private String status;
  private int age;
  private long salary;

  @Singular
  private List<Address> addresses;

  @Singular
  private List<Order> orders;

  @Singular
  private List<PaymentMethod> paymentMethods;

  private Profile profile;

}

