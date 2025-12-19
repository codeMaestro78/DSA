package DataStructure.LinkedList;

public class ReverseLinkedList {

    static class Node {
        int val;
        Node next;

        public Node(int key) {
            val = key;
            next = null;
        }
    }

    static Node reverseLinked(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;

            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
