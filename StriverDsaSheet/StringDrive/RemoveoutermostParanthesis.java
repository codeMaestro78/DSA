package StriverDsaSheet.StringDrive;

import java.util.Stack;

public class RemoveoutermostParanthesis {
    public static void main(String[] args) {
        String st="(()())(())";
        System.out.println(removeOuterMostParentheses(st));

    }
    public static String removeOuterMostParentheses(String s){
        Stack<Character> st= new Stack<>();
        StringBuilder sb= new StringBuilder();

        for(int i=0;i<s.length();i++){
            char ch =  s.charAt(i);

            if(ch == '('){
                if(!st.isEmpty()){
                    sb.append(ch);
                }
                st.push(ch);
            }
            else{
                st.pop();
                if(!st.isEmpty()){
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
}
