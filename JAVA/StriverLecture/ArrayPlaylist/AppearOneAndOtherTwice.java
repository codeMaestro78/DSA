package JAVA.StriverLecture.ArrayPlaylist;

import java.util.HashMap;

public class AppearOneAndOtherTwice {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 3, 4, 4, 5, 5  };

        System.out.println(appearOneAndTwice(arr));
        System.out.println(appearBetter(arr));

        System.out.println(appearMapBetter(arr));
        System.out.println(appearOptimal(arr));

    }

    // brute force one the time complexity here is the O(n^2) and the space
    // complexity is O(1);
    public static int appearOneAndTwice(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int count = 0;

            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == num) {
                    count++;
                }
            }

            if (count == 1)
                return num;
        }
        return -1;

    }

    // Better solution
    public static int appearBetter(int[] nums) {
        int maxi = nums[0];

        for (int i = 0; i < nums.length; i++) {
            maxi = Math.max(maxi, nums[i]);
        }
        // count frequency
        int[] hash = new int[maxi + 1];
        for (int i = 0; i < nums.length; i++) {
            hash[nums[i]]++;
        }

        // we can use the map here instead of the hash .cause using freq array only
        // works only when number are positive and non negative.

        for (int i = 0; i < nums.length; i++) {
            if (hash[nums[i]] == 1) {
                return nums[i];
            }
        }
        return -1;

    }

    // even better solution is using the map.

    public static int appearMapBetter(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // find the element that appears ones.
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                return nums[i];
            }
        }
        return -1;

    }

    // OPtimal solution will the xor. TC :- O(n) SC :- O(1)
    public static int appearOptimal(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }

}
