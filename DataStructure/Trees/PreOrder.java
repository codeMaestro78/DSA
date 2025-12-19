package DataStructure.Trees;

import java.util.ArrayList;
//PREORDER :- ROOT -> LEFT-> RIGHT
public class PreOrder {

    static void preOrder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);

        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        ArrayList<Integer> result = new ArrayList<>();
        preOrder(root, result);

        for (int val : result) {
            System.out.println(val);
        }
    }
}
