package DataStructure.StackDS;

public class BalanceParantheses {

    private class CharStack {
        char[] arr;
        int top;


        public CharStack(int cap) {
            arr = new char[cap];
            top = -1;
        }

        public void push(char ch) {
            top++;
            arr[top] = ch;
        }

        public char pop() {
            char var = arr[top];
            top--;
            return var;
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }


    public String balanceParantheses(String s) {
        if (s == null) {
            return "Not balanced";
        }
        if (s.length() == 0) {
            return "Balanced";
        }

        CharStack st = new CharStack(s.length());
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (st.isEmpty()) {
                    return "Not balanced";
                }
                char open = st.pop();
                if ((ch == ')' && open != '(') ||
                        (ch == ']' && open != '[') ||
                        (ch == '}' && open != '{')) {
                    return "Not balanced";   
                }

            }
        }

        if (st.isEmpty()) {
            return " balanced";
        } else {
            return "NOt Balanced";
        }
    }

    public static void main(String[] args) {

        BalanceParantheses bp = new BalanceParantheses();

        System.out.println(bp.balanceParantheses("()"));
        System.out.println(bp.balanceParantheses("({[]})"));
        System.out.println(bp.balanceParantheses("({[})"));
        System.out.println(bp.balanceParantheses("(()"));
        System.out.println(bp.balanceParantheses(""));
    }

}
