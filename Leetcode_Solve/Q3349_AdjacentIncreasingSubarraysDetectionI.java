package Leetcode_Solve;
// Q3349_AdjacentIncreasingSubarraysDetection I
import java.util.*;

public class Q3349_AdjacentIncreasingSubarraysDetectionI {
    public static void main(String[] args) {
    List<Integer> nums=new ArrayList<>(Arrays.asList(1,2,3,4,4,4,4,5,6,7));
    int k=3;
        System.out.println(hasIncreasingSubarrays(nums,k));
    }

    public static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        for (int i = 0; i <= n - 2 * k; i++) {
            if (isIncreasing(nums, i, k) && isIncreasing(nums, i + k, k)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIncreasing(List<Integer> nums, int start, int k) {
        for (int i = start; i < start + k - 1; i++) {
            if (nums.get(i) >= nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
