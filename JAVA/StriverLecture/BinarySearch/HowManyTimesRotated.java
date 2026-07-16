package StriverLecture.BinarySearch;

// Find out how many times the array has been rotated
public class HowManyTimesRotated {
    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2, 3 };
        System.out.println(ArrayRotated(arr));
        System.out.println(betterSolution(arr));
        System.out.println(optimalSolution(arr));
    }


    // Brute force solution
    public static int ArrayRotated(int[] nums) {
        int smallest = nums[0];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < smallest) {
                smallest = nums[i];
                index = i;
            }
        }
        return index;
    }

    // Better solution
    // Think of the sorted array as a sorted belt of numbers. Rotation just cuts the
    // belt at one position and reattaches it. The spot where the order breaks where
    // a number is bigger than the next number is exactly where the cut happened. So
    // instead of searching for the minimum value by comparing all values to a
    // running minimum, just walk once through the array and find the first place
    // where the sequence goes down. The rotation count is the number of steps from
    // the start to that break point. If there’s no break, the array wasn’t rotated.

    // Time complexity O(n), Spcace complexity O(1)
    public static int betterSolution(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i + 1;
            }
        }
        return 0;
    }


    // Think of the rotated sorted array as two sorted halves the rotation “break”
    // point is where the smallest element lives. Using binary search, we can
    // efficiently zoom in on this smallest element by comparing middle elements to
    // the rightmost element. If the middle element is greater than the rightmost
    // element, the rotation point is to the right. Otherwise, it's to the left or
    // could be the middle itself. This way, we reduce the search space by half each
    // time, getting the rotation count in O(log n).

    public static int optimalSolution(int[] nums) {
        int low =0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            high = mid;
        }
        return low;
    }

}
