package DataStructure.QueueDS;

public class Queue {
    int[] arr;
    int front;
    int rear;
    int size;
    int capacity;


    public Queue(int cap) {
        arr = new int[cap];
        front = 0;
        rear = 0;
        size = 0;
        this.capacity =cap;

    }

    public void enqueue(int x) {
        if (size == capacity) {
            throw new IllegalStateException("queue full");
        }
        arr[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }

    public int enqueue() {

        if (isEmpty()) {
            throw new IllegalStateException("dequeue from empty queue");
        }
        int val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("peek from empty queue");
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}


// 1.

// Sliding Window
// Cheatsheet Template-https:// lnkd.in/dv2-NP5b
// 2.
// Binary Search Template-https:// lnkd.in/deDpHndC
// 3. Two-
// pointers problems-https:// lnkd.in/dGRdAMg9
// 4.
// Dynamic Programming Patterns-https:// lnkd.in/dXg5PJ_9
// 5. 10-line Substring Template-https:// lnkd.in/dHsB8yRP
// 6.
// String Questions Pattern-https:// lnkd.in/d_mazjKr
// 7.
// Graph For Beginners-https:// lnkd.in/dcQxHDwX
// 8. DFS&
// BFS Tree Traversal-https:// lnkd.in/dh-i2vnY
// 9.
// Backtracking Pattern-https:// lnkd.in/dzGKrpju
// 10.
// Backtracking questions
// in Java-https:// lnkd.in/dp9T65VN
// 11.
// Monotonic Stack Problems-https:// lnkd.in/dQyT-QXs
// 12. Patterns for
// Bit Manipulation-https:// lnkd.in/dtGJkEin
// 13. BFS+
// DFS Problems–Part 1-https:// lnkd.in/du95kqCw
// 14. BFS+
// DFS Problems–Part 2-https:// lnkd.in/ddyeSDRb
// 15.
// More DP Patterns-https:
// // lnkd.in/dH-za55c
