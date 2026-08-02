package StriverLecture.ArrayPlaylist;

import StriverLecture.ArrayPlaylist.SetMatrixZeroes.Pair;
import java.util.*;

//  Set matrix zeroes

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };

        betterBruteForce(matrix);
        printMatrix(matrix);
    }

    // brute force solution
    // but here is the catch with the brute force is that , our code only works when
    // matrix contains 0 and 1 or only non-negative numbers.
    //  but if the matrix contains -1, the our code will think that -1.
    // was replaced by you and will convert it to 0 in the end. which is wrong.
    //  SO we need a better brute force solution.

    public static void bruteForce(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0) {

                    for (int k = 0; k < n; k++) {
                        if (nums[i][k] != 0) {
                            nums[i][k] = -1;
                        }
                    }

                    for (int k = 0; k < m; k++) {
                        if (nums[j][k] != 0) {
                            nums[j][k] = -1;
                        }
                    }
                }
            }
        }

        // convert -1 to 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (nums[i][j] == -1) {
                    nums[i][j] = 0;
                }
            }
        }
    }


    //  better brute force solution is that , we have to store the
    // positions or use row[] and col[] marker arrays.
    // Time Complexity: O(m * n * (m + n)) in worst case, but we can write it as O(m*n) for finding + O(k * (m+n)) for setting.
// Space Complexity: O(k) where k = number of zeroes.  

    static class Pair {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void betterBruteForce(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;

        List<Pair> zeroes = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0) {
                    zeroes.add(new Pair(i, j));
                }
            }
        }

        //  for each zero position , set its row and col to 0.
        for (Pair p : zeroes) {
            int c = p.col;
            int r = p.row;

            for (int j = 0; j < n; j++) {
                nums[r][j] = 0;
            }

            for (int i = 0; i < m; i++) {
                nums[i][c] = 0;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }







}
