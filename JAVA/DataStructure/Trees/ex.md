# PreOrder Traversal of Binary Tree

## -> Root -> Left -> Right

```
void preOrder(TreeNode n ){
    if (n== null) return;
    System.out.println(n.data);
    preOrder(n.left);
    preOrder(n.right);
}
```

# InOrder Traversal of Binary Tree

## -> Left -> Root -> Right

```
void inOrder(TreeNode n){
    if(n==null) return;
    inOrder(n.left);
    System.out.println(n.data);
    inOrder(n.right);
}
```

# PostOrder Traversal of Binary Tree

## -> Left -> Right -> Root

```
void postOrder(TreeNode n){
    if (n==null) return;
    postOrder(n.left);
    postOrder(n.right);
    System.out.println(n.data);
}
```

# Level Order Traversal of Binary Tree
## 1,2,3,4,5 level by level with Queue:

```
void levelOrder(TreeNode root){
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while(!q.isEmpty()){
        TreeNode curr = q.poll();
        System.out.println(curr.data);
        if(curr.left!=null) q.add(curr.left);
        if(curr.right!=null) q.add(curr.right);
    }
}
```

# BST Search logic
## Left smaller , Right bigger


```
TreeNode insert(TreeNode root , int val){
    if(root == null ) return new TreeNode(val);
    if(val < root.data) roo.left =insert(root.left, val);
    else root.right  = insert(root.right,val);
    return root;
}
```

```
boolean search(TreeNode root , int val){
    if(root == null) return false;
    if(root.data == val) return true;
    if(val < root.data) return search(root.left, val);
    else return search(root.right, val);
}
```
