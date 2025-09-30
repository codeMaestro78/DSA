package Leetcode_Solve;

public class Q1039_MinimumScoreTriangulationofPolygon {
    public static void main(String[] args) {

    }

    public int minScoretriangulation(int[] values) {
        int n = values.length;
        Integer[][] dp = new Integer[n][n];
        return helper(values, 0, n - 1, dp);
    }

    private int helper(int[] values, int s, int e, Integer[][] dp) {
        if (s + 1 == e) {
            return 0;
        }
        if (dp[s][e] != null) {
            return dp[s][e];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = s + 1; i < e; i++) {
            ans = Math.min(ans,
                    values[s] * values[e] * values[i] + helper(values, s, i, dp) + helper(values, i, e, dp));
        }
        dp[s][e] = ans;

        return ans;
    }
}
