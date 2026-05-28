package DataStructure.DynamicProgramming;

public class HouseRobber {

    static int fOptimized(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            int take = nums[i];
            if (i > 1) take += prev2;

            int notTake = prev;

            int curr = Math.max(take, notTake);
            prev2 = prev;
            prev = curr;

        }
        return prev;
    }

    static long robHouse(int[] money) {
       int n = money.length;
        if (n == 1) return money[0];

        // exclude first house
        int[] temp1 = new int[n - 1];
        // exclude last house
        int[] temp2 = new int[n - 1];

        for (int i = 0; i <  n - 1; i++) {
            temp1[i] = money[i + 1];
            temp2[i] = money[i];
        }

        return Math.max(fOptimized(temp1), fOptimized(temp2));
    }
}
