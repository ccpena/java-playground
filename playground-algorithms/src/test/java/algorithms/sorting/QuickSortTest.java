package algorithms.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kkpa.algorithms.sorting.InsertionSort;
import org.kkpa.algorithms.sorting.MergeSort;
import org.kkpa.algorithms.sorting.QuickSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {
  int[] array = null;
  int[] expected = null;

  @BeforeEach
  public void setup() {
    array = new int[]{5, 45, 25, 35, 15, 1, 10};
    expected = new int[]{1, 5, 10, 15, 25, 35, 45};
  }

  @Test
  public void testingQuickSort() {
    System.out.println("Original array: " + Arrays.toString(array));
    QuickSort.sort(array);
    System.out.println("Sorted array: " + Arrays.toString(array));
    assertArrayEquals(expected, array);
  }

  @Test
  public void testingMergeSort() {
    System.out.println("Original array: " + Arrays.toString(array));
    MergeSort.sort(array);
    System.out.println("Sorted array: " + Arrays.toString(array));
    assertArrayEquals(expected, array);
  }

  @Test
  public void testingInsertionSort() {
    System.out.println("Original array: " + Arrays.toString(array));
    InsertionSort.sort(array);
    System.out.println("Sorted array: " + Arrays.toString(array));
    assertArrayEquals(expected, array);
  }

}
