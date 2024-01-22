package org.kkpa.pgutil.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PaymentMethod {
  private String type;
  private String cardNumber; // Store securely in production
  private String expirationDate;
  private Address billingAddress;
}
