package JAVA.StriverLecture.ArrayPlaylist;

import java.util.ArrayList;
import java.util.List;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 0, 0, 4, 5, 6, 7, 0, 8, 0 };
        moveZeroesOptimized(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    // Brute force
    public static List<Integer> moveZeroes(int[] arr) {
        ArrayList<Integer> temp = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                temp.add(arr[i]);

            }
        }

        int nz = temp.size();

        for (int i = 0; i < nz; i++) {
            arr[i] = temp.get(i);
        }

        for (int i = nz; i < arr.length; i++) {
            arr[i] = 0;
        }

        return temp;

    }

    // but here the time complexity is O(n) and space complexity is O(n) as we are
    // using extra space for the temp array.

    // Optimal solution

    // we can use the two pointer approach to solve this problem in O(n) time
    // complexity and O(1) space complexity.

    // Optimal in-place solution: two-pointer
    // Moves non-zero elements forward and fills the rest with zeros
    public static void moveZeroesOptimized(int[] arr) {
        int lastNonZeroFoundAt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // swap only if i and lastNonZeroFoundAt are different
                if (i != lastNonZeroFoundAt) {
                    int tmp = arr[lastNonZeroFoundAt];
                    arr[lastNonZeroFoundAt] = arr[i];
                    arr[i] = tmp;
                }
                lastNonZeroFoundAt++;
            }
        }
    }
}
