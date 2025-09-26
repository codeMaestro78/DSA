package StriverDsaSheet;

import java.util.Arrays;

public class Sortanarrayofzeroesandones {

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        System.out.println(Arrays.toString(sortarratzeroes(arr)));
    }
// Insertion sort
    static int[] sortarratzeroes(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
        return nums;
    }
}
