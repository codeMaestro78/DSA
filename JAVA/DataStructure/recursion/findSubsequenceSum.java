package DataStructure.recursion;

import java.util.ArrayList;
import java.util.List;

public class findSubsequenceSum {
    public static void main(String[] args) {
        int[] arr = {1,  2, 1};
        int target = 2;
        SubsequenceSum(arr, target);
    }

    public static void SubsequenceSum(int[] nums, int target) {
        helper(0, nums, target, 0, new ArrayList<>());
    }

    private static void helper(int index, int[] nums, int target, int currentSum, List<Integer> current) {
        if (index == nums.length) {
            if (currentSum == target) {
                System.out.println(current);
            }
            return;
        }
//        include
        current.add(nums[index]);
        helper(index + 1, nums, target, currentSum + nums[index], current);

//        exclude
        current.remove(current.size() - 1);
        helper(index + 1, nums, target, currentSum, current);
    }
}
