package Leetcode_Solve;

public class Q2327_NumberofPeopleAwareofaSecret {
    public static void main(String[] args) {
        System.out.println(peopleAwareOfSecret(6, 2, 4));
    }

    static int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;

        long[] dp = new long[n + 1];
        dp[1] = 1;

        long share = 0;

        for (int day = 2; day <= n; day++) {
            if (day - delay >= 1) {
                share = (share + dp[day - delay]) % MOD;
            }
            if (day - forget >= 1) {
                share = (share - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = share;
        }
        long result = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            if (i >= 1) {
                result = (result + dp[i]) % MOD;
            }
        }
        return (int) (result);
    }
}
