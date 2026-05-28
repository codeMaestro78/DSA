package DataStructure.Trees;

public class InsertInBST {

    static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode curr = root;
        while (true) {
            if (val < curr.val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;

                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                }
                curr = curr.right;
            }
        }
        return root;
    }

    static void printBT(TreeNode root) {
        if (root != null) {
            printBT(root.left);
            System.out.println(root.val + " ");
            printBT(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        root.left.right = new TreeNode(8);
        root.left.left = new TreeNode(6);

        root.right = new TreeNode(11);
        root.right.right = new TreeNode(13);
        root.right.left = new TreeNode(12);

        TreeNode result = insertIntoBST(root, 20);
        printBT(result);

    }
}
