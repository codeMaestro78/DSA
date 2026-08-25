package StriverLecture.Bitmanupulation;

public class ReverseBits {
    public static void main(String[] args) {
        int n = 43261596;
        System.out.println(reverseBits(n));
    }

    public static int reverseBits(int n) {
        int ans = 0;
        for (int i = 1; i <= 32; i++) {
            ans = (ans << 1) | (n & 1);
            n >>= 1;
        }
        return ans;

    }
}
