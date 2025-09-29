package DataStructure.Trees;

import java.util.*;

public class BinaryTree {

    public BinaryTree() {

    }

    static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // insert elements
    public void populate(Scanner scanner) {
        System.out.println("Enter the root node:- ");
        int value = scanner.nextInt();
        root = new Node(value);
        populate(scanner, root);
    }

    public void populate(Scanner sc, Node node) {
        System.out.println("Do you want to enter left of " + node.val);
        boolean left = sc.nextBoolean();
        if (left) {
            System.out.println("enter the value of the left of " + node.val);
            int value = sc.nextInt();
            node.left = new Node(value);
            populate(sc, node.left);
        }

        System.out.println("Do you want to enter right of " + node.val);
        boolean right = sc.nextBoolean();
        if (right) {
            System.out.println("enter the value of the right of " + node.val);
            int value = sc.nextInt();
            node.right = new Node(value);
            populate(sc, node.right);
        }
    }

    public void prettyDisplay() {
        prettyDisplay(root, 0);
    }

    public void prettyDisplay(Node node, int level) {
        if (node == null) {
            return;
        }
        prettyDisplay(node.right, level + 1);

        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t\t");
            }
            System.out.println("|--------->" + node.val);

        } else {
            System.out.println(node.val);
        }
        prettyDisplay(node.left, level + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree bt = new BinaryTree();
        bt.populate(sc);
        bt.prettyDisplay();
    }
}
