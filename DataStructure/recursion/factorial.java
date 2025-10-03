package DataStructure.recursion;

public class factorial {
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(factorial2(6));
    }

    static long factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    static long factorial2(int n) {
        long []dp = new long[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            dp[i]=dp[i-1]*i;
        }
        return dp[n];
    }
}
