package StriverDsaSheet;

import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {2, 6, 5, 8, 11};
        int target = 14;

        String ans = twoSum(arr, target);
        System.out.println(ans);
    }

    //    This is using the two pointer method
    static String twoSum(int[] arr, int target) {
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == target) {
                return "YES";
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return "NO";
    }
}
