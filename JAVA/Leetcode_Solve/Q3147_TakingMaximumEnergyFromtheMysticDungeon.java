package Leetcode_Solve;

public class Q3147_TakingMaximumEnergyFromtheMysticDungeon {
    public static void main(String[] args) {
    int []arr={5,2,-10,-5,1};
    int k=3;
        System.out.println(maximumEnergies(arr,k));

    }

    public static int maximumEnergies(int[] Energy, int k) {
        int n = Energy.length;
        int[] dp = new int[n];
        int maximum = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Energy[i];
            if (i + k < n) {
                dp[i] += dp[i + k];
            }
            maximum = Math.max(maximum, dp[i]);
        }
        return maximum;
    }
}
