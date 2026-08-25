package StriverLecture.Bitmanupulation;

public class Techniques {
    public static void main(String[] args) {
        System.out.println("Is Bit Set:-  " + isBitSet(5, 2));
        System.out.println("Set A Bit:- " + setBit(5, 1));
        System.out.println("Clear A Bit:- " + clearBit(5, 2));
        System.out.println("Toggle a bit:- " + toggleBit(2, 5));
        System.out.println("Check if power of 2 :- " + isPowerOfTwo(8));
        System.out.println("Count A Bit:- " + countBit(11));
        System.out.println("Get the rightmost Set Bit :- " + rightmostSetBit(12));
        System.out.println("Turn off the rightmost Set Bit :- "+clearrightmostsetbit(12));
    }

    // Check if a bit is Set
    // Example : Is bit 2 set in 5 (0101) ?
    // 1 <<2 :- 0100 (4)
    // 5 & 4 = 0101 & 0100 (non - zero , so YES)

    public static boolean isBitSet(int n, int k) {
        return (n & (1 << k)) != 0;
    }

    // 2 ) Set a bit
    // To set the k-th bit to 1:
    // OR with a mask that has 1 at position at k:
    public static int setBit(int n, int k) {
        return n | (1 << k);
    }

    //  3 ) Clear a Bit
    // To clear(unset ) the kth bit:
    // AND with a mask that has 0 at position k and 1s everywhere else.

    public static int clearBit(int n, int k) {
        return n & ~(1 << k);
    }

    // 4 ) Toggle A Bit
    //  To flip a bit (o to 1 , or 1 to 0):
    // XOR with a mask that has 1 at position k.

    public static int toggleBit(int n, int k) {
        return n ^ (1 << k);
    }


    //  5 ) Check if power of 2
    // A number is a power of 2 if it has exactly one bit set.
    //  power of 2 have only one bit set : 1,10,100,1000 ....
    //  subtracting 1 flips all bits after (and including) the rightmost set bit
    //  AND if these gives 0 only for powers of 2.

    public static boolean isPowerOfTwo(int n ) {
        return n >0  & (n & (n-1)) == 0 ;
    }

    //  6 ) COUNT A BITS
    //  count the no of 1s in the binary representation:

    public static int countBit(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);
            count += 1;
        }
        return count;
    }

    //  7 ) Get the rightmost Set Bit
    //  Extract the rightmost bit thta is set to 1:
    public static int rightmostSetBit(int n) {
        return n & (-n);
    }

    //  8) Turn off the rightmost Set Bit
    public static int clearrightmostsetbit(int n) {
        return n & (n - 1);
    }





}
