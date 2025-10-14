package DataStructure.recursion;

public class LongestBalancedSubstring_I {
    public static void main(String[] args) {

    }

    public int longestBalancedSubString(String s) {
        int n = s.length();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (freq[idx] == 0) distinct++;
                freq[idx]++;
                if (isBalanced(freq, distinct)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    private static boolean isBalanced(int[] freq, int distinct) {
        int target = 0, count = 0;
        for (int f : freq) {
            if (f > 0) {
                if (target == 0) {
                    target = f;
                } else if (f != target) return false;
                count++;
            }
        }
        return count == distinct;
    }
}
