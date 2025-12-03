package StriverDsaSheet.Linkedlist;

class Solution_01 {
    public boolean isValue(Node head, int target) {
        boolean isPresent = false;
        Node temp = head;

        while (temp != null) {
            if (temp.data == target) {
                return true;
            }
            temp = temp.next;
        }
        return isPresent;
    }
}
class Main1{
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        Solution_01 obj = new Solution_01();

        // Search for value
        if (obj.isValue(head, 20))
            System.out.println("Found");
        else
            System.out.println("Not Found");
    }
}
