//package DataStructure.Trees;
//
//
//class BinaryTree {
//    class TreeNode {
//        int key;
//        DataStructure.Trees.TreeNode left;
//        DataStructure.Trees.TreeNode right;
//
//        public TreeNode(int n) {
//            key = n;
//            left = null;
//            right = null;
//        }
//    TreeNode root;
//
//    public void BinaryTree() {
//        root = null;
//
//    }
//
//    public void insert(int key) {
//        root = insertRec(root, key);
//    }
//
//    public TreeNode insertRec(TreeNode root, int key) {
//        if (root == null) {
//            root = new TreeNode(key);
//            return root;
//        }
//
//        if (key < root.key) {
//            root.left = insertRec(root.left, key);
//        } else if (key > root.key) {
//            root.right = insertRec(root.right, key);
//        }
//
//        return root;
//    }
//
//    public void inorder() {
//        inorderRec(root);
//    }
//
//    private void inorderRec(TreeNode root) {
//        if (root != null) {
//            inorderRec(root.left);
//            System.out.println(root.key + " ");
//            inorderRec(root.right);
//        }
//    }
//
//    public boolean search(int key) {
//        return searchRec(root, key);
//    }
//
//    private boolean searchRec(TreeNode root, int key) {
//        if (root == null) {
//            return false;
//        }
//        if (root.key == key) {
//            return true;
//        }
//        if (key < root.key) {
//            return searchRec(root.left, key);
//        } else {
//            return searchRec(root.right, key);
//        }
//    }
//
//    public int findMin() {
//        return findMinRec(root);
//    }
//
//    private int findMinRec(TreeNode root) {
//        if (root == null)
//            throw new IllegalStateException("Tree is empty");
//        if (root.left == null)
//            return root.key;
//        return findMinRec(root.left);
//    }
//
//    public int findMax() {
//        return findMaxRec(root);
//    }
//
//    public int findMaxRec(TreeNode root) {
//        if (root == null)
//            throw new IllegalStateException("Tree is empty");
//        if (root.right == null) {
//            return root.key;
//        }
//
//        return findMaxRec(root.right);
//    }
//
//    public static void main(String[] args) {
//        BinaryTree bt = new BinaryTree();
//        bt.insert(50);
//        bt.insert(30);
//        bt.insert(20);
//        bt.insert(40);
//        bt.insert(60);
//        bt.insert(80);
//        bt.insert(70);
//
//        System.out.println("inorder traversal: ");
//        bt.inorder();
//
//        int searchKey= 40;
//        if(bt.search(searchKey)){
//            System.out.println(searchKey+"found");
//        }
//        else{
//            System.out.println("not found");
//        }
//
//        System.out.println("min value: "+bt.findMin());
//        System.out.println("max value: "+bt.findMax());
//    }
//}
//}
//
