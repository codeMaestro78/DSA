package DataStructure.ArraysRev;
import java.util.Arrays;
public class RotateArrayByk {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println("Original: " + java.util.Arrays.toString(arr));
        rotate(arr, k);
        System.out.println("Rotated : " + java.util.Arrays.toString(arr));
    }
    public static void rotate(int[] arr, int k) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        k = k % n;
//        1 ) reverse first n-k elements
        reverse(arr, 0, n - k - 1);
//        2 ) reverse last k elements
        reverse(arr, n - k, n - 1);
//        3 ) reverse whole array
        reverse(arr, 0, n - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    }

//Normalize k: k = k % n (to handle cases where k > n)
//Reverse the first n - k elements
//Reverse the last k elements
//Reverse the entire array

//In-place, O(1) extra space
