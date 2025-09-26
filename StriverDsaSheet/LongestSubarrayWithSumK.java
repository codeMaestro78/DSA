package StriverDsaSheet;

import java.util.HashMap;

public class LongestSubarrayWithSumK {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 1, 1, 1, 3, 3};
        System.out.println(usingTwoPointer(arr, 6));

    }

    static int bruteforce(int[] arr, int k) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }
//  The time complexity of the brute force solution is (O(n^2))

    static int optimal(int[] arr, int k) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == k) {
                maxLen = i + 1;
            }
            if (prefixSum.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - prefixSum.get(sum - k));
            }
//            only store the first occurence to get the longest subarray...

            if (prefixSum.containsKey(sum)) {
                prefixSum.put(sum, i);
            }
        }
        return maxLen;
    }
//    using the hashmap the time complexity of this is (O(n))

    static int usingTwoPointer(int[] arr, int k) {
        int right = 0, left = 0;
        int sum = arr[0];
        int maxLen = 0;
        int n = arr.length;

        while (right < n) {
            while (left <= right && sum > k) {
                sum -= arr[left];
                left++;
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if (right < n) {
                sum += arr[right];
            }
        }
        return maxLen;
    }

}

