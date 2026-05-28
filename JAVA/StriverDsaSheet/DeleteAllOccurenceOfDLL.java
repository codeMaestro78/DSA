package StriverDsaSheet;


import DataStructure.ReverseDoublyLinkedList;

public class DeleteAllOccurenceOfDLL {
    public static void main(String[] args) {
        int[] arr = {10, 10, 10, 4, 5, 10, 10, 1, 2, 3, 10};
        Node head = convertArr2DLL(arr);
        printDLL(head);
        head = deleteAllOccurrences(head, 10);
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

    static Node deleteAllOccurrences(Node head, int val) {
        Node temp = head;
        while (temp != null) {
            if (temp.val == val) {
                if (temp == head) {
                    head = temp.next;
                }
                Node nextNode = temp.next;
                Node prevNode = temp.prev;
                if (nextNode != null) nextNode.prev = prevNode;
                if (prevNode != null) prevNode.next = nextNode;
                temp = nextNode;
            } else {
                temp = temp.next;
            }
        }
        return head;
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
