package Leetcode_Solve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q417_PacificAtlanticWaterFlow {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int r = heights.length, c = heights[0].length;
        boolean[][] pacificVisited = new boolean[r][c];
        boolean[][] atlanticVisited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            dfs(i, 0, heights, pacificVisited, r, c);
            dfs(i, c - 1, heights, atlanticVisited, r, c);
        }
        for (int i = 0; i < c; i++) {
            dfs(0, i, heights, pacificVisited, r, c);
            dfs(r - 1, i, heights, atlanticVisited, r, c);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;

    }

    private static void dfs(int x, int y, int[][] heights, boolean[][] visited, int r, int c) {
        visited[x][y] = true;

        int[][] directions = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValid(newX, newY, r, c) && !visited[newX][newY] && heights[newX][newY] >= heights[x][y]) {
                dfs(newX, newY, heights, visited, r, c);
            }
        }
    }

    private static boolean isValid(int x, int y, int r, int c) {
        return x >= 0 && y >= 0 && x < r && y < c;
    }

}
