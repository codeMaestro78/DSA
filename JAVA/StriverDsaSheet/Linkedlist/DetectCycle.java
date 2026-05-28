//  Detect a cycle .

class Node {
    int val;
    Node next;


    Node (int val) {
        this.val= val;
        this.next = null;
    }
}


public class DetectCycle {
    public static void main(String [] args) {
Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        // Creating cycle
        head.next.next.next.next = head.next;

        boolean ans = hasCycle(head);

        System.out.println(ans);
    }


    public boolean hasCycle(Node head){
        //  edge cases

        if(head == null || head.next === null ) {
            return false;
        }

        Node slow = head;
        NOde fast = head;


        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }
        return false;

    }
}
