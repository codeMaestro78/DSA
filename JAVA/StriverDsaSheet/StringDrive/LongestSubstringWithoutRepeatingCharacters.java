package StriverDsaSheet.StringDrive;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
    String s= "abcabcbb";
        System.out.println(longestSubstring(s));
        System.out.println(longestSubstring2(s));
    }

    public static int longestSubstring(String s) {
        int left = 0;
        int maxLen = 0;
        HashSet<Character> set = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);


            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    //    even more optimized using an int array instead of hashset
    public static int longestSubstring2(String s) {
        int[] map = new int[256];
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (map[c] > 0) {
                left = Math.max(left, map[c]);

            }
            map[c] = right + 1;
            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;

    }
}
