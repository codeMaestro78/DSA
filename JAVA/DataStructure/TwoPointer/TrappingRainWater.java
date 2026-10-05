package DataStructure.TwoPointer;

import java.util.Scanner;

public class TrappingRainWater {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input format:
        // n
        // h[0] h[1] ... h[n - 1]
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }

        System.out.println(bruteForce(heights));
        System.out.println("Optimized DP :- " + optimizedSol(heights));
        System.out.println("Optimal Two pointer :- " + optimizedSol(heights));

    }

    public static int bruteForce(int[] h) {
        int n = h.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int lmax = 0, rmax = 0;

            for (int j = 0; j <= i; j++) {
                lmax = Math.max(lmax, h[j]);
            }
            for (int j = i; j < n; j++) {
                rmax = Math.max(rmax, h[j]);
            }

            int w = Math.min(lmax, rmax) - h[i];
            if (w > 0) {
                ans += w;
            }
        }
        return ans;
    }

    // brute force
    //  Time complexity = O(n^2)
    // space  complexiy O(1)


    //  optimizes solution using the dp
    public static int optimizedSol(int[] h) {
        int n = h.length;
        if (n == 0)
            return 0;

        int[] left = new int[n], right = new int[n];
        left[0] = h[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], h[i]);
        }

        right[n - 1] = h[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], h[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int w = Math.min(left[i], right[i]) - h[i];
            if (w > 0) {
                ans += w;

            }
        }
        return ans;

    }

    // optimized dp takes O(n) time and O(n) space complexity

    public static int optimalSol(int[] h) {
        int left = 0, right = h.length - 1;
        int lmax = 0, rmax = 0, ans = 0;

        while (left <= right) {
            if (h[left] <= h[right]) {
                if (h[left] >= lmax) {
                    lmax = h[left];
                } else {
                    ans += lmax - h[left];
                }
                left++;
            }

            else {
                if (h[right] >= rmax) {
                    rmax = h[right];
                } else {
                    ans += rmax - h[right];
                }
                right--;
            }
        }
        return ans;
    }

}
