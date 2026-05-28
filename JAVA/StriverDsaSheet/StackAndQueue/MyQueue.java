package StriverDsaSheet.StackAndQueue;

//✔ enqueue()
//✔ dequeue()
//✔ peek()
//✔ isEmpty()
//✔ isFull()
public class MyQueue {
    private final int[] arr;
    private int front;
    private int rear;
    private int size;
    private final int capacity;


    public MyQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue  overflow");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is underflow");
        }
        int removed = arr[front];
        front = (front + 1) % capacity;
        size--;
        return removed;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Queue is underflow");
        }
        return arr[front];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean isFull(){
        return size==capacity;
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Front Element: " + q.peek());
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Is Empty? " + q.isEmpty());
        System.out.println("Size: " + q.size());

        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60); // works because circular queue
        System.out.println("Front Element: " + q.peek());
    }
}
