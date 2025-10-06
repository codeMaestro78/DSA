package DataStructure.recursion;

public class ReverseNumber {
    public static void main(String[] args) {
        System.out.println(reverse(1234,0));
        System.out.println(reverseIte(1234));

    }
    public static int reverse(int n,int rev){
        if(n==0) return rev;
        rev=rev*10+(n%10);
        return reverse(n/10,rev);
    }
    public static int reverseIte(int n){
        int rev=0;
        while(n!=0){
            int digit=n%10;
            rev=rev*10+digit;
            n/=10;
        }
        return rev;
    }
}
