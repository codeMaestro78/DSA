package Codeforces;
import java.util.Scanner;
public class Q200B_Drinks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double[] n2 = new double[n];
        double ans = 0;
        for (int i = 0; i < n2.length; i++) {
            n2[i] = sc.nextInt();
            ans += (n2[i] / n);
        }


        System.out.println(ans);

    }
}
