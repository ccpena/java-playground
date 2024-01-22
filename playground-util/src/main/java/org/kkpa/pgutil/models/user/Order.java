package org.kkpa.pgutil.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Order {
  private Long orderId;
  private List<Item> items; // Assuming a separate Item class for purchased items
  private BigDecimal totalPrice;
  private LocalDateTime orderDate;
  private Address shippingAddress;
  private Address billingAddress;
}
