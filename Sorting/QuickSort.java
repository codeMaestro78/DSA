package Sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};

        System.out.println("Original Array:");
        print(arr);

        quickSort(arr);

        System.out.println("Sorted Array:");
        print(arr);
    }

    static int[] quickSort(int[] arr) {
        quick(arr, 0, arr.length - 1);
        return arr;
    }

    static void quick(int[] arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high);
            quick(arr, low, pIndex - 1);
            quick(arr, pIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];  // Choosing first element as pivot
        int i = low;
        int j = high;

        while (i < j) {
            while (i <= high - 1 && arr[i] <= pivot) {
                i++;
            }
            while (j >= low + 1 && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot with arr[j]
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j; // new pivot index
    }

    static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
