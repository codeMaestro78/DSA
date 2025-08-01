package StriverDsaSheet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {7, 7, 7, 7, 7, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1,1,1,11,1,1,1,1,1,1,11,1,1,1,1,1,1,1};
        System.out.println(arr.length);
        System.out.println("Array: " + Arrays.toString(arr));
//        int result = findMajorityElement(arr);
//        System.out.println("Majority Element: " + result);
//        int result = majorityElementBet  ter(arr);
//        System.out.println("Majority Element: " + result);
        int result = MajorityElementOptimal(arr);
        System.out.println("Majority Element: " + result);
    }
//    Brute force solution with time complexity of O(n^2)


    static int findMajorityElement(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            if (count > n / 2) {
                return nums[i];
            }
        }

        return -1;
    }

//    Better solution using hashmap time complexity will be (nlog(n))

    static int majorityElementBetter(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

        }
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() > nums.length / 2) {
                return item.getKey();
            }
        }
        return -1;
    }

    //    optimal solution using moore's voting algorithms
//     Below is the moores voting algorithm
    static int MajorityElementOptimal(int[] nums) {
        int count = 0;
        int element = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count = 1;
                element = nums[i];
            } else if (nums[i] == element) {
                count++;
            } else {
                count--;
            }
        }
        int count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == element) {
                count1++;
            }
        }
        if (count1 > (nums.length / 2)) {
            return element;
        }
        return -1;
    }
}
