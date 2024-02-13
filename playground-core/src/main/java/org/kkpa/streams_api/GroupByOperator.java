package org.kkpa.streams_api;

import org.kkpa.pgutil.models.user.Item;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupByOperator {
  public static void main(String[] args) {
    List<Item> items = Arrays.asList(
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("apple", 20, new BigDecimal("9.99")),
            new Item("banana", 20, new BigDecimal("19.99")),
            new Item("banana", 10, new BigDecimal("19.99")),
            new Item("papaya", 20, new BigDecimal("9.99"))
    );

    System.out.println("groupByNameAndCount: " + groupByNameAndCount(items));
    System.out.println("groupByNameAndSumThem: " + groupByNameAndSumThem(items));
    System.out.println("groupByPriceWithUniqueNames: " + groupByPriceWithUniqueNames(items));
  }

  private static Map<String, Long> groupByNameAndCount(List<Item> items) {
    return items.stream()
            .collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
  }

  private static Map<String, Long> groupByNameAndSumThem(List<Item> items) {
    return items.stream()
            .collect(Collectors.groupingBy(Item::getName, Collectors.summingLong(value -> value.getQuantity())));
  }

  private static Map<BigDecimal, Set<String>> groupByPriceWithUniqueNames(List<Item> items) {
    return items.stream().collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));
  }
}
