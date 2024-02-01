package org.kkpa.algorithms.sorting;

public class QuickSort {

  public static void sort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  private static void quickSort(int[] array, int low, int high) {
    if (low >= high) {
      return;
    }
    int pivot = findPivotFromPartition(array, low, high);
    quickSort(array, low, pivot - 1);
    quickSort(array, pivot + 1, high);
  }

  private static int findPivotFromPartition(int[] array, int low, int high) {
    int pivot = array[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      int currentValue = array[j];
      if (currentValue < pivot) {
        i++;
        swap(array, i, j);
      }
    }
    swap(array, i + 1, high);//Put pivot next to sorted numbers
    return i + 1;
  }

  private static void swap(int[] array, int i, int j) {
    if (i == j) return;

    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}
