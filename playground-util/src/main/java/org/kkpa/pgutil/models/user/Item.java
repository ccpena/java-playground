package org.kkpa.pgutil.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

  private Long id;
  private String name;

  private String description;
  private BigDecimal price;
  private int quantity;
  private String imageUrl; // Optional for visual representation


  public Item(String name, int quantity, BigDecimal price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }
}
