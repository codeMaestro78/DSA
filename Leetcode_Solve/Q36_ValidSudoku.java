package Leetcode_Solve;

import java.util.HashSet;

public class Q36_ValidSudoku {
    public static void main(String[] args) {

    }

    static boolean isValidSudoku(char[][] board) {
//        store the enties we have seen already
        HashSet<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];

                if (num != '.') {
                    String rK = num + " in row " + i;
                    String cK = num + "  in column " + j;
                    String bK = num + "  in block " + (i / 3) + "-" + j / 3;

                    if (!seen.add(rK) || !seen.add(cK) || !seen.add(bK)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean optimalSol(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') { // ✅ Skip empty cells
                    int num = board[i][j] - '1'; // '1' -> 0, '9' -> 8
                    int boxIndex = (i / 3) * 3 + (j / 3); // map to box 0..8

                    // If already seen in row, column, or box → invalid
                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        return false;
                    }

                    // Mark as seen
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }
        return true;
    }
}
