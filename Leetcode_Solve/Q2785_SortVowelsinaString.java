package Leetcode_Solve;

import java.util.ArrayList;
import java.util.Collections;

public class Q2785_SortVowelsinaString {
    public static void main(String[] args) {
        String s1 = "lEetcOde";
        System.out.println(sortVowels(s1));

        // Example 2
        String s2 = "lYmpH";
        System.out.println(sortVowels(s2));
    }

    static boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) != -1;
    }

    static String sortVowels(String s) {
        ArrayList<Character> vowels = new ArrayList<Character>();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }
        Collections.sort(vowels);
        StringBuilder sb = new StringBuilder();
        int vowelIndex = 0;
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                sb.append(vowels.get(vowelIndex++));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
