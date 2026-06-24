package JAVA.StriverLecture.ArrayPlaylist;

public class MaximumConsecutiveOnes {
    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 2, 3, 4, 5, 6, 1, 1, 1, 1, 5, 6 };
        System.out.println(findMaxConsecutiveOnes(arr));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }
        return maxCount;
    }
}
