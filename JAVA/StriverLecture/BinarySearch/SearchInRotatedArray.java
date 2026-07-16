package StriverLecture.BinarySearch;
// Search in Rotated Sorted Array
public class SearchInRotatedArray {
    public static void main(String[] args) {
        int[] arr = { 3, 4, 5, 6, 1, 2 };
        System.out.println(search(arr, 6));
    }

    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
        }
        return -1;

    }
}
