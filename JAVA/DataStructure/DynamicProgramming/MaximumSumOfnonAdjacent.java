package DataStructure.DynamicProgramming;

//eg :- [2,1,4,6] -> maximum sum is 8  ----> (2) we can not pick 2 and 1 or 1 and 4 because they are adjacent to each other.

import java.util.Arrays;

public class MaximumSumOfnonAdjacent {
    // TC: O(2^n), SC: O(n) recursion stack
    static int fmax(int ind, int[] nums) {
        if (ind == 0) return nums[ind];
        if (ind < 0) return 0;

        int pick = nums[ind] + fmax(ind - 2, nums);
        int notPick = fmax(ind - 1, nums);

        return Math.max(pick, notPick);
    }

    static int maximumSumOfNonAdjacent(int[] nums) {
        int n = nums.length;
        return fmax(n - 1, nums);
    }

//    Complexity
//    Time: O(n)
//    Space: O(n) (dp + recursion stack)

    static int optimizeDPMaxi(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return fOptimize(n - 1, nums, dp);
    }

    private static int fOptimize(int i, int[] nums, int[] dp) {

        if (i == 0) return nums[i];
        if (i < 0) return 0;
        if (dp[i] != -1) return dp[i];

        int pick = nums[i] + fOptimize(i - 2, nums, dp);
        int notPick = fOptimize(i - 1, nums, dp);

        return dp[i] = Math.max(pick, notPick);
    }
//    tabulation bottom up
// TC : O(n) SC: O(n)

    static int fTabulation(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int pick = nums[i];
            if (i > 1) pick += dp[i - 2];
            int notPick = nums[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }

    //    as we have the space com is O(n) we can further optimize the space comp O(1) by using variables
//     Complexity
//    Time: O(n)
//    Space: O(1)
    static int fSpaceOptimized(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;

        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            int pick = nums[i];
            if (i > 1) pick += prev2;
            int notPick = prev;

            int curr = Math.max(pick, notPick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,4,6};
        System.out.println(maximumSumOfNonAdjacent(nums));
        System.out.println(fSpaceOptimized(nums));
    }
}


