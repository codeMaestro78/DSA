package Leetcode_Solve;

public class Q812_LargestTriangleArea {
    public static void main(String[] args) {

    }

    public double largestTriangleArea(int[][] points) {
        double area = 0.0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double area1 = triangleArea(points[i], points[j], points[k]);
                    area = Math.max(area, area1);
                }
            }
        }
        return area;

    }

    private double triangleArea(int[] p1, int[] p2, int[] p3) {
        return 0.5 * Math.abs(p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) +
                p3[0] * (p1[1] - p2[1]));
    }
}
