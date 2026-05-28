package Sorting;

import java.util.*;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        print(arr);
        bubbleSort(arr);
        print(arr);
        System.out.println("The time complexity is: O(n^2)");
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            int didSwap = 0;
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    didSwap = 1;
                }
            }
            if (didSwap == 0) {
                break;
            }
            System.out.println("Runs");
        }
    }

    static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
