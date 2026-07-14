package StriverLecture.ArrayPlaylist;

import java.util.*;
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int []arr = { 100, 4, 200, 1, 3, 2 };
        System.out.println(longestConsecutive(arr));
    }



    // O(n^3) worst case. Outer loop n, while loop can run n, and contains is O(n).
    // Too slow.
    // Brute force solution
    public static int longestConsecutive(int[] nums) {
        int longest = 0;

        for (int num : nums) {
            int currSum = num;
            int streak = 1;

            while (contains(nums, currSum + 1)) {
                currSum += 1;
                streak += 1;
            }
            longest = Math.max(longest, streak);
        }
        return longest;
    }

    private static boolean contains(int[] nums, int target) {
        for (int num : nums)
            if (num == target)
                return true;

        return false;
    }


    // Better solution is the sort first. if we sort first then consecutive numbers sit next to each other.
    // O(n log n) time due to sorting, O(1) or O(n) space depending on sort.
    public static int betterSolution(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        Arrays.sort(nums); // O(n log n)

        int longest = 1;
        int streak = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;   // skipping the duplicates
            } else if (nums[i] == nums[i - 1] + 1) {
                streak++;
            } else {
                longest = Math.max(longest, streak);
                streak = 1;
            }
        }
        return Math.max(longest, streak);
    }
}
