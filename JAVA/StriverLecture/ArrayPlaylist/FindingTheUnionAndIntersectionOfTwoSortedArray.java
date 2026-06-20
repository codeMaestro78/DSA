package StriverLecture.ArrayPlaylist;

import java.util.ArrayList;
import java.util.List;

public class FindingTheUnionAndIntersectionOfTwoSortedArray {
    public static void main(String[] args) {

    }

    // Brute force union: O((n + m)^2) in the worst case because of duplicate checks.
    public static int[] unionBruteForce(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();

        addUnique(result, arr1);
        addUnique(result, arr2);

        return toArray(result);
    }

    // Optimal union for sorted arrays: O(n + m)
    public static int[] unionOptimal(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < arr1.length && j < arr2.length) {
            int value;
            if (arr1[i] < arr2[j]) {
                value = arr1[i++];
            } else if (arr2[j] < arr1[i]) {
                value = arr2[j++];
            } else {
                value = arr1[i];
                i++;
                j++;
            }

            appendIfNew(result, value);
        }

        while (i < arr1.length) {
            appendIfNew(result, arr1[i++]);
        }

        while (j < arr2.length) {
            appendIfNew(result, arr2[j++]);
        }

        return toArray(result);
    }

    // Brute force intersection: O(n * m)
    public static int[] intersectionBruteForce(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr1.length; i++) {
            if (i > 0 && arr1[i] == arr1[i - 1]) {
                continue;
            }

            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    appendIfNew(result, arr1[i]);
                    break;
                }
            }
        }

        return toArray(result);
    }

    // Optimal intersection for sorted arrays: O(n + m)
    public static int[] intersectionOptimal(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr1[i]) {
                j++;
            } else {
                appendIfNew(result, arr1[i]);
                i++;
                j++;
            }
        }

        return toArray(result);
    }

    private static void addUnique(List<Integer> result, int[] arr) {
        for (int value : arr) {
            appendIfNew(result, value);
        }
    }

    private static void appendIfNew(List<Integer> result, int value) {
        if (result.isEmpty() || result.get(result.size() - 1) != value) {
            result.add(value);
        }
    }

    private static int[] toArray(List<Integer> result) {
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

}
