package StriverLecture.ArrayPlaylist;

import java.util.Arrays;
import java.util.HashMap;

public class Twosum {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int target = 14;

        System.out.println(Arrays.toString(bruteForce(arr, target)));

        System.out.println(Arrays.toString(betterSolution(arr, target)));

        System.out.println(slightlyBetter(arr, target));
    }

    // time complexity is going to be O(n^2)
    public static int[] bruteForce(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }

        }
        return new int[] { -1, -1 };

    }

    // Better solution is Hashing.

    public static int[] betterSolution(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int needed = target - num;

            if (map.containsKey(needed)) {
                return new int[] { map.get(needed), i };
            }
            map.put(num, i);
        }
        return new int[] { -1, -1 };
    }

    // slightly better solution if hashmap is not allowed so then we can use the two
    // pointer.

    public static String slightlyBetter(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return "YES";
            } else if (sum < target)
                left++;
            else
                right--;
        }
        return "NO";
    }

}
