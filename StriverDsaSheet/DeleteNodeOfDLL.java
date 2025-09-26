package StriverDsaSheet;

class Node {
    int val;
    Node prev;
    Node next;

    Node(int val, Node prev, Node next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    Node(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

public class DeleteNodeOfDLL {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6, 7, 8, 9};
        Node head = convertArr2DLL(arr);
        System.out.print("Original DLL: ");
        printDLL(head);

        head = deleteNode(head);
        System.out.print("After deleting head: ");
        printDLL(head);
    }

    static Node convertArr2DLL(int[] arr) {
        if (arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node prev = head;

        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            prev.next = temp;
            temp.prev = prev;
            prev = temp;
        }
        return head;
    }

    static Node deleteNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node newHead = head.next;
        newHead.prev = null;
        head.next = null;

        return newHead;
    }

    static void printDLL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" <=> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }
}
