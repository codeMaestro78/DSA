package Sorting;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 4, 6};
        print(arr);
        insertionSort(arr);
    }
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
//            shift larget element to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            System.out.println(i);
            print(arr);
        }
    }
    static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
