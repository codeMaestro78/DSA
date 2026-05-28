package Codeforces;

import java.util.Scanner;

public class Q996A_hitTheLottery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int[] bills = {100,20,10,5,1};

        long count = 0;
        for (int num : bills) {
            count += n / num;
            n %= num;
        }
        System.out.println(count);
    }
}


//To minimize the number of bills WE should always use the largest possible denomination first.
//If you started with small bills (like 1s), WE’d end up with way more bills  which is the opposite of what we want.