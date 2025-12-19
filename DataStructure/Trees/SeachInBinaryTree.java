package DataStructure.Trees;

public class SeachInBinaryTree {
    TreeNode root;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int item) {
            val = item;
            left = null;
            right = null;
        }
    }

    static TreeNode searchInBT(TreeNode root, int target) {
        while (root != null && root.val != target) {
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;

    }

    public static void main(String[] args) {
        TreeNode tn = new TreeNode(4);
        tn.left = new TreeNode(2);
        tn.right = new TreeNode(7);
        tn.left.left = new TreeNode(1);
        tn.left.right = new TreeNode(3);

        TreeNode result = searchInBT(tn, 2);
        if (result != null) {
            System.out.println(result.val + " found");
        } else {
            System.out.println("Not found");
        }
    }

}
