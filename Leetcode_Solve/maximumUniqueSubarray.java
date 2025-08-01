package Leetcode_Solve;
// Leetcode problem 1695
public class maximumUniqueSubarray {
    public static void main(String[] args) {
    int []arr={4,2,4,5,6};
        System.out.println(maximumUniqueSubarray(arr));
    }

    static int maximumUniqueSubarray(int[] nums) {
        int[] lastIndex = new int[10001];
        int length = nums.length;
        int[] prefixsum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            prefixsum[i + 1] = prefixsum[i] + nums[i];
        }
        int maxsum = 0;
        int windowstart = 0;
        for (int i = 1; i <= length; i++) {
            int value = nums[i - 1];
            windowstart = Math.max(windowstart, lastIndex[value]);
            maxsum = Math.max(maxsum, prefixsum[i] - prefixsum[windowstart]);
            lastIndex[value] = i;
        }
        return maxsum;
    }
}
