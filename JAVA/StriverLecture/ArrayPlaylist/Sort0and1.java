package StriverLecture.ArrayPlaylist;

import java.util.Arrays;

public class Sort0and1 {
    public static void main(String[] args) {
        int [] arr = {0,1,2,0,1,2,1,2,0,0,0,1};
        System.out.println(Arrays.toString(sortingBetter(arr)));

        Sort0and1 so = new Sort0and1();
        so.print(arr);
    }

    public static int[] sortingBetter(int[] nums) {
        int count1 = 0, count2 = 0, count3 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                count1++;
            if (nums[i] == 1)
                count2++;
            if (nums[i] == 2)
                count3++;
        }
        for (int i = 0; i < count1; i++) {
            nums[i] = 0;
        }
        for (int i = count1; i < count1 + count2; i++) {
            nums[i] = 1;
        }
        for (int i = count1 + count2; i < nums.length; i++) {
            nums[i] = 2;
        }

        return nums;
    }

    // But the optimal solution is very important.
    // Dutch national flag algorithm.
    //  the time complexity here is O(n) and space complexity is O(1).

    public static void optimalSolution(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums,low,mid);
                low++;
                mid++;

            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums,mid,high);
                high--;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    private int[] print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        return nums;
    }

}
