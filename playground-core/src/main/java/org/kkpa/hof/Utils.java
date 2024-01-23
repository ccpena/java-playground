package org.kkpa.hof;

import java.util.function.UnaryOperator;

public class Utils {

  static UnaryOperator<Integer> adder(int x) {
    return (y) -> y + x;
  }

  static UnaryOperator<Integer> multiplyBy(int x) {
    return (y) -> y * x;
  }

  static UnaryOperator<Integer> addOne() {
    return (x) -> x + 1;
  }
}
