package DataStructure.DynamicProgramming;

// Leetcode 70
public class ClimbingStairs {
    static int climbingStairs(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

//trick for dp
// 1 ) Try to represent problem in terms of  index.
// 2 ) do all possible stuff on that index
// 3 ) sum of all stuffs --> count all the ways
// 4 ) min (of all stuffs) --> if question asked to find min  and same for the max  if asked
// 5 ) 
