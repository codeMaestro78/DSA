package DataStructure.recursion;

public class fibonacci {
    public static void main(String []args){
        System.out.println(fib(10));
        System.out.println(fib2(1));
        System.out.println(fib3(9,0,1));

    }
// using recursive
    public static int fib(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        return fib(n-1)+fib(n-2);
    }

//    using dp
    public static int fib2(int n){
        int []fib= new int[n+1];
        fib[0]=0;
        fib[1]=1;
        for(int i=2;i<=n;i++){
            fib[i]=fib[i-1]+fib[i-2];
        }
        return fib[n];
    }

//    tail recursion
    public static int  fib3(int n,int a ,int b){
        if(n==0) return a;
        if(n==1) return b;

        return fib3(n-1,b,a+b);
    }
}
