package StriverLecture.BinarySearch;


// Binary search only works on the sorted data, instead of scannnig linearly O(n) , we repeteadly cut the problem in the half O(log n ).
public class BS {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(binarySearch(arr, 4));
    }

    public static int binarySearch(int[] nums,int target) {
        int low = 0;
        int high = nums.length - 1;


        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;

            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;

    }
}
