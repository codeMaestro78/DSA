package DataStructure.Trees;


public class CountGoodNodesInBT {
    public static void main(String[] args) {
        // Test case: root = [2,1,1,3,null,1,5], Expected Output: 3
        //         2
        //        / \
        //       1   1
        //      /   / \
        //     3   1   5
        TreeNode root = new TreeNode(2,new TreeNode(1, new TreeNode(3), null),new TreeNode(1, new TreeNode(1), new TreeNode(5))
        );

        CountGoodNodesInBT solution = new CountGoodNodesInBT();
        int result = solution.goodNode(root);
        System.out.println("Good Nodes Count: " + result); // Expected: 3
    }

    public int goodNode(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }


    private int dfs(TreeNode node, int maxval) {

        if (node == null) {
            return 0;
        }

        int count = 0;

        if (node.val >= maxval) {
            count = 1;
        }

        maxval = Math.max(maxval, node.val);

        count += dfs(node.left, maxval);
        count += dfs(node.right, maxval);


        return count;


    }
}
