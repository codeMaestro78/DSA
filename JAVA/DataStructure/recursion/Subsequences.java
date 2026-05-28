package DataStructure.recursion;

import java.io.PrintStream;

public class Subsequences {
    public static void main(String[] args) {
        printSubsequences("abc");

    }

    public static void printSubsequences(String s) {
        helper(0, s, "");
    }

    private static void helper(int index, String s, String current) {
        if (index == s.length()) {
            System.out.println(current);
            return;
        }

//        choice 1:- including current character
        helper(index + 1, s, current + s.charAt(index));
//        choice 2- exclude current character
        helper(index + 1, s, current);
    }

}
