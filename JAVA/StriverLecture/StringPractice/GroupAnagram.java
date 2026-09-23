package JAVA.StriverLecture.StringPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GroupAnagram {
    public static void main(String[] args) {
        String[] str = { "act", "pots", "tops", "cat", "stop", "hat" };
        System.out.println(groupAnagramBruteForce(str));
        System.out.println(groupAnagramOptimized(str));
        System.out.println(groupAnagramOptimal(str));

    }

    // brute force approach
    // Check existing group.
    // Compare it with a representative string from each group.
    // if its and anagram , put it in that group.
    // otherwise create a new group.
    // Time: // O(n² × k)
    // Space: O(n × k)

    public static List<List<String>> groupAnagramBruteForce(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        for (String str : strs) {
            boolean found = false;

            for (List<String> group : result) {
                if (isAnagram(group.get(0), str)) {
                    group.add(str);
                    found = true;
                    break;
                }
            }

            if (!found) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                result.add(newGroup);
            }
        }
        return result;
    }

    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

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

    // Optimized version - sort each string\
    // If two strings are anagrams, their sorted versions are identical.
    // So we can use the sorted string as the HashMap key.
    // Sorting a string of length k:
    //
    // O(k log k)
    //
    // For n strings:
    //
    // Time: O(n × k log k)
    // Space: O(n × k)

    public static List<List<String>> groupAnagramOptimized(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();

            Arrays.sort(chars);

            String key = new String(chars);

            result.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(result.values());
    }

    // optimal solution - character frequency as key

    public static List<List<String>> groupAnagramOptimal(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];

            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder key = new StringBuilder();

            for (int val : count) {
                key.append(val).append('#');
            }
            // if (!map.containsKey(key.toString())) {
            //     map.put(key.toString(), new ArrayList<>());
            // }
            // map.get(key.toString()).add(str);

            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

}
