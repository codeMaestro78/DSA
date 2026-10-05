package Sorting;

import java.util.*;

public class BucketSort {
    public static void main(String[] args) {
        float[] arr = { 0.42f, 0.32f, 0.73f, 0.11f, 0.85f, 0.64f, 0.29f };

        System.out.println("Before: " + Arrays.toString(arr));
        bucketSort(arr);
        System.out.println("After:  " + Arrays.toString(arr));
    }

    // Bucket sort works on a simple idea: distribute elements into buckets, sort
    // each bucket, then concatenate.

    public static void bucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 1)
            return;

        // 1 ) Create n empty buckets
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 2 ) distribute elements into buckets
        // element x goes to bucket index : (int) (x*n)
        for (float num : arr) {
            int bucketIndex = (int) (num * n);
            // we have to take care about the edge case which is if num == 1.0 then it would
            // go out of bounds
            if (bucketIndex == n)
                bucketIndex = n - 1;
            buckets[bucketIndex].add(num);
        }

        // 3 ) sort each bucket using the insertion sort (efficient for small lists)

        for (List<Float> bucket : buckets) {
            insertionSort(bucket);
        }

        // 4 ) concatenate all buckets back into arr
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    // Generalizing for any range(not just 0-1)
    // for integers or arbitarty ranges , normalize the values:

    public static void bucketSortGeneral(int[] arr, int numBuckets) {
        int n = arr.length;
        if (n <= 1)
            return;


        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        int range = max - min + 1;

        List<Integer>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // normalize - map value to bucket index
        for (int num : arr) {
            int bucketIndex = (int) ((long) (num - min) * (numBuckets - 1) / (range - 1));
            buckets[bucketIndex].add(num);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }

    }

    private static void insertionSort(List<Float> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            float key = bucket.get(i);
            int j = i - 1;

            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }
}
