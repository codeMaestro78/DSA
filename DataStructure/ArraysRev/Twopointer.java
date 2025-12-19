package DataStructure.ArraysRev;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Twopointer {
    public static boolean twoPointer(int[] nums, int target) {
//        if the array isn't sorted then we sort it first.
        Arrays.sort(nums);

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int currentSum = nums[left] + nums[right];

            if (currentSum == target) {
                return true;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
    //above solution is best for the sorted array
//Time complexity :- O(n)


    public static int[] twoSumUnsortedArray(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complements = target - nums[i];

            if (map.containsKey(complements)) {
                return new int[]{map.get(complements), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}

//Above solution is best for when array are in unsorted order
