package org.kkpa.algorithms.search;

public class RecursiveBinarySearch {


  public static int search(int[] numbers, int value) {
    if (numbers == null || numbers.length == 0) {
      return -1;
    }

    int middle = numbers.length / 2;
    if (numbers[middle] == value) {
      return middle;
    }
    if (numbers[middle] > value) {
      return search(numbers, value, 0, middle - 1);
    } else {
      return search(numbers, value, middle + 1, numbers.length);
    }
  }

  private static int search(int[] numbers, int value, int start, int end) {
    if (start >= end) {
      return -1;
    }
    int middleIdx = start + (end - start) / 2;
    if (numbers[middleIdx] == value) {
      return middleIdx;
    }
    if (numbers[middleIdx] > value) {
      return search(numbers, value, start, middleIdx - 1);
    } else {
      return search(numbers, value, middleIdx + 1, end);
    }
  }

  public static Integer search(Integer[] integers, Integer target) {
    return search(integers, target);
  }
}
