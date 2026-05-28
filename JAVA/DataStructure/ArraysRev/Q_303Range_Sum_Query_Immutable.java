package DataStructure.ArraysRev;

public class Q_303Range_Sum_Query_Immutable {
    private int[] prefix;

    public static void main(String[] args) {
        int[] arr = {-2, 0, 3, -5, 2, -1};
        Q_303Range_Sum_Query_Immutable obj = new Q_303Range_Sum_Query_Immutable(arr);
        System.out.println(obj.sumRange(2,5));

    }

    public Q_303Range_Sum_Query_Immutable(int[] nums) {
        int n = nums.length;
        prefix = new int[n];
        prefix[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    public int sumRange(int l, int r) {
        if (l == 0) return prefix[r];
        return prefix[r] - prefix[l - 1];

    }

}
