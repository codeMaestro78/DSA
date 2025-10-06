package DataStructure.recursion;

public class GCD {
    public static void main(String []args){
        System.out.println(gcd(48, 18));
        System.out.println(gcdIte(48, 18));
    }
//    recursive
    public static int gcd(int a,int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
//    simple (iterative) method
    public static int gcdIte(int a,int b){
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }

}
