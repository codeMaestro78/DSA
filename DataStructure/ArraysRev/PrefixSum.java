
package DataStructure.ArraysRev;
public class PrefixSum {
    private final int[] prefix;

    public PrefixSum(int[] arr) {
        int n = arr.length;
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }
    }

    public int rangeSum(int left, int right) {
        if (left < 0 || right >= prefix.length || left > right) {
            throw new IllegalArgumentException("Invalid range");
        }
        return prefix[right + 1] - prefix[left];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        PrefixSum ps = new PrefixSum(arr);
        System.out.println(ps.rangeSum(1, 3));
        System.out.println(ps.rangeSum(1, 4));
        System.out.println(ps.rangeSum(2, 2));
    }
}

// To answer range sum queries efficiently.
//Time & Space Complexity:
//Preprocessing: O(n)
//Each Query: O(1)
//Space: O(n) for prefix array


// common use case
//Range sum queries (static array)
//Subarray sum equals K (with HashMap + prefix sum)
//Finding equilibrium index
//Sliding window optimizations
//2D prefix sums for matrix region sums


