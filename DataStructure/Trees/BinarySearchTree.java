package DataStructure.Trees;

public class BinarySearchTree {

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    static class Node {
        private int val;
        private Node left;
        private Node right;

        Node(int val) {
            this.val = val;
            this.left = null;

            this.right = null;
        }
    }

    Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    Node search(Node root, int key) {
        if (root == null || root.val == key) {
            return root;
        }
        if (key < root.val) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.val + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Node root = null;

        int[] values = { 50, 30, 70, 20, 40, 60, 80 };
        for (int val : values) {
            root = bst.insert(root, val);
        }
        System.out.println("Inorder traversal (sorted):");
        bst.inorder(root);

        int key = 40;
        if (bst.search(root, key) != null) {
            System.out.println("\n" + key + " found in BST");
        } else {
            System.out.println("\n" + key + " not found in BST");
        }
    }

}
