package Leetcode_Solve;

public class Q1935_MaximumNumberofWordsYouCanType {
    public static void main(String[] args) {

    }

    static int canBeTypeWords(String text, String brokenLetters) {
        boolean[] arr = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            arr[c - 'a'] = true;
        }
        String[] words = text.split(" ");
        int count = 0;

        for (String word : words) {
            boolean canType = true;
            for (char c : word.toCharArray()) {
                if (arr[c - 'a']) {
                    canType = false;
                    break;
                }
            }
            if (canType) {
                count++;
            }
        }
        return count;
    }
}
