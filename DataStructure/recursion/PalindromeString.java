package DataStructure.recursion;

public class PalindromeString {
    public static void main(String[] args) {
    String str="madam";
        System.out.println(isPalindrome(str,0,str.length()-1));
        System.out.println(isPalindromeIte(str));
    }

//    recursive
    public static boolean isPalindrome(String s, int left,int right){
        if(left>=right)return true;
        if(s.charAt(left)!=s.charAt(right)) return false;
        return isPalindrome(s,left+1,right-1);
    }
//    iterative
    public static boolean isPalindromeIte(String s){
        int left=0,right=s.length()-1;
        while(left<right){
               if(s.charAt(left)!=s.charAt(right)) return false;
               left++;
               right--;

        }
        return true;
    }
}

