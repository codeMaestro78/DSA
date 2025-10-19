package Leetcode_Solve;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class Q1625_LexicographicallySmallestStringAfterApplyingOperations {
    public static void main(String[] args) {

    }

    public String findLexSmallestString(String s, int a, int b) {
        Deque<String> q = new ArrayDeque<>();
        q.offer(s);
        HashSet<String> vis = new HashSet<>();
        vis.add(s);
        String ans = s;
        int n = s.length();
        while (!q.isEmpty()) {

            if (ans.compareTo(s) > 0) {
                ans = s;
            }
            char[] ch = s.toCharArray();
            for (int i = 1; i < n; i += 2) {
                ch[i] = (char) (((ch[i] - '0' + a) % 10) + '0');
            }
            String t1 = String.valueOf(ch);
            String t2 = s.substring(n - b) + s.substring(0, n - b);
            for (String t : List.of(t1, t2)) {
                if (vis.add(t)) {
                    q.offer(t);
                }
            }
        }
        return ans;
    }
}
