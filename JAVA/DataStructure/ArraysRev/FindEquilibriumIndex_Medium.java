package DataStructure.ArraysRev;


//Question: Find an index i such that the sum of elements on its left equals
// the sum on its right.

public class FindEquilibriumIndex_Medium {
    public static void main(String[] args) {
        int[] arr = {2, 3, -1, 8, 4};
        System.out.println(findEquilibrium(arr));


    }

    public static int findEquilibrium(int[] nums) {
        int totalSum = 0, leftSum = 0;
        for (int num : nums) totalSum += num;

        for (int i = 0; i < nums.length; i++) {
            totalSum -= nums[i];
            if (leftSum == totalSum) return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
