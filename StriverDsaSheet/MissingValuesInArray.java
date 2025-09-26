package StriverDsaSheet;

public class MissingValuesInArray {
    public static void main(String[] args) {
        int[] arr = {0, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(missingValuesUsingXOR(arr));

    }

    static int missingValues(int[] arr) {
        int n = arr.length;
        int expectedSum = n * (n + 1) / 2;  //total sum from 0 to n
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    static int missingValuesUsingXOR(int[] arr) {
        int n = arr.length;
        int x1 = 0, x2 = 0;
        for (int i = 0; i < n; i++) {
            x1 ^= arr[i];
            x2 ^= i;
        }
        x2 ^= n;
        return x1 ^ x2;
    }
}
