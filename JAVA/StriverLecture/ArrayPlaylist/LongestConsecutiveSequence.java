package StriverLecture.ArrayPlaylist;

import java.util.*;
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int []arr = { 100, 4, 200, 1, 3, 2 };
        System.out.println(longestConsecutive(arr));
        System.out.println(betterSolution(arr));
        System.out.println(optimalSolution(arr));
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
                continue; // skipping the duplicates
            } else if (nums[i] == nums[i - 1] + 1) {
                streak++;
            } else {
                longest = Math.max(longest, streak);
                streak = 1;
            }
        }
        return Math.max(longest, streak);
    }


    //  Optimal solution :
    // However optimal hashset solution isn't always the optimal in real life. it depends on constraints.
    // Constraint 1: Values must be hashable + fit in memory
    // Constraint 2: O(n) extra space is allowed
    // Constraint 3: No integer overflow / underflow
    // Constraint 4: HashSet gives average O(1), not worst case
    // Constraint 5: Input has duplicates / negative numbers


//     Why O(n)?
// The outer loop runs n times. But the inner while only runs for numbers that extend a sequence. Each number gets visited once in total across all while loops. So amortized O(n).

// Space: O(n) for the HashSet.

    public static int optimalSolution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num:nums)
            set.add(num);

        int longest = 0;
        for (int num : set) {
            // Only start counting if 'num' is the start of a sequence
            if (!set.contains(num - 1)) {
                int currSum = num;
                int streak = 1;

                while (set.contains(currSum + 1)) {
                    currSum++;
                    streak++;
                }
                longest = Math.max(longest, streak);
            }
        }
        return longest;
    }
}
