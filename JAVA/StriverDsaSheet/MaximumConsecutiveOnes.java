package StriverDsaSheet;

public class MaximumConsecutiveOnes {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0};
        System.out.println(maximumOne(arr));
    }

    static int maximumOne(int[] arr) {
        int maxCount = 0;
        int currentCount = 0;

        for (int num : arr) {
            if (num == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0;
            }
        }
        return maxCount;
    }

}
