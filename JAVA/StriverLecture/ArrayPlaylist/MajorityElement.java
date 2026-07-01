package StriverLecture.ArrayPlaylist;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = { 2,2,3,3,1,2,2};
        System.out.println(bruteForce(arr));
        System.out.println(betterSolution(arr));
        int[] arr2 = { 7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 5, 5, 5, 5 };
        System.out.println(optimalSolution(arr2));
    }

    public static int bruteForce(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > n / 2) {
                return nums[i];
            }
        }
        return -1;

    }

    // Better solution

    public static int betterSolution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return -1;

    }

    //optimal solution
    // Moore's voting algorithm

    public static int optimalSolution(int[] nums) {
        int count = 0;
        int el = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count = 1;
                el = nums[i];
            } else if (nums[i] == el) {
                count++;

            } else {
                count--;
            }
        }

        int count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == el)
                count1++;
        }
        if (count1 > nums.length / 2) {
            return el;
        }
        return -1;
        
    }
}
