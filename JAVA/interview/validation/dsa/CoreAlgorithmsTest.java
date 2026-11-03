import java.util.*;

/** Deterministic edge cases plus fixed-seed differential checks; no libraries. */
public final class CoreAlgorithmsTest {
    private static int checks;
    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError(message);
    }
    private static void expect(Class<? extends Throwable> type, Runnable action) {
        checks++;
        try { action.run(); }
        catch (Throwable failure) {
            if (type.isInstance(failure)) return;
            throw new AssertionError("wrong exception", failure);
        }
        throw new AssertionError("expected " + type.getSimpleName());
    }
    private static long bruteMax(int[] a) {
        long best = Long.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            long sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j]; best = Math.max(best, sum);
            }
        }
        return best;
    }
    private static long bruteCount(int[] a, int target) {
        long count = 0;
        for (int i = 0; i < a.length; i++) {
            long sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j]; if (sum == target) count++;
            }
        }
        return count;
    }
    private static boolean pairExists(int[] a, int target) {
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                if ((long) a[i] + a[j] == target) return true;
        return false;
    }
    private static int bruteDistinct(String s) {
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < s.length() && seen.add(s.charAt(j)); j++)
                best = Math.max(best, j - i + 1);
        }
        return best;
    }
    private static void testList(int n) {
        CoreAlgorithms.Node[] nodes = new CoreAlgorithms.Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new CoreAlgorithms.Node(i);
        for (int i = 0; i + 1 < n; i++) nodes[i].next = nodes[i + 1];
        CoreAlgorithms.Node head = n == 0 ? null : nodes[0];
        CoreAlgorithms.reorder(head);
        Set<CoreAlgorithms.Node> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        CoreAlgorithms.Node cursor = head;
        for (int index = 0; index < n; index++) {
            int expected = index % 2 == 0 ? index / 2 : n - 1 - index / 2;
            check(cursor == nodes[expected], "reorder identity/order n=" + n);
            check(visited.add(cursor), "cycle");
            cursor = cursor.next;
        }
        check(cursor == null, "reorder terminates");
    }
    private static int[] bellmanFordUnit(int[][] graph, int source) {
        int[] d = new int[graph.length]; Arrays.fill(d, 1_000_000); d[source] = 0;
        for (int pass = 1; pass < graph.length; pass++) {
            for (int u = 0; u < graph.length; u++)
                for (int v : graph[u]) d[v] = Math.min(d[v], d[u] + 1);
        }
        for (int i = 0; i < d.length; i++) if (d[i] == 1_000_000) d[i] = -1;
        return d;
    }
    private static int bruteCoins(int[] coins, int remaining) {
        if (remaining == 0) return 0;
        int best = Integer.MAX_VALUE;
        for (int coin : coins) if (coin <= remaining) {
            int rest = bruteCoins(coins, remaining - coin);
            if (rest >= 0) best = Math.min(best, rest + 1);
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
    public static void main(String[] args) {
        expect(NullPointerException.class, () -> CoreAlgorithms.maxSubarray(null));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.maxSubarray(new int[0]));
        expect(NullPointerException.class, () -> CoreAlgorithms.countSum(null, 0));
        expect(NullPointerException.class, () -> CoreAlgorithms.sortedPair(null, 0));
        expect(NullPointerException.class, () -> CoreAlgorithms.longestDistinct(null));
        expect(NullPointerException.class, () -> CoreAlgorithms.lowerBound(null, 0));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.distances(new int[0][], 0));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.distances(new int[][]{{1}}, 0));
        expect(NullPointerException.class, () -> CoreAlgorithms.distances(new int[][]{null}, 0));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.minCoins(new int[]{0}, 2));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.minCoins(new int[]{-1}, 2));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.minCoins(new int[]{1}, -1));
        expect(IllegalArgumentException.class, () -> CoreAlgorithms.minCoins(new int[]{1}, Integer.MAX_VALUE));
        check(CoreAlgorithms.maxSubarray(new int[]{-8, -3, -6}) == -3, "all negative");
        check(CoreAlgorithms.maxSubarray(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}) == 4294967294L, "long sum");
        check(CoreAlgorithms.countSum(new int[]{0, 0, 0}, 0) == 6, "duplicate prefixes");
        check(CoreAlgorithms.countSum(new int[]{Integer.MAX_VALUE, 1, -1}, Integer.MAX_VALUE) == 2, "prefix long");
        check(!pairExists(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2), "oracle overflow");
        check(CoreAlgorithms.sortedPair(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2)[0] == -1, "pair overflow");
        check(CoreAlgorithms.longestDistinct("abba") == 2, "left never goes backwards");
        check(CoreAlgorithms.longestDistinct("a\uD83D\uDE00b") == 4, "UTF16 contract");
        check(CoreAlgorithms.lowerBound(new int[0], 0) == 0, "empty bound");
        check(CoreAlgorithms.lowerBound(new int[]{1,2,2,2,3},2)==1,"leftmost duplicate");
        check(CoreAlgorithms.minCoins(new int[0],0)==0,"zero coins/amount");
        check(CoreAlgorithms.minCoins(new int[0],2)==-1,"unreachable");
        for(int n=0;n<=60;n++) testList(n);
        Random random = new Random(20261102L);
        for (int trial=0; trial<2000; trial++) {
            int[] a = new int[random.nextInt(11)];
            for(int i=0;i<a.length;i++) a[i]=random.nextInt(15)-7;
            int target=random.nextInt(21)-10;
            if(a.length>0) check(CoreAlgorithms.maxSubarray(a)==bruteMax(a),"max differential");
            check(CoreAlgorithms.countSum(a,target)==bruteCount(a,target),"count differential");
            int[] sorted=a.clone();Arrays.sort(sorted);
            int[] pair=CoreAlgorithms.sortedPair(sorted,target);
            boolean found=pair[0]!=-1;
            check(found==pairExists(sorted,target),"pair existence");
            if(found) check(pair[0]<pair[1] && pair[0]>=0 && pair[1]<sorted.length && (long)sorted[pair[0]]+sorted[pair[1]]==target,"pair validity");
            int expected=0;while(expected<sorted.length&&sorted[expected]<target)expected++;
            check(CoreAlgorithms.lowerBound(sorted,target)==expected,"lower bound differential");
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<a.length;i++)sb.append((char)('a'+random.nextInt(5)));
            check(CoreAlgorithms.longestDistinct(sb.toString())==bruteDistinct(sb.toString()),"window differential");
        }
        for(int trial=0;trial<400;trial++) {
            int n=1+random.nextInt(9);int[][] graph=new int[n][];
            for(int u=0;u<n;u++) {
                List<Integer> row=new ArrayList<>();
                for(int v=0;v<n;v++)if(random.nextInt(4)==0)row.add(v);
                graph[u]=row.stream().mapToInt(Integer::intValue).toArray();
            }
            int source=random.nextInt(n);
            check(Arrays.equals(CoreAlgorithms.distances(graph,source),bellmanFordUnit(graph,source)),"BFS differential");
        }
        for(int trial=0;trial<400;trial++) {
            int[] coins=new int[random.nextInt(4)];
            for(int i=0;i<coins.length;i++)coins[i]=1+random.nextInt(6);
            int amount=random.nextInt(11);
            check(CoreAlgorithms.minCoins(coins,amount)==bruteCoins(coins,amount),"coin differential");
        }
        System.out.println("PASS: " + checks + " checks; seed=20261102; 2000 array/string, 400 graph, 400 coin trials; list sizes 0..60");
    }
}
