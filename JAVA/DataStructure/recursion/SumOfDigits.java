package DataStructure.recursion;

public class SumOfDigits {
    public static void main(String[] args) {
        System.out.println(sod(123));
        System.out.println(sodIte(1011225));
    }
//    recursive
    public static int sod(int n){
        if(n==0) return 0;
        return (n%10) + sod(n/10);
    }
//    simple (Iterative) method
    public static int sodIte(int n){
        int sum=0;
        while(n>0){
            sum+=n%10;
            n/=10;
        }
        return sum;
    }
}
