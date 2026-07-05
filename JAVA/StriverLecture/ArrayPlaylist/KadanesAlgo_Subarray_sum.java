package StriverLecture.ArrayPlaylist;

import java.util.Arrays;

// Maximum subarray sum
public class KadanesAlgo_Subarray_sum {
    public static void main(String[] args) {
        int[] arr = { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println(bruteForce(arr));
        System.out.println(Arrays.toString(optimalSolution(arr)));
    }

    // brute force solution
    public static int bruteForce(int[] nums) {
        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxi = Math.max(maxi, sum);
            }
        }

        return maxi;
    }


    // most optimal solution using the kadane's algorithm.


    public static int[] optimalSolution(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0, end = 0, s = 0;

        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] < nums[i]) {
                sum = nums[i];
                s = i;
            } else {
                sum += nums[i];
            }

            if (sum > maxi) {
                maxi = sum;
                start = s;
                end = i;
            }
        }

        return Arrays.copyOfRange(nums, start, end + 1);
    }
}
