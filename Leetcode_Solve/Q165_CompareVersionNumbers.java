package Leetcode_Solve;

public class Q165_CompareVersionNumbers {
    public static void main(String[] args) {

    }

    static int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        int n1 = version1.length(), n2 = version2.length();

        while (i < n1 && j < n2) {
            int num1 = 0;
            while (i < n1 && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            int num2 = 0;
            while (j < n2 && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
            i++;
            j++;
        }
        return 0;
    }
}
