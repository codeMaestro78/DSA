package DataStructure.Trees;
// there is three cases for deleting the node:
//********* 1 ) The target value with Zero children:*********
//Node has No Children (Leaf Node)

//if the target node is the leaf node it can directly remove from the tree since it has no child to maintain./// ///////

//case 2 : The Node has one child (either left or right)
//if the target node has only one child, we remove the node and connect its parent directly to its only child.
// This way, the tree remains valid after deletion of target node.

//case 3 : The Node has two children (left and right)
// this case is slightly more complex. because after deleting the target node we have to maintain BST property.
//To maintain bst property we have to find a replacement node for a TARGET .
// The replacement can be either
// 1 ) Inorder successor :- The value of the inorder successor will be larger than the anything from the left subtree. BUt
// smaller than everything else in the right subtree.

//the smallest value in the right subtree, which is the next greater value than the target node.

// 2)  Inorder Predecessor :-The value of the inorder predecessor will be smaller than the anything else in the from right subtree.But
//larget than everything else in the left subtree.

// the largest value in the left subtree, which is the next smaller value then the target node.
public class DeleteNodeInBST {

//        Find the Inorder Successor (go right once, then all the way left).

    static TreeNode inorderSuccessor(TreeNode root) {

        root = root.right;
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    static TreeNode delNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key) {
            root.left = delNode(root.left, key);
        } else if (root.val < key) {
            root.right = delNode(root.right, key);
        } else {
//            this is for the node with 0 or 1 child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

//            this is for node with 2 child
            TreeNode succ = inorderSuccessor(root);
            root.val = succ.val;
//            delete the inorder successor from the right subtree
//            we are deleting the successor old position only.
            root.right = delNode(root.right, succ.val);
        }
        return root;
    }
}



