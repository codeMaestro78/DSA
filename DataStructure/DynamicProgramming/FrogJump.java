package DataStructure.DynamicProgramming;

import java.util.Arrays;

public class FrogJump {

    static int recursiveFrogJump(int n, int[] heights) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return recursiveF(n-1, heights, dp);

    }

    static int recursiveF(int ind, int[] heights, int[] dp) {
        if (ind == 0) return 0;
        if (dp[ind] != -1) return dp[ind];

        int fs = recursiveF(ind - 1, heights, dp) + Math.abs(heights[ind] - heights[ind - 1]);

        int ss = Integer.MAX_VALUE;
        if (ind > 1) {
            ss = recursiveF(ind - 2, heights, dp) + Math.abs(heights[ind] - heights[ind - 2]);
        }
        return dp[ind] = Math.min(fs, ss);

    }

    static int tabulationFrogJump(int n, int[] heights) {
        int[] dp = new int[n];
        Arrays.fill(dp, 0);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int fs = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int ss = Integer.MAX_VALUE;
            if (i > 1) ss = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);

            dp[i] = Math.min(fs, ss);
        }
        return dp[n - 1];
    }

    //    we  can further optimize the tabulation function by using two variables.
// from O(n) --> O(1)
    static int optimizeFrogJump(int n, int[] heights) {
        int prev = 0;
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            int fs = prev + Math.abs(heights[i] - heights[i - 1]);

            int ss = Integer.MAX_VALUE;
            if (i > 1) {
                ss = prev2 + Math.abs(heights[i] - heights[i - 2]);
            }
            int curr = Math.min(fs, ss);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] heights = {30, 10, 60, 10, 60, 50};
        int n = heights.length;
        int result1 = recursiveFrogJump(n, heights);
        int result2 = tabulationFrogJump(n, heights);
        int result3 = optimizeFrogJump(n, heights);
        System.out.println("Minimum energy required " + result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}

