import java.util.*;

/** Educational examples. Contracts and complexity are documented in the guides. */
public final class CoreAlgorithms {
    private CoreAlgorithms() {}

    // ARRAY: Nonempty contiguous maximum sum; empty input is rejected.
    public static long maxSubarray(int[] a) {
        Objects.requireNonNull(a, "array");
        if (a.length == 0) throw new IllegalArgumentException("nonempty array required");
        long ending = a[0], best = ending;
        for (int i = 1; i < a.length; i++) {
            ending = Math.max((long) a[i], ending + a[i]);
            best = Math.max(best, ending);
        }
        return best;
    }

    // HASH: Number of nonempty contiguous subarrays with the specified sum.
    // Target is int; prefixes/counts use long to avoid int overflow.
    public static long countSum(int[] a, int target) {
        Objects.requireNonNull(a, "array");
        Map<Long, Long> seen = new HashMap<>();
        seen.put(0L, 1L);
        long sum = 0, result = 0;
        for (int value : a) {
            sum += value;
            result += seen.getOrDefault(sum - target, 0L);
            seen.merge(sum, 1L, Long::sum);
        }
        return result;
    }

    // TWO_POINTER: Nondecreasing input required; returns indices or {-1, -1}.
    public static int[] sortedPair(int[] a, int target) {
        Objects.requireNonNull(a, "array");
        int left = 0, right = a.length - 1;
        while (left < right) {
            long sum = (long) a[left] + a[right];
            if (sum == target) return new int[]{left, right};
            if (sum < target) left++;
            else right--;
        }
        return new int[]{-1, -1};
    }

    // WINDOW: Distinct UTF-16 code units, not Unicode code points/graphemes.
    public static int longestDistinct(String text) {
        Objects.requireNonNull(text, "text");
        Map<Character, Integer> last = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < text.length(); right++) {
            char c = text.charAt(right);
            Integer previous = last.put(c, right);
            if (previous != null) left = Math.max(left, previous + 1);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // BINARY_SEARCH: First index with value >= target, or a.length.
    public static int lowerBound(int[] a, int target) {
        Objects.requireNonNull(a, "array");
        int low = 0, high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public static final class Node {
        public final int value;
        public Node next;
        public Node(int value) { this.value = value; }
    }

    // LIST: Acyclic list, exclusively mutable by caller. Null is empty.
    public static void reorder(Node head) {
        if (head == null || head.next == null) return;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node current = slow.next;
        slow.next = null; // Separate the halves before reversing/merging.
        Node previous = null;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        Node first = head, second = previous;
        while (second != null) {
            Node nextFirst = first.next, nextSecond = second.next;
            first.next = second;
            second.next = nextFirst;
            first = nextFirst;
            second = nextSecond;
        }
    }

    // GRAPH: Directed unweighted graph, vertices 0..n-1, all rows nonnull.
    // Input representation validation is included: O(V+E) even if unreachable.
    public static int[] distances(int[][] adjacency, int source) {
        Objects.requireNonNull(adjacency, "adjacency");
        int n = adjacency.length;
        if (source < 0 || source >= n) throw new IllegalArgumentException("source");
        for (int[] row : adjacency) {
            Objects.requireNonNull(row, "adjacency row");
            for (int v : row) {
                if (v < 0 || v >= n) throw new IllegalArgumentException("vertex");
            }
        }
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        distance[source] = 0;
        queue.addLast(source);
        while (!queue.isEmpty()) {
            int u = queue.removeFirst();
            for (int v : adjacency[u]) {
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.addLast(v); // Mark on enqueue, not on dequeue.
                }
            }
        }
        return distance;
    }

    // DP: Unlimited positive coin denominations; amount >= 0.
    public static int minCoins(int[] coins, int amount) {
        Objects.requireNonNull(coins, "coins");
        if (amount < 0 || amount == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("amount must be in 0..MAX_VALUE-1");
        }
        for (int coin : coins) {
            if (coin <= 0) throw new IllegalArgumentException("positive coins required");
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int value = 1; value <= amount; value++) {
            for (int coin : coins) {
                if (coin <= value && dp[value - coin] <= amount) {
                    dp[value] = Math.min(dp[value], dp[value - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
