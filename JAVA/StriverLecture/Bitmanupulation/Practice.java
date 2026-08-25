package StriverLecture.Bitmanupulation;

public class Practice {
    public static void main(String[] args) {
        int dec = 42;
        int bin = 0b1011001;
        int hex = 0x2F;

        System.out.println(dec + " " + bin + " " + hex);

        int pos = 6;
        int neg = -6;

        System.out.println("\n+6 in Binary: " + String.format("%32s", Integer.toBinaryString(pos)).replace(' ', '0'));
        System.out.println("-6 in Binary: " + Integer.toBinaryString(neg));

        int max = Integer.MAX_VALUE;
        System.out.println("\nInteger MAX: " + max);
        System.out.println("Integer MAX + 1 (Overflow): " + (max + 1));

        int a = 12;
        int b = 10;

        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(a ^ b);
        System.out.println(~a);

        // shift operations
        int x = 5;
        System.out.println("5 << 2" + " " + (x << 2));
        System.out.println("5 >> 2" + " " + (x >> 2));

        int negA = -16;
        System.out.println("-16 >> 2" + (negA >> 2));
        System.out.println("-16 >>> 2" + (negA >>> 2));

    }

}
