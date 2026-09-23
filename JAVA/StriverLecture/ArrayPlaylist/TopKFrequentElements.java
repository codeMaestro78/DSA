package JAVA.StriverLecture.ArrayPlaylist;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 4, 5, 5, 6, 6 };
        int k = 3;
        System.out.println(Arrays.toString(bruteForce(arr, k)));
        System.out.println(Arrays.toString(optimized(arr, k)));
    }


    // Brute force approach
    // Get all distinct elements, sort them by count descending, pick first k.

    //  Complexity
    //  time complexity:- O(n + d log d) -> worst O(n log n )
    //  space :- O(n) for map + list

    public static int[] bruteForce(int[] nums, int k) {

        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        //  sort distinct elements by frequency: O(d logd)

        List<Integer> dist = new ArrayList<>(freq.keySet());
        dist.sort((a, b) -> Integer.compare(freq.get(b), freq.get(a)));

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = dist.get(i);
        }
        return res;
    }


    // Optimized the code further:
    // Dont sort all d elements keep only best k in a min-heap . smallest of the top-k sits at top, easy to evict.
    // Walkthrough for example, k=2:
// 1. Offer 1(1) -> heap [1]
// 2. Offer 2(2) -> heap [1,2]
// 3. Offer 3(3) -> heap [1,2,3] size 3 > 2, poll 1 -> heap [2,3]
// 4. Result [2,3]



public static int[] optimized(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    // Min-Heap ordered by frequency. We keep size == k.
    // Why min? So we can evict the least frequent among current top-k in O(1).

    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(freq.get(b), freq.get(a)));

    for (int num : freq.keySet()) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    int[] res = new int[k];
    for (int i = 0; i < k; i++) {
        res[i] = minHeap.poll();
    }
    return res;
}

//  For the optimal solution We have a bucket sort( O(n))
// Frequency of any element is in range 1..n . So instead of sorting , make an array of bucket.
// where : index = frequency
// bucket[i] = list of numbers appearing 1 times
//  Then scan buckets from n downwards and collect untill we have k elements.

public static int[] optimalSolution(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();

    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    // bucket : index = frequency , value = list of nums
    // freq is at most nums.length
    List<Integer>[] bucket = new List[nums.length + 1];
    for (int num : freq.keySet()) {
        int f = freq.get(num);
        if (bucket[f] == null) {
            bucket[f] = new ArrayList<>();
        }
        bucket[f].add(num);
    }

    // Collect top k from high to low frequency
    int[] res = new int[k];
    int idx = 0;
    for (int i = bucket.length - 1; i >= 0 && idx < k; i--) {
        if (bucket[i] != null) {
            for (int num : bucket[i]) {
                res[idx++] = num;
                if (idx == k)
                    break;
            }
        }
    }
    return res;
}
}
