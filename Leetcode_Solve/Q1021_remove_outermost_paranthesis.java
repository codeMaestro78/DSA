package Leetcode_Solve;
import java.util.Stack;
public class Q1021_remove_outermost_paranthesis {
    public static void main(String[] args) {
        String s = "(()())(())";
        System.out.println(removeOuterParenthesesUsingStack(s));
    }
//    using recursion
    static String removeOuterParentheses(String s) {
        return helper(s, 0);
    }
    static String helper(String s, int start) {
        if (start >= s.length()) return "";
        int count = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                String inner = s.substring(start + 1, i);
                return inner + helper(s, i + 1);
            }
        }
        return "";
    }

    //    using stack implementation time complexity O(n)
    static String removeOuterParenthesesUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (!stack.isEmpty()) {
                    sb.append(c);
                }
                stack.push(c);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

}
