package org.kkpa.algorithms.sorting;

public class BubbleSort {


  public static void sort(int[] array) {
    int size = array.length;

    for (int i = 0; i < size; i++) {
      int max = size - 1 - i;
      for (int j = 0; j < max; j++) {
        if (array[j] > array[j + 1]) {
          int temp = array[j + 1];
          array[j + 1] = array[j];
          array[j] = temp;
        }
      }
    }
  }
}
