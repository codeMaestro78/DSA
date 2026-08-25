package StriverLecture.StringPractice;

public class RemoveOMParantheses {
    public static void main(String[] args) {
        String s = "(()())(())";

        RemoveOMParantheses sh = new RemoveOMParantheses();

        String ans = removeOuterMostParantheses(s);

        System.out.println(ans);

    }

    public static String removeOuterMostParantheses(String s) {
        StringBuilder result = new StringBuilder();
        int level = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                level++;
                if (level > 1) {
                    result.append(ch);
                }
            } else if (ch == ')') {
                if (level > 1) {
                    result.append(ch);
                }
                level--;
            }
        }
        return result.toString();
    }
}
