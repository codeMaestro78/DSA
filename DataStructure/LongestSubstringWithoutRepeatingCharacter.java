package DataStructure;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(longestSubstring(s));
    }

    public static int longestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;

            }
            set.add(ch);
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }
}
