package Leetcode_Solve;

// 2221. Find Triangular Sum of an Array
// daily problem
public class Q2221_FindTriangularSumofanArray {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        System.out.println(triangularSum(nums));

    }

    static int triangularSum(int[] num) {
        int n = num.length;
        int[] binomial = new int[n];
        binomial[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                binomial[j] = (binomial[j] + binomial[j - 1]) % 10;
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = (result + num[i] * binomial[i]) % 10;
        }
        return result;

    }
}
