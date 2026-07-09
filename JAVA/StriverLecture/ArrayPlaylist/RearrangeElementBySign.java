package StriverLecture.ArrayPlaylist;

import java.util.Arrays;

public class RearrangeElementBySign {
    public static void main(String[] args) {
        int[] arr = { 3, 1, -2, -5, 2, -4 };
        System.out.println(Arrays.toString(bruteForce(arr)));
        System.out.println(Arrays.toString(optimalSolution(arr)));
    }

    //  SC:- O(2n)  and TC:- O(n)
    public static int[] bruteForce(int[] nums) {
        int[] neg = new int[nums.length / 2];
        int[] pos = new int[nums.length / 2];

        int p = 0;
        int n = 0;

        for (int num : nums) {
            if (num > 0) {
                pos[p++] = num;
            } else {
                neg[n++] = num;
            }
        }

        for (int i = 0; i < nums.length / 2; i++) {
            nums[2 * i] = pos[i];
            nums[2 * i + 1] = neg[i];
        }
        return nums;
    }

    //  this problem can be solved O(n) time using only one extra array

    public static int[] optimalSolution(int[] nums) {
        int[] ans = new int[nums.length];

        int posIndex = 0;
        int negIndex = 1;

        for (int num : nums) {
            if (num > 0) {
                ans[posIndex] = num;
                posIndex += 2;
            } else {
                ans[negIndex] = num;
                negIndex += 2;
            }
        }
        return ans;
    }

}
