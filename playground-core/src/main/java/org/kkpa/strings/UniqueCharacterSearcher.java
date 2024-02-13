package org.kkpa.strings;

public class UniqueCharacterSearcher {

  public static void main(String[] args) {

    String s = "leetcode";
    int result = findUniqueCharacter(s);
    System.out.println("Unique Character is: " + result);
  }

  private static int findUniqueCharacter(String s) {
    int size = s.length();

    for (int i = 0; i < size; i++) {
      int numChart = s.charAt(i);
      if (s.indexOf(numChart) == s.lastIndexOf(numChart)) {
        return i;
      }
    }
    return -1;
  }

}
