package DataStructure.recursion;

public class PowerRecursive {
    public static void main(String []args){
        System.out.println(power(2, 10));
        System.out.println(powerIte(2, 10));
    }
//    recursive method
    public static int power(int a ,int b){
        if(b==0) return 1;
        int half=power(a,b/2);
        if(b%2==0){
            return half*half;
        }
        else {
            return a*half*half;
        }
    }

//    simple (iterative) method
    public static int powerIte(int a,int b){
        int result=1;
        for(int i=1;i<=b;i++){
            result*=a;
        }
        return result;
    }
}
