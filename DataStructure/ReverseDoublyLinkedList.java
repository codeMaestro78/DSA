package DataStructure;

public class ReverseDoublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node head = DoublyLinkedList.convertArr2DLL(arr);
        head = reverseDLL(head);
        DoublyLinkedList.printDLL(head);

    }

    static Node reverseDLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node back = null;
        Node temp = head;

//        Swapping
        while (temp != null) {
            back = temp.prev;
            temp.prev = temp.next;
            temp.next = back;
            temp = temp.prev;
        }
        return back.prev;
    }
}
