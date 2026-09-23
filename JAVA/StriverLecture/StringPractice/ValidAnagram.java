package JAVA.StriverLecture.StringPractice;

import java.util.*;

public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagramBruteForce(s, t));
        System.out.println(isAnagramOptimized(s, t));
        System.out.println(isAnagramOptimal(s, t));

    }

    public static boolean isAnagramBruteForce(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        StringBuilder remaining = new StringBuilder(t);

        for (char c : s.toCharArray()) {
            int index = remaining.indexOf(String.valueOf(c));
            if (index == -1) {
                return false;
            }
            remaining.deleteCharAt(index);
        }
        return true;
        //  This solution take O(n^2) time complexity and O(n) space complexity
        // Searching and delete through stringbuilder take O(n).

    }

    public static boolean isAnagramOptimized(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // second approach is a optimized approach using the sorting  .
        //  if we sort the two string then they become identical/

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);

        //  in this approach the time coplexity is O(n log n )
        //  sorting takes O(n log n ) for each string.
        // and the space comp is O(n).

    }
    public static boolean isAnagramOptimal(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Frequency Counting
        // instead of sorting we can ask how many time does each character occurs?

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
}

