package DataStructure.Hashmaps;

import java.util.HashMap;

public class frequencyOfElement {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 4, 5, 6, 6, 6, 7, 7, 8, 8, 9 };
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        System.out.println(freq);
    }
}
