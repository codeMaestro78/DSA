package DataStructure.LinkedList.DoublyLinkedList;


class Node {
    int data;
    Node next;
    Node prev;


    Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    Node(int data) {
        this.data = data;
        prev = null;
        next = null;
    }
}

public class ConvertArr2DLL {
    public static void main(String[] args) {
        int[] arr = { 12, 5, 8, 7 };
        Node head = convertArr2DLL(arr);
        print(head);
        // head = deletionHead(head);
        // print(head);
        head = taildeletion(head);
        print(head);

    }

    public static Node convertArr2DLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node prev = head;

        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            prev.next = temp;
            prev = temp;
        }
        return head;
    }

    // deletion of head

    public static Node deletionHead(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node temp = head;
        head = head.next;

        head.prev = null;
        temp.next = null;

        return head;
    }

    // deletion of tail

    public static Node taildeletion(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }



        return head;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + "<=>");
            head = head.next;
        }
        System.out.println("NULL");
    }



}
