package DataStructure.Hashmaps;

import java.util.HashMap;

public class Anagrams {
    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";

        if (isAnagram(s1, s2)) {
            System.out.println(s1 + " and " + s2 + " are anagrams");
        } else {
            System.out.println(s1 + " and " + s2 + " are NOT anagrams");
        }

    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character, Integer> freq = new HashMap<>();

        for (char c : s1.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (!freq.containsKey(c)) {
                return false;
            }
            freq.put(c, freq.get(c) - 1);
            if (freq.get(c) < 0) {
                return false;
            }
        }

        for (int count : freq.values()) {
            if (count != 0)
                return false;
        }
        return true;
    }
}
