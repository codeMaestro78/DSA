package StriverDsaSheet.BinarySearch;

public class First_And_Last_occurence {
    public static void main(String[] args) {
        int[] nums = {2, 4, 4, 5, 6, 7};
        int target = 4;

        int[] result = searchRange(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    static int firstOccurrence(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    static int lastOccurrence(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    static int[] searchRange(int[] nums, int target) {
        int first = firstOccurrence(nums, target);
        int last = lastOccurrence(nums, target);
        return new int[]{first, last};
    }
}
