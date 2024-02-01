package org.kkpa.algorithms.sorting;

import java.util.Arrays;

/**
 * Divide the array into two halves, recursively sort each half, and then merge.
 */
public class MergeSort {

  public static void sort(int[] numbers) {
    mergeSort(numbers);
  }

  private static void mergeSort(int[] numbers) {
    if (numbers.length <= 1) {
      return;
    }
    int mid = numbers.length / 2;
    int[] left = Arrays.copyOfRange(numbers, 0, mid);
    int[] right = Arrays.copyOfRange(numbers, mid, numbers.length);
    mergeSort(left);
    mergeSort(right);
    merge(numbers, left, right);
  }

  private static void merge(int[] numbers, int[] left, int[] right) {
    int leftIndex = 0, rightIndex = 0, numberIndex = 0;

    while (isPendingToTraverse(leftIndex, left) && isPendingToTraverse(rightIndex, right)) {
      if (left[leftIndex] < right[rightIndex]) {
        numbers[numberIndex++] = left[leftIndex];
        leftIndex++;
      } else {
        numbers[numberIndex++] = right[rightIndex];
        rightIndex++;
      }
    }
    while (isPendingToTraverse(leftIndex, left)) {
      numbers[numberIndex++] = left[leftIndex];
      leftIndex++;
    }
    while (isPendingToTraverse(rightIndex, right)) { // if left is not ending)
      numbers[numberIndex++] = right[rightIndex];
      rightIndex++;
    }
  }

  private static boolean isPendingToTraverse(int index, int[] numbers) {
    return index < numbers.length;
  }

}
