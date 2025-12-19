package DataStructure.Trees;

public class MInMaxInBT {
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

    static int maxInBT(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        int leftMax = maxInBT(root.left);
        int rightMax = maxInBT(root.right);

        return Math.max(root.val, Math.max(leftMax, rightMax));
    }

    static int minInBT(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;

        int leftMin = minInBT(root.left);
        int rightMin = minInBT(root.right);

        return Math.min(root.val, Math.min(leftMin, rightMin));
    }

    public static void main(String []args){

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(7);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode (9);
        root.right.left = new TreeNode (4);
        root.right.left.right = new TreeNode(21);

        System.out.println(maxInBT(root));
        System.out.println(minInBT(root));
    }

}
