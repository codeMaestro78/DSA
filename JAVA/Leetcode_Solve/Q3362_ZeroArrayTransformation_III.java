package Leetcode_Solve;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Q3362_ZeroArrayTransformation_III {
    public static void main(String[] args) {
        int []nums={2,0,2};
        int [][]queries={{0,2},{0,2},{1,1}};
        System.out.println(maxRemoval(nums,queries));


    }

    static int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> available = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> running = new PriorityQueue<>();

        int idx = 0;
        for (int i = 0; i < n; i++) {
            while (idx < queries.length && queries[idx][0] <= i) {
                available.offer(queries[idx][1]);
                idx++;
            }
            while (!running.isEmpty() && running.peek() < i) {
                running.poll();
            }
            while (nums[i] > running.size()) {
                if (available.isEmpty() || available.peek() < i)
                    return -1;
                running.offer(available.poll());
            }
        }
        return available.size();
    }
}
