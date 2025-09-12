package Leetcode_Solve;

public class Q3227_VowelsGameinaString {
    public static void main(String[] args) {
        System.out.println(doesAliceWin("bbdd"));
    }

    static boolean doesAliceWin(String s) {
        int vowels = 0;
        for (char c : s.toCharArray()) {
            if (isVowel(c)) vowels++;
        }
        return vowels % 2 == 1;
    }

    static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

}
