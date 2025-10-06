package Leetcode_Solve;

import java.util.PriorityQueue;

public class Q778_SwiminRisingWater {
    public static void main(String[] args) {

    }

    public static int swimWater(int[][] grid) {
        int n = grid.length;

        PriorityQueue<int[]> pd = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        boolean[][] visited = new boolean[n][n];
        pd.offer(new int[]{grid[0][0], 0, 0});
        int result = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!pd.isEmpty()) {
            int[] curr = pd.poll();
            int elevation = curr[0], r = curr[1], c = curr[2];

            if (visited[r][c]) continue;
            ;
            visited[r][c] = true;

            result = Math.max(result, elevation);
            if(r==n-1 && c==n-1) return result;

            for(int []dir:directions){
                int nr=r+dir[0];
                int nc=c+dir[1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]){
                    pd.offer(new int[]{grid[nr][nc],nr,nc});
                }
            }
        }
        return  -1;
    }
}
