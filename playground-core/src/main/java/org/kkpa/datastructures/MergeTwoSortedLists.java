package org.kkpa.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeTwoSortedLists {

  public static void main(String[] args) {
    MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();

    List<Integer> inputFirstListNode = Arrays.asList(1, 2, 4);
    List<Integer> inputSecondListNode = Arrays.asList(1, 3, 4);

    ListNode firstListNode = mergeTwoSortedLists.buildListNode(inputFirstListNode);
    ListNode secondListNode = mergeTwoSortedLists.buildListNode(inputSecondListNode);
    ListNode mergedListNode = mergeTwoSortedLists.mergeTwoLists(firstListNode, secondListNode);
    System.out.println(mergedListNode);

    //Empty bOTH
    inputFirstListNode = Collections.emptyList();
    inputSecondListNode = Collections.emptyList();
    mergedListNode = mergeTwoSortedLists.mergeTwoLists(
            mergeTwoSortedLists.buildListNode(inputFirstListNode),
            mergeTwoSortedLists.buildListNode(inputSecondListNode)
    );
    System.out.println(mergedListNode);

    //Empty 1
    inputFirstListNode = Collections.emptyList();
    inputSecondListNode = Arrays.asList(0);
    mergedListNode = mergeTwoSortedLists.mergeTwoLists(
            mergeTwoSortedLists.buildListNode(inputFirstListNode),
            mergeTwoSortedLists.buildListNode(inputSecondListNode)
    );
    System.out.println(mergedListNode);


    inputFirstListNode = Arrays.asList(2);
    inputSecondListNode = Arrays.asList(1);
    mergedListNode = mergeTwoSortedLists.mergeTwoLists(
            mergeTwoSortedLists.buildListNode(inputFirstListNode),
            mergeTwoSortedLists.buildListNode(inputSecondListNode)
    );
    System.out.println(mergedListNode);


    inputFirstListNode = Arrays.asList(5);
    inputSecondListNode = Arrays.asList(1, 2, 4);
    mergedListNode = mergeTwoSortedLists.mergeTwoLists(
            mergeTwoSortedLists.buildListNode(inputFirstListNode),
            mergeTwoSortedLists.buildListNode(inputSecondListNode)
    );
    System.out.println("Expected: 1,2,4,5  => Result: " + mergedListNode);
  }

  public ListNode buildListNode(List<Integer> input) {
    if (input == null || input.size() == 0) {
      return null;
    }
    int size = input.size();
    ListNode head = new ListNode(input.get(0));

    ListNode currentNode = head;
    for (int i = 1; i < size; i++) {
      currentNode.next = new ListNode(input.get(i));
      currentNode = currentNode.next;
    }
    return head;
  }

  /**
   * 1. Create the head node with the first elements of both listNodes.
   * 2. Sort the elements (head and head.next) => currentDepthNode and currentDepthNode.next
   * 3. Traverse the next position for each listNodes
   * 4. Create currentDepthNode with both sorted nodes.
   * 5. Assign currentDepthNode to the last node associated with the previous depth.
   * 6. Repeat Step 3 handling base cases to stopping it.
   *
   * @param inputFirstListNode
   * @param inputSecondListNode
   * @return
   */
  public ListNode mergeTwoLists(ListNode inputFirstListNode, ListNode inputSecondListNode) {
    ListNode head = null, firstList = inputFirstListNode, secondList = inputSecondListNode;
    if (firstList != null) {
      head = new ListNode(firstList.val);
      firstList = firstList.next;
    }
    if (secondList != null) {
      ListNode second = new ListNode(secondList.val);
      if (head == null) {
        head = second;
      } else {
        head.next = second;
      }
      secondList = secondList.next;
    }
    if (head == null) return head;
    head = mergeAndSortNodes(head, head, head.next);
    ListNode currentDepthNode = head.next;

    while (firstList != null || secondList != null) {
      ListNode firstNode = null, secondNode = null;
      if (currentDepthNode == null) {
        break;
      }

      if (firstList != null) {
        firstNode = new ListNode(firstList.val);
        firstList = firstList.next;
      }
      if (secondList != null) {
        secondNode = new ListNode(secondList.val);
        secondList = secondList.next;
      }
      mergeAndSortNodes(head, firstNode, secondNode);
    }

    return head;
  }

  private ListNode mergeAndSortNodes(ListNode headPointerNode, ListNode inputFirstNode, ListNode inputSecondNode) {
    ListNode sortedDepthNode = null, firstNode = null, secondNode = null;
    if (inputFirstNode == null && inputSecondNode == null) return sortedDepthNode;

    if (inputFirstNode != null) firstNode = new ListNode(inputFirstNode.val);
    if (inputSecondNode != null) secondNode = new ListNode(inputSecondNode.val);

    ListNode tmpHead = headPointerNode;
    while (tmpHead.next != null) {
      if (firstNode != null) {
        if (tmpHead.val > firstNode.val) {
          ListNode tmp = tmpHead;
          tmpHead = firstNode;
          tmpHead.next = tmp;
        }
      }
      if (secondNode != null) {
        if (tmpHead.val > secondNode.val) {
          ListNode tmp = tmpHead;
          tmpHead = firstNode;
          tmpHead.next = tmp;
        }
      }
    }
    return sortedDepthNode;
  }

  class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override public String toString() {
      List<String> values = new ArrayList<>();
      ListNode current = this;
      while (current != null) {
        values.add("" + current.val);
        current = current.next;
      }
      String joined = values.stream().reduce((a, b) -> a + "," + b).get();
      return "[" + joined + "]";
    }
  }
}
