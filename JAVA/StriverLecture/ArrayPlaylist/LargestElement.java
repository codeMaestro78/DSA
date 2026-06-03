package StriverLecture.ArrayPlaylist;

public class LargestElement {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };

        System.out.println(largestElement(arr, 5));
    }


    public static int largestElement(int[] arr, int n) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i]>max) {
                max = arr[i];
            }
        }
        return max;
    }
}
