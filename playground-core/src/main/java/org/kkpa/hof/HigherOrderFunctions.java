package org.kkpa.hof;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

import static org.kkpa.hof.Utils.adder;
import static org.kkpa.hof.Utils.multiplyBy;

public class HigherOrderFunctions {


  public static void main(String[] args) {
    Function<Integer, Function<Integer, Integer>> makeAdder = Utils::adder;
    Function<Integer, Integer> addOne = makeAdder.apply(1);
    System.out.println("Add One to 5: " + addOne.apply(5));

    // Add 3 and multiply by 3
    int input = 10;
    int pivot = 3;
    int result = multiplyBy(pivot).apply(adder(pivot).apply(input));
    System.out.printf("Adding 3 and Multiplying result by 3  Input:%s - Yields:%s", input, result);

    // Function Composition
    //But what if we created a function that did both things instead?
    // (f,g) -> x -> g( f(x) )
    BinaryOperator<UnaryOperator<Integer>> functionCompositions =
            (multiplier, adder) -> x -> multiplier.apply(adder.apply(x));

    result = functionCompositions.apply(multiplyBy(pivot), adder(pivot)).apply(input);
    System.out.printf("\nFunction Composition  Input:%s - Yields:%s", input, result);

    // Function Composition with compose
    Function<Integer, Integer> composed = multiplyBy(pivot).compose(adder(pivot));
    result = composed.apply(input);
    System.out.printf("\nFunction Composed  Input:%s - Yields:%s", input, result);


    Function<Integer, Function<Integer, Integer>> sumFunction = x -> y -> x + y;
    Function<Integer, IntUnaryOperator> sumIntFunction = x -> y -> x + y;
    IntFunction add = n -> n + 1;
    IntFunction<IntFunction> sum = x -> y -> x + y;
    System.out.println("Testing IntFunction" + sum.apply(10).apply(4));

  }

}
