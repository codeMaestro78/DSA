package Leetcode_Solve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Q3346_MaximumFrequencyanElementAfterPerformingOperationsI {
    public static void main(String[] args) {

    }

    public static int maxFrequency(int[] nums, int k, int numOperations) {
        int ans = 0, maxx = nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            maxx = Math.max(maxx, num);
            map.put(num, map.getOrDefault(num, 0) + 1);

        }
        int[] count = new int[maxx + 1];
        for (int num : nums) {
            if (num - k < 0) {
                count[0]++;
            } else {
                count[num - k]++;
            }
            if (num + k + 1 <= maxx) {
                count[num + k + 1]--;
            }

        }
        for (int i = 1; i <= maxx; i++) {
            count[i] += count[i - 1];
            int freq = map.getOrDefault(i, 0);
            int extra = count[i] - freq;
            int curr = freq + Math.min(extra, numOperations);
            ans = Math.max(ans, curr);
        }
        return ans;

    }
}
