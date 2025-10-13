package Leetcode_Solve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Q1488_AvoidFloodinTheCity {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4 };
        System.out.println(Arrays.toString(avoidFlood(arr)));

    }

    public static int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Map<Integer, Integer> lr = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                ans[i] = 1;
            } else {
                ans[i] = -1;
                if (lr.containsKey(rains[i])) {
                    int lrd = lr.get(rains[i]);
                    Integer dryDay = dryDays.higher(lrd);
                    if (dryDay == null) {
                        return new int[0];
                    }
                    ans[dryDay] = rains[i];
                    dryDays.remove(dryDay);

                }
                lr.put(rains[i], i);
            }
        }
        return ans;
    }
}
