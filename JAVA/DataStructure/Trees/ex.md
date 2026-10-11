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
