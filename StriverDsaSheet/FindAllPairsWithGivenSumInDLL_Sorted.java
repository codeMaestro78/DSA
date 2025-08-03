package StriverDsaSheet;

import java.util.ArrayList;
import java.util.List;


public class FindAllPairsWithGivenSumInDLL_Sorted {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = convertArr2DLL(arr);

        System.out.print("DLL: ");
        printDLL(head);
        List<List<Integer>> pairs = findAllPairsWithGivenSumInDLL(head, 5);

        for (List<Integer> pair : pairs) {
            System.out.println(pair.toString());
        }
    }

    static Node convertArr2DLL(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node prev = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            prev.next = temp;
            temp.prev = prev;
            prev = temp;
        }
        return head;
    }

    static List<List<Integer>> findAllPairsWithGivenSumInDLL(Node head, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (head == null) return result;

        Node left = head;
        Node right = head;

        // Move right to the end
        while (right.next != null) {
            right = right.next;
        }

        // Two pointer approach
        while (left != null && right != null && left != right && right.next != left) {
            int currentSum = left.val + right.val;

            if (currentSum == sum) {
                List<Integer> pair = new ArrayList<>();
                pair.add(left.val);
                pair.add(right.val);
                result.add(pair);
                left = left.next;
                right = right.prev;
            } else if (currentSum < sum) {
                left = left.next;
            } else {
                right = right.prev;
            }
        }
        return result;
    }

    static void printDLL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" <=> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }
}
