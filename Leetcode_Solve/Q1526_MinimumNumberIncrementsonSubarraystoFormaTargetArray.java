package Leetcode_Solve;
//hard
public class Q1526_MinimumNumberIncrementsonSubarraystoFormaTargetArray {
    public static void main(String[] args) {
    int []arr={1,2,3,2,1};
        System.out.println(minNumberOperations(arr));
    }

    public static int minNumberOperations(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int ops = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                ops += nums[i] - nums[i - 1];
            }
        }
        return ops;
    }
}
