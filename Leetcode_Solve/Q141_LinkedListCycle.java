package Leetcode_Solve;

// NO 141. Linked List Cycle
public class Q141_LinkedListCycle {
    public static void main(String[] args) {
        int[] arr = {3, 2, 0, -4};
        int pos = 1;  // create cycle at position 1 (0-based index)

        ListNode head = createLinkedListWithCycle(arr, pos);

        boolean result = hasCycle(head);
        System.out.println("Has cycle: " + result);
    }

    // Floyd's Cycle Detection (Tortoise & Hare)
    static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Helper to create a linked list with a cycle at `pos`
    static ListNode createLinkedListWithCycle(int[] arr, int pos) {
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        ListNode cycleNode = null;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
            if (i == pos) {
                cycleNode = current;
            }
        }

        if (pos != -1) {
            current.next = cycleNode;
        }

        return head;
    }
}

// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
