package Leetcode_Solve;

public class Q121_buyandsellstock {
    public static void main(String[] args) {
    int []prices={7,1,5,3,6,4};
        System.out.println(butAndSell(prices));
    }

    public static int butAndSell(int[] prices) {
        int min = Integer.MAX_VALUE, maxProfit = 0;

        for (int p : prices) {
            if (p < min) min = p;
            else maxProfit = Math.max(maxProfit, p - min);
        }
        return maxProfit;
    }
}
