package DataStructure.DynamicProgramming;

import java.util.Arrays;

// as prev frog jump we are only allowed to jump i-1 and i-2
// now we have k jumps so that we can jump ((i-1)..(i-k)) i-1, i-2, ..., i-k   (if index ≥ 0)
public class FrogJumpByKDistance {

    static int frogJumpByKRecur(int n, int[] heights, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return fk(n - 1, heights, dp, k);
    }

    static int fk(int ind, int[] heights, int[] dp, int k) {
        if (ind == 0) return 0;
        if (dp[ind] != -1) return dp[ind];

        int minEn = Integer.MAX_VALUE;

        for (int j = 1; j <= k; j++) {
            if (ind - j >= 0) {
                int jump = fk(ind - j, heights, dp, k) + Math.abs(heights[ind] - heights[ind - j]);
                minEn = Math.min(minEn, jump);
            }
        }
        return dp[ind] = minEn;
    }

    static int frogJumpKTab(int n, int[] heights, int k) {
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minEn = Integer.MAX_VALUE;

            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    minEn =Math.min(minEn,jump);
                }
            }
            dp[i] = minEn;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] heights = {30, 10, 60, 10, 60, 50};
        int n = heights.length;
        int k = 3;

        System.out.println(frogJumpByKRecur(n,heights,k));
        System.out.println(frogJumpKTab(n,heights,k));
    }
}

// now we can not optimize further from O(n*k ) to O(1)
//        dp[i] depends on k previous states
//
//        We must store k values
//
//        Space optimization below O(n) is not possible
