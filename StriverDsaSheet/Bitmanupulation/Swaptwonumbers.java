package StriverDsaSheet.Bitmanupulation;

public class Swaptwonumbers {
    public static void main(String[] args) {
        swap(1, 2);
    }

    public static void swap(int a, int b) {

        System.out.println("Before swap: a = " + a + ", b = " + b);

        a = a ^ b;
        b = a ^ b;

        a = a ^ b;

        System.out.println("After swap: a = " + a + ", b = " + b);
    }
}
