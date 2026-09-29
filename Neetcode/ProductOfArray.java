package Neetcode;

import java.util.Arrays;

// Products of Array Except Self
public class ProductOfArray {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 6 };
        System.out.println("Brute force:- " + Arrays.toString(bruteForce(arr)));
        System.out.println(Arrays.toString(optimalSolution(arr)));
    }


    //  brute force , for each i multiply everything else.

    public static int[] bruteForce(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                prod *= nums[j];
            }
            ans[i] = prod;
        }
        return ans;
    }

    // Time complexity = O(n^2)
    // space: O(1) extra.

    // for the optimal solution

    public static int[] optimalSolution(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        //
        for (int i = n - 1, suffix = 1; i >= 0; --i) {
            ans[i] *= suffix;
            suffix *= nums[i];
        }
        return ans;
    }
}
