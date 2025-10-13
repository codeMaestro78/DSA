package Leetcode_Solve;

import java.util.Arrays;

public class Q2300_SuccessfulPairsofSpellsandPotions {
    public static void main(String[] args) {
    int []s={5,1,3};
    int []p={1,2,3,4,5};
        System.out.println(Arrays.toString(successfulPairs(s,p,7)));
    }

    public static  int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int s = spells[i];

            int left = 0, right = m - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = (long) potions[mid] * s;
                if (product >= success) {
                    right = mid - 1;

                } else {
                    left = mid + 1;
                }
            }
            result[i]=m-left;
        }
        return result;
    }
}
