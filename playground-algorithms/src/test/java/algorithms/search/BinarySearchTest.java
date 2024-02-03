package algorithms.search;

import org.junit.jupiter.api.Test;
import org.kkpa.algorithms.search.RecursiveBinarySearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {


  @Test
  public void testBinarySearchRecursiveFound() {
    int[] testArray = {1, 2, 3, 4, 5};
    int target = 3;
    assertEquals(2, RecursiveBinarySearch.search(testArray, target));
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

  @Test
  public void testBinarySearchRecursiveNotFoundEdgeCase() {
    int[] testArray = {2, 5, 8, 12, 16};
    int target = 9;
    assertEquals(-1, RecursiveBinarySearch.search(testArray, target));
  }

}

