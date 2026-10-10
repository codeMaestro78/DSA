package DataStructure.SlidingWindow;


// Maximum Average Subarray I - LeetCode 643

// Core Task:You have nums=[1,12,-5,-6,50,3],k=4.
//  Find a continuous block of exactly k elements with max average.Return the average.
public class MaximumSubarrayAverage {
    public static void main(String[] args) {

    }

    public static double findMaxAverage(int[] nums, int k) {

        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;

        for (int right = k; right < nums.length; right++) {
            windowSum += nums[right];
            windowSum -= nums[right - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) (maxSum / k);
    }
}
