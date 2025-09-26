package DataStructure;

class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public Node(int data) {
        this(data, null, null);
    }
}

public class DoublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node head = convertArr2DLL(arr);
        System.out.print("Original DLL: ");
        printDLL(head);
        // head = deleteHead(head);
        // System.out.print("After deleting head: ");
        // printDLL(head);
//        head = deleteTail(head);
//        System.out.print("After deleting tail: ");
//        printDLL(head);
//        head = deleteKthElement(head, 1);
//        printDLL(head);
//        deleteNode(head.next.next);
//        printDLL(head);
//        head = insertBeforeHead(head, 10);
//        printDLL(head);
//        head = insertBeforeTail(head, 10);
//        printDLL(head);
        head = insertBeforeKthNode(head, 3, 10);
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

    //        Deleting the head
    static Node deleteHead(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node temp = head;
        head = head.next;
        head.prev = null;
        temp.next = null;
        return head;
    }

    //          Deleting the tail
    static Node deleteTail(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        Node newTail = temp.prev;
        if (newTail != null) {
            newTail.next = null;
        }
        temp.prev = null;
        return head;
    }

    static Node deleteKthElement(Node head, int k) {
        if (head == null || k <= 0) return head;
        Node knode = head;
        int cnt = 1;
        while (knode != null && cnt < k) {
            knode = knode.next;
            cnt++;
        }
        if (knode == null) return head;
        if (knode.prev == null) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (knode.next == null) {
            knode.prev.next = null;
        } else {
            knode.prev.next = knode.next;
            knode.next.prev = knode.prev;
        }
        knode.next = null;
        knode.prev = null;
        return head;
    }

    //    Delete node
    static void deleteNode(Node temp) {
        Node back = temp.prev;
        Node front = temp.next;
        if (front == null) {
            back.next = null;
            temp.prev = null;
            return;
        }
        back.next = front;
        front.prev = back;
        temp.next = temp.prev = null;
    }

    //    insertion at before the node
    static Node insertBeforeHead(Node head, int val) {
        Node newNode = new Node(val, head, null);
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        return newNode;
    }

    //    Insertion before the tail
    static Node insertBeforeTail(Node head, int val) {
        if (head.next == null) return insertBeforeHead(head, val);
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        Node back = tail.prev;
        Node newNode = new Node(val, tail, back);
        back.next = newNode;
        tail.prev = newNode;
        return head;
    }

    //    Inserting before the kth element
    static Node insertBeforeKthNode(Node head, int k, int val) {
        if (head == null) {
            return insertBeforeHead(head, val);
        }
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if (count == k) {
                break;
            }
            temp = temp.next;
        }
        Node back = temp.prev;
        Node newNode = new Node(val, temp, back);
        back.next = newNode;
        temp.prev = newNode;
        return head;
    }

    //    Insert before given node
    static void insertBeforeGivenNode(Node node, int val) {
        Node back = node.prev;
        Node newNode = new Node(val, node, back);
        back.next = newNode;
        node.prev = newNode;
    }

    //   Printing doubly linked list
    static void printDLL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" <=> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }
}
