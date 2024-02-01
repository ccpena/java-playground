package algorithms.sorting;

import org.junit.jupiter.api.Test;
import org.kkpa.algorithms.sorting.BubbleSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BubbleSortTest {

  @Test
  public void testEmptyArray() {
    int[] array = {};
    BubbleSort.sort(array);
    assertArrayEquals(array, new int[]{});
  }

  @Test
  public void testSingleElementArray() {
    int[] array = {1};
    BubbleSort.sort(array);
    assertArrayEquals(array, new int[]{1});
  }

  @Test
  public void testSortedArray() {
    int[] array = {1, 2, 3, 4, 5};
    BubbleSort.sort(array);
    assertArrayEquals(array, new int[]{1, 2, 3, 4, 5});
  }

  @Test
  public void testUnsortedArray() {
    int[] array = {2, 3, 5, 4, 1};
    BubbleSort.sort(array);
    assertArrayEquals(array, new int[]{1, 2, 3, 4, 5});
  }


  @Test
  public void testNegativeNumbers() {
    int[] array = {-5, -2, -1, -4, -3};
    BubbleSort.sort(array);
    assertArrayEquals(array, new int[]{-5, -4, -3, -2, -1});
  }

  @Test
  public void testDuplicates() {
    int[] array = {4, 2, 2, 3, 1};
    BubbleSort.sort(array);
    assertArrayEquals(array, new int[]{1, 2, 2, 3, 4});
  }

  // Add more test cases for different scenarios and edge cases

  // Replace BubbleSort.sort with your actual sorting algorithm method
}

