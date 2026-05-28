package StriverDsaSheet;

import java.util.HashMap;

public class LengthOflongestsubaarraywithsumzro {
    public static void main(String[] args) {
        int[] arr = {9, -3, 3, -1, 6, -5};
        System.out.println(lengthOfLongest(arr));
        System.out.println(longestLengthOptimal(arr, arr.length));
    }

    //    brute force approach
    public static int lengthOfLongest(int[] arr) {
        int maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0) {
                maxLen = i + 1;
            } else if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    public static int longestLengthOptimal(int[] arr, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                max = i + 1;

            } else {
                if (map.containsKey(sum)) {
                    max = Math.max(max, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }

        }
        return max;
    }
}

//Complexity Analysis  for the brute force approach
//Time Complexity: O(N^2), where N is the size of the array. This is because we are using two nested loops to check all possible subarrays.
//
//Space Complexity: O(1), as we are not using any additional data structures that grow with input size. We are only using a few variables to store the maximum length and the current sum.

//Optimal solution

//Time Complexity: O(n), where n is the length of the string. This is because we are using a single pass through the string with two pointers, leading to linear time complexity.
//
//Space Complexity: O(1), as we are using a fixed-size hash array of size 256 (for ASCII characters) and not using any additional data structures that grow with input size.
