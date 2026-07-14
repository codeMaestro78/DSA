package StriverLecture.ArrayPlaylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//  Leaders in the array -> Everything on the right should be smaller
public class LeadersInArray {
    public static void main(String[] args) {
        int[] arr = { 23, 12, 10, 11, 5, 6, 5, 4, 3, 2, 1 };
        System.out.println(Arrays.toString(bruteForce(arr)));
    }

    public static int[] bruteForce(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> leaders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean isLeader = true;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    isLeader = false;
                    break;
                }
            }

            if (isLeader) {
                leaders.add(nums[i]);
            }
        }
        int[] ans = new int[leaders.size()];
        for (int i = 0; i < leaders.size(); i++) {
            ans[i] = leaders.get(i);
        }
        return ans;

    }

    //  optimal solution

    public static ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (nums.length == 0) {
            return ans;
        }


        //  last element of the array is always
        int max = nums[nums.length - 1];
        ans.add(nums[nums.length - 1]);


        // check element right to left
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                ans.add(nums[i]);
                max = nums[i];
            }
        }
        Collections.reverse(ans);
        return ans;
    }

}
