package StriverDsaSheet.Linkedlist;

//The simple idea to solve this problem is to traverse the linked list and count the number of nodes using a counter.
//Initialize a temporary pointer to the head of the list. The temporary pointer will be used to traverse the list.
//Traverse the linked list until the the current node is not null.
//At every node, increment the counter to count number of nodes.
//After reaching the end of the linked list, return the count. This will be your total number of nodes which is the length of the linked list.

class Solution {
    public int lengthOfLL(Node head) {
        int count = 0;
        Node temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class Main_LL{
        public static void main(String[] args) {
            Node head =new Node(10);
            head.next=new Node(20);
            head.next.next=new Node (30);

            Solution obj = new Solution();
            System.out.println(obj.lengthOfLL(head));
        }
    }

