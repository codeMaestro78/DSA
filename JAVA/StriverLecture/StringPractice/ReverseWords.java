package StriverLecture.StringPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseWords {
    public static void main(String[] args) {
        String s = " amazing coding skills ";
        System.out.println(reverseWords(s));
        System.out.println(optimalApproach(s));
    }

    // brute force approach
    public static String reverseWords(String s) {

        List<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            }

            else if (word.length() > 0) {
                words.add(word.toString());
                // reset word
                word.setLength(0);
            }
        }

        // add the last word if present
        if (word.length() > 0) {
            words.add(word.toString());
        }

        Collections.reverse(words);

        return String.join(" ", words);

    }

    // optimal approach
    public static String optimalApproach(String s) {
        StringBuilder result = new StringBuilder();

        int i = s.length() - 1;

        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }

            if (i < 0)
                break;

            int end = i;

            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            String word = s.substring(i + 1, end + 1);

            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(word);
        }

        return result.toString();
    }
}
