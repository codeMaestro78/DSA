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

/*Time complexity :-  O(n) Even though there is a while loop inside the for loop each character is visible by left and right pointers
* at most once*/
/* Space complexity :- O(min(m,n)) where n is the string length and m is the size od the character set */
