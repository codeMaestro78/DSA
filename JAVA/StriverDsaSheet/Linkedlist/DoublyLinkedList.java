package StriverDsaSheet.Linkedlist;

public class DoublyLinkedList {
    private DoublyNode head;
    private DoublyNode tail;
    private int size;

    DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertAtHead(int val) {
        DoublyNode newNode = new DoublyNode(val);
        if (head == null) {
            head = newNode;
            tail = newNode;

        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertAtTail(int val) {
        DoublyNode newNode = new DoublyNode(val);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void insertAtPosition(int pos, int val) {
        if (pos < 1 || pos > size + 1) {
            System.out.println("Invalid position");
        }
        if (pos == 1) {
            insertAtHead(val);
        }
        if (pos == (size + 1)) {
            insertAtTail(val);
        }
        DoublyNode newNode = new DoublyNode(val);
        DoublyNode temp = head;

        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;
        size++;
    }

    public void deleteHead() {
        if (head == null) {
            System.out.println("List is empty");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void deleteTail() {
        if (tail == null) {
            System.out.println("List is empty");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void deleteAtPosition(int pos) {
        if (pos < 1 || pos > size) {
            System.out.println("Invalid position");
        }
        if (pos == 1) {
            deleteHead();
            return;
        }
        if (pos == (size)) {
            deleteTail();
            return;
        }
        DoublyNode temp = head;

        for (int i = 1; i < pos; i++) {
            temp = temp.next;
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        size--;

    }

    public void printForward() {
        DoublyNode temp = head;
        System.out.println("Forward ");
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();

    }

    public void printBackward() {
        DoublyNode temp = tail;
        System.out.print("Backward: ");
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.prev;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }
}

class Main {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtTail(30);
        list.insertAtPosition(2, 25); // insert at pos 2

        list.printForward();
        list.printBackward();

        list.deleteHead();
        list.deleteTail();
        list.deleteAtPosition(1);

        list.printForward();
        System.out.println("Size: " + list.getSize());
    }
}
