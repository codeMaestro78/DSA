package DataStructure.StackDS;

public class Stack {
    int[] arr;
    int top;

    public Stack(int capacity) {
        arr = new int[capacity];
        top = -1;
    }

    public void push(int x) {
        if (top + 1 >= arr.length) {
            throw new IllegalStateException("Overflow");
        }
        top = top + 1;
        arr[top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        int val = arr[top];
        top = top - 1;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }


    public static void main(String[] args) {
        Stack s = new Stack(5);
        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println(s.peek());
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.size());
    }
}
