package DataStructure.Trees;

public class CeilBinarySearchTree {

    static int ceilBinaryTree(TreeNode root, int key) {
        int ceil = -1;

        while (root != null) {
            if (root.val == key) {
                return root.val;

            }
            if (root.val < key) {
                root = root.right;
            } else {
                ceil = root.val;
                root = root.left;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left=new TreeNode(5);
        root.left.left= new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(15);


        System.out.println(ceilBinaryTree(root,7));
    }
}

