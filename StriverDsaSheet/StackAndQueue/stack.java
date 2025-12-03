package StriverDsaSheet.StackAndQueue;

class MyStack {
    private int[] arr;
    private int top;
    private int capacity;

    public MyStack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int x) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Resizing...");
            resize();
        }
        arr[++top] = x;
    }


    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow! Cannot pop.");
        }
        return arr[top--];
    }


    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty! Cannot peek.");
        }
        return arr[top];
    }


    public boolean isEmpty() {
        return top == -1;
    }


    public int size() {
        return top + 1;
    }

    // Automatically resize array when full
    private void resize() {
        int[] newArr = new int[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        capacity *= 2;
    }
}

class Main {
    public static void main(String[] args) {
        MyStack stack = new MyStack(3);

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        System.out.println("Top Element: " + stack.peek());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Is Empty: " + stack.isEmpty());
        System.out.println("Size: " + stack.size());
    }
}
