package Leetcode_Solve;

import java.awt.print.PrinterGraphics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q3005_CountElementsWithMaximumFrequency {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 5, 6, 7, 8, 8, 9};
        System.out.println(maxFrequencyElements(arr));
    }
    static int maxFrequencyElements(int[] nums) {
        int freq = 0;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int count : map.values()) {
            freq = Math.max(freq, count);
        }
        for (int count : map.values()) {
            if (count == freq) {
                result += count;
            }
        }
        return result;
    }
}
