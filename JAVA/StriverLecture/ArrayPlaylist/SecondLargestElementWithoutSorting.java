package StriverLecture.ArrayPlaylist;

public class SecondLargestElementWithoutSorting {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };

        System.out.println(secondLargest(arr));
    }

    public static int secondLargest(int[] arr) {
        int MAX = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > MAX) {
                secondMax = MAX;
                MAX = arr[i];
            } else if (arr[i] > secondMax && arr[i] != MAX) {
                secondMax = arr[i];
            }
        }
        return secondMax;
    }


}
