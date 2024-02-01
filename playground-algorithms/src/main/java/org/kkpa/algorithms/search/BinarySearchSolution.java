package org.kkpa.algorithms.search;

public class BinarySearchSolution {


  public static void main(String[] args) {
    BinarySearchSolution bs = new BinarySearchSolution();
    int found = bs.search(new int[]{0, 100, 200, 300, 400, 500, 600, 700, 800, 900}, 100);
    System.out.println("Found: " + found);
  }

  private int search(int[] numbers, int value) {
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

  private int search(int[] numbers, int value, int start, int end) {
    if (start > end) {
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

}
