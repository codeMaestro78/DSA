package StriverLecture.ArrayPlaylist;

//  left rotate array by one place,we can add more complexity later.
// as of now we are doing left rotation by one place, we can do right rotation by one place as well.

public class RotateArrayByKPlace {
    public static void main(String[] args) {

    }

    // time complexity is O(n) and space complexity is O(1)
    public static void leftRotateByOnePlace(int[] arr) {
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = temp;
    }

    // time complexity is O(n) and space complexity is O(1)
    public static void rightRotateByOnePlace(int[] arr) {
        int temp = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;
    }

    // left rotate by k place brute force
    public static void leftRotateByKPlcae(int[] arr, int k) {
        k = k % arr.length;

        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        for (int i = k; i < arr.length; i++) {
            arr[i - k] = arr[i];
        }

        for (int i = arr.length - k; i < arr.length; i++) {
            arr[i] = temp[i - (arr.length - k)];
        }
    }
    // But here above we have the time complexity O(k) + O(n-k) + O(k)= O (n+k) and
    // the space complexity is O(k) as we are using extra space for the temp array.

    // Here we can optimize the space complexity to O(1) by using the reversal
    // algorithm for array roatation.

    public static void leftRotateBYKPlaceOptimized(int[] arr, int k) {
        k = k % arr.length;

        // reverse the first k elements
        reverse(arr, 0, k - 1);
        // reverse the remaining n-k elements
        reverse(arr, k, arr.length - 1);
        // reverse the whole array
        reverse(arr, 0, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
