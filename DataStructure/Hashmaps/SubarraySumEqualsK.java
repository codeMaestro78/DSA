package DataStructure.Hashmaps;

import java.util.HashMap;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 1, 1, 1, 1 };
        int k = 2;
        System.out.println(subarraySum(nums, k));

    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (prefix.containsKey(sum - k)) {
                count += prefix.get(sum - k);
            }
            prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);
        }
        return count;

    }

}
