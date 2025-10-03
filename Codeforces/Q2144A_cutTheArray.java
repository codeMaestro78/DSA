package Codeforces;

import java.util.Scanner;

public class Q2144A_cutTheArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int[] prefixSum = new int[n + 1];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                prefixSum[i + 1] = prefixSum[i] + arr[i];
            }

            boolean found = false;

            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int sum1 = prefixSum[i] % 3;
                    int sum2 = (prefixSum[j] - prefixSum[i]) % 3;
                    int sum3 = (prefixSum[n] - prefixSum[j]) % 3;

                    if (sum1 < 0)
                        sum1 += 3;
                    if (sum2 < 0)
                        sum2 += 3;
                    if (sum3 < 0)
                        sum3 += 3;

                    if ((sum1 == sum2 && sum2 == sum3) ||
                            (sum1 != sum2 && sum2 != sum3 && sum1 != sum3)) {
                        System.out.println(i + " " + j);
                        found = true;
                        break;
                    }
                }

                if (found)
                    break;
            }

            if (!found) {
                System.out.println("0 0");
            }
        }

        sc.close();
    }
}
