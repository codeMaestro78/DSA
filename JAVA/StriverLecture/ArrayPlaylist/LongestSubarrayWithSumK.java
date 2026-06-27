package StriverLecture.ArrayPlaylist;

import java.util.HashMap;

//
public class LongestSubarrayWithSumK {
    public static void main(String[] args) {
        int[] arr = { 1, 2,3,1,1,1,1,4,2,3};
        System.out.println(bruteForceLongestSubarray(arr, 3));
        int[] arr2 = { 0, 0, 0, 0, 0 };
        System.out.println(betterSolution(arr2, 0));

        int[] arr3 = { 1, 2, 3, 4, 5, 5, 5, 5, 6, 7, 8, 9, 9 };
        System.out.println(optimalSolution(arr3,20));
    }

    // brute force

    public static int bruteForceLongestSubarray(int[] nums, int target) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {


                // for (int k = i; k <= j; k++) {
                    // Brute Force:
                    // We calculate the sum of every subarray by traversing from index i to j.
                    // This extra loop makes the overall time complexity O(n^3),
                    // because for every (i, j) pair we iterate through the entire subarray.
                    // sum += nums[k];

                    // Optimization:
                    // Instead of recalculating the sum of the subarray from scratch,
                    // we can keep a running sum while extending the subarray.
                    // Since j is increasing one step at a time, we only need to add nums[j]
                    // to the existing sum.
                    //
                    // This removes the innermost loop, reducing the overall
                    // time complexity from O(n^3) to O(n^2).

                    sum += nums[j];
                // }
                if (sum == target) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    //  Better solution

    public static int betterSolution(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                maxLength = Math.max(maxLength, i + 1);
            }

            int rem = sum - k;
            if (prefixSum.containsKey(rem)) {
                int len = i - prefixSum.get(rem);
                maxLength = Math.max(maxLength, len);
            }
            if (!prefixSum.containsKey(sum)) {
                prefixSum.put(sum, i);
            }
        }

        return maxLength;
    }

    // optimal solution --> THe array contains only non-negative numbers  (0 and +ve) time complexity O(n) and sc O(1)
    public static int optimalSolution(int[] nums, int k) {
        int left = 0;
        int right = 0;

        int sum = nums[0];
        int maxLen = 0;
        int n = nums.length;

        while (right < n) {
            while (left <= right && sum > k) {
                sum -= nums[left];
                left++;

            }
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if (right < n)
                sum += nums[right];
        }
        return maxLen;
    }

}
