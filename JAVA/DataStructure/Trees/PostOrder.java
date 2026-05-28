package DataStructure.Trees;

import java.util.ArrayList;

public class PostOrder {
    static void postOrder(TreeNode root, ArrayList<Integer> res) {
        if (root == null) {
            return;
        }

        postOrder(root.left, res);
        postOrder(root.right, res);

        res.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> res = new ArrayList<>();
        postOrder(root,res);
        for(int val:res){
            System.out.println(val);
        }
    }
}
