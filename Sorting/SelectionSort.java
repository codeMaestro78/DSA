package Sorting;

public class SelectionSort {
    public static void main(String[] args) {

        int[] arr = {64, 25, 12, 22, 11};

        print(arr);
        selectionSort(arr);
        print(arr);
    }

    static void selectionSort(int[] arr) {

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int miniIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[miniIndex]) {
                    miniIndex = j;
                }
            }
            int temp = arr[miniIndex];
            arr[miniIndex] = arr[i];
            arr[i] = temp;
        }
    }

    static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
