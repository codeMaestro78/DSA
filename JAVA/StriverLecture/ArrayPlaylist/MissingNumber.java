package JAVA.StriverLecture.ArrayPlaylist;

public class MissingNumber {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 5, 6, 7, 8, 9 };

        System.out.println(missingNumber(arr));
        System.out.println(missingNumberOptimized(arr));
        System.out.println(missingNumberXOR(arr));
    }

    // brute force solution time complexity: O(n^2) and space complexity: O(1)
    public static int missingNumber(int[] nums) {
        int n = nums.length;

        for (int i = 1; i <= n + 1; i++) {
            boolean flag = false;

            for (int j = 0; j < n; j++) {
                if (nums[j] == i) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return i;
            }
        }
        return -1;

    }

    // optimize solution (sum formula)

    static int missingNumberOptimized(int[] nums) {
        int n = nums.length;
        long expectedSum = (long) (n + 1) * (n + 2) / 2;
        long actualSum = 0;

        for (int num : nums) {
            actualSum += num;

        }
        return (int) (expectedSum - actualSum);
    }


    // optimal solution using the XOR operation

    static int missingNumberXOR(int[] nums) {
        int n = nums.length;

        int xor1 = 0;
        int xor2 = 0;

        for (int i = 1; i <= n + 1; i++) {
            xor1 ^= i;
        }

        for (int num : nums) {
            xor2 ^= num;
        }
        return xor1 ^ xor2;
    }

}
