package algorithms.search;

import org.junit.jupiter.api.Test;
import org.kkpa.algorithms.search.RecursiveBinarySearch;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

  @Test
  public void testBinarySearchEmptyNotFound() {
    int[] testArray = {};
    int target = 3;
    assertEquals(-1, RecursiveBinarySearch.search(testArray, target));
  }

  @Test
  public void testBinarySearchSingleFound() {
    int[] testArray = {3};
    int target = 3;
    assertEquals(0, RecursiveBinarySearch.search(testArray, target));
  }

  @Test
  public void testBinarySearchCustomCase() {
    // Generate a sorted list of n integers from 0 to n
    int n = 100; // Change the value of n as needed
    int[] sortedArray = new int[n];
    for (int i = 0; i < n; i++) {
      sortedArray[i] = i;
    }

    // Choose a random target m where m <= n
    int m = ThreadLocalRandom.current().nextInt(0, n + 1);

    // Test the binary search with the custom case
    int expectedIndex = Arrays.binarySearch(sortedArray, m);
    int actualIndex = RecursiveBinarySearch.search(sortedArray, m);

    // Assert that the expected and actual indices are the same
    assertEquals(expectedIndex, actualIndex);
  }


  @Test
  public void testBinarySearchRecursiveNotFound() {
    int[] testArray = {1, 2, 3, 4, 5};
    int target = 6;
    assertEquals(-1, RecursiveBinarySearch.search(testArray, target));
  }

  @Test
  public void testBinarySearchRecursiveEdgeCase() {
    int[] testArray = {2, 5, 8, 12, 16};
    int target = 8;
    assertEquals(2, RecursiveBinarySearch.search(testArray, target));
  }


}

