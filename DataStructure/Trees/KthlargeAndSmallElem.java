package DataStructure.Trees;

import java.util.*;

public class KthlargeAndSmallElem {
    //    Brute force
    //Time Complexity: O(N), where N is the number of nodes in the BST. Because the code performs an in-order traversal of the BST,
// which requires O(N) time.
//Space Complexity: O(N), since the code stores all the node values in a list.

    static void  inOrder(TreeNode root, List<Integer> values) {
        if (root != null) {
            inOrder(root.left, values);
            System.out.println(root.val);
            inOrder(root.right, values);
        }

    }

    static List<Integer> kthSmallerstANdLargest(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);

        int kth_smallest = values.get(k - 1);
        int kth_largest = values.get(values.size() - k);

        List<Integer> result = new ArrayList<>();
        result.add(kth_smallest);
        result.add(kth_largest);
        return result;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);

        int k = 1;
        List<Integer> result = kthSmallerstANdLargest(root, k);
        System.out.println(result);
    }

//    below is the optimal approach


}

