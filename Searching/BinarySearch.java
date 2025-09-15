package Searching;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int target = 8;
        System.out.println(binarySearch(arr,target));
    }

    static int binarySearch(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] <= target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
