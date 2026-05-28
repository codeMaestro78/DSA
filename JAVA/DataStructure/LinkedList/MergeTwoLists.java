package DataStructure.LinkedList;

public class MergeTwoLists {
    static class Node {
        int val;
        Node next;

        public Node(int key) {
            val = key;
            next = null;
        }
    }

    static Node mergeTwoList(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) {
            tail.next = l1;

        } else if (l2 != null) {
            tail.next = l2;
        }
        return dummy.next;
    }

    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();

    }


    public static void main(String[] args) {
        Node l1 = new Node(1);
        l1.next = new Node(2);
        l1.next.next = new Node(4);

        Node l2 = new Node(1);
        l2.next = new Node(3);
        l2.next.next = new Node(4);

        Node result = mergeTwoList(l1, l2);
        printList(result);


    }
}
