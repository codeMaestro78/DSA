package StriverLecture.ArrayPlaylist;

public class BuyAndSellStock {
    public static void main(String[] args) {
        int[] arr = { 7, 1, 5, 6, 8, 9, 4, 5 };
        System.out.println(buyAndSellStocks(arr));
    }

    public static int buyAndSellStocks(int []nums) {
        int mini = nums[0];
        int profit = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int cost = nums[i] - mini;
            profit = Math.max(profit, cost);
            mini = Math.min(mini, nums[i]);
        }
        return profit;
    }
}
