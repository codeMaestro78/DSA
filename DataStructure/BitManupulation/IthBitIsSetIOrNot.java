package DataStructure.BitManupulation;

public class IthBitIsSetIOrNot {
    public static void main(String[] args) {
        int n = 15, i = 2;

        if((n & (1<<i))!=0){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }

}

// Iteration:
//     13 -- > (1 1 0 1)(bin)     1 << (i)
//        0 0 0 0.......  1 1 0 1
//                          1 0 0
// (n & (1<<i)!= 0 true else false


