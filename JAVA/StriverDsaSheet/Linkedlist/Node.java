package StriverDsaSheet.Linkedlist;


public class Node {
    public int data;
    public int[] arr;
    public Node left;
    public Node right;
    public Node next;
    public Node random;


    public Node(int data) {
        this.data = data;
    }

    public Node(int data, int[] arr) {
        this.data = data;
        this.arr = arr;
    }
}
