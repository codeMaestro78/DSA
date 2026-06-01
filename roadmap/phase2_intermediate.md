# Phase 2 — Intermediate (Weeks 7–12)

> **Focus:** Tree structures, graph traversals, and DP foundations — the core of Google/Meta/Amazon hard rounds.

---

## Week 7 — Greedy & Interval Scheduling
**Priority:** ⭐⭐⭐⭐ High | **Companies:** Amazon, Google

### Concepts to Learn
- [ ] Greedy: make locally optimal choice at each step
- [ ] Interval merging (sort by start time)
- [ ] Interval scheduling (sort by end time — activity selection)
- [ ] Meeting rooms / minimum resources needed
- [ ] Jump game patterns

### LeetCode Problems
- [ ] [55] Jump Game — Medium ⭐
- [ ] [45] Jump Game II — Medium ⭐
- [ ] [56] Merge Intervals — Medium ⭐
- [ ] [57] Insert Interval — Medium ⭐
- [ ] [252] Meeting Rooms — Easy *(premium — sort by start)*
- [ ] [253] Meeting Rooms II — Medium ⭐ *(min-heap / sweep)*
- [ ] [435] Non-overlapping Intervals — Medium ⭐
- [ ] [763] Partition Labels — Medium
- [ ] [134] Gas Station — Medium
- [ ] [1029] Two City Scheduling — Medium

### Key Patterns
```
Merge Intervals:
  sort by start
  for interval in intervals:
      if result[-1][1] >= interval[0]:  # overlap
          result[-1][1] = max(result[-1][1], interval[1])
      else:
          result.append(interval)

Min rooms (Meeting Rooms II):
  Use min-heap of end times
  If heap[0] <= start: reuse room (heapreplace)
  Else: add new room (heappush)
```

---

## Week 8 — Binary Trees & BST
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Companies:** Google, Meta, Microsoft

### Concepts to Learn
- [ ] Tree traversals: inorder, preorder, postorder (recursive + iterative)
- [ ] Level order traversal (BFS with queue)
- [ ] Height, depth, diameter calculations
- [ ] LCA (Lowest Common Ancestor)
- [ ] BST insertion, deletion, validation
- [ ] Serialization / deserialization
- [ ] Path sum patterns

### LeetCode Problems
- [ ] [104] Maximum Depth of Binary Tree — Easy
- [ ] [226] Invert Binary Tree — Easy
- [ ] [100] Same Tree — Easy
- [ ] [572] Subtree of Another Tree — Medium
- [ ] [102] Binary Tree Level Order Traversal — Medium ⭐
- [ ] [543] Diameter of Binary Tree — Easy ⭐
- [ ] [110] Balanced Binary Tree — Easy
- [ ] [112] Path Sum — Easy
- [ ] [124] Binary Tree Maximum Path Sum — Hard ⭐
- [ ] [235] LCA of BST — Medium ⭐
- [ ] [236] LCA of Binary Tree — Medium ⭐
- [ ] [98] Validate BST — Medium ⭐
- [ ] [105] Construct Tree from Preorder+Inorder — Medium ⭐
- [ ] [297] Serialize and Deserialize Binary Tree — Hard ⭐
- [ ] [199] Binary Tree Right Side View — Medium

### Key Patterns
```
DFS height/path:
  def dfs(node):
      if not node: return 0
      left = dfs(node.left)
      right = dfs(node.right)
      # update global answer here
      return 1 + max(left, right)

Level Order BFS:
  queue = deque([root])
  while queue:
      level_size = len(queue)
      for _ in range(level_size):
          node = queue.popleft()
          if node.left: queue.append(node.left)
          if node.right: queue.append(node.right)

BST validation (pass min/max bounds):
  def valid(node, lo, hi):
      if not node: return True
      if not (lo < node.val < hi): return False
      return valid(node.left, lo, node.val) and valid(node.right, node.val, hi)
```

---

## Week 9 — Graphs (BFS / DFS)
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Companies:** Google, Meta, Microsoft, Uber

### Concepts to Learn
- [ ] Graph representations: adjacency list, matrix
- [ ] DFS iterative + recursive
- [ ] BFS for shortest path (unweighted)
- [ ] Connected components (count islands pattern)
- [ ] Flood fill / grid DFS
- [ ] Bipartite check
- [ ] Cycle detection (directed + undirected)

### LeetCode Problems
- [ ] [200] Number of Islands — Medium ⭐
- [ ] [133] Clone Graph — Medium ⭐
- [ ] [695] Max Area of Island — Medium
- [ ] [417] Pacific Atlantic Water Flow — Medium ⭐
- [ ] [130] Surrounded Regions — Medium
- [ ] [994] Rotting Oranges — Medium ⭐ *(multi-source BFS)*
- [ ] [127] Word Ladder — Hard ⭐ *(BFS)*
- [ ] [207] Course Schedule — Medium ⭐ *(cycle detection)*
- [ ] [323] Number of Connected Components — Medium *(Union-Find or DFS)*
- [ ] [684] Redundant Connection — Medium *(Union-Find)*

### Key Patterns
```
Grid DFS (4-directional):
  DIRS = [(0,1),(0,-1),(1,0),(-1,0)]
  def dfs(r, c):
      if out_of_bounds or visited or blocked: return
      visited[r][c] = True
      for dr, dc in DIRS:
          dfs(r+dr, c+dc)

Multi-source BFS (rotting oranges):
  queue = deque(all_starting_nodes)
  while queue:
      node = queue.popleft()
      for neighbor in neighbors(node):
          if not visited:
              visited.add(neighbor)
              queue.append(neighbor)
```

---

## Week 10 — Graph Algorithms (Topological Sort, Union-Find)
**Priority:** ⭐⭐⭐⭐ High | **Companies:** Google, Meta

### Concepts to Learn
- [ ] Topological sort — Kahn's algorithm (BFS in-degree)
- [ ] Topological sort — DFS post-order
- [ ] Union-Find (Disjoint Set Union) with path compression + rank
- [ ] Dijkstra's algorithm (weighted shortest path)
- [ ] Bellman-Ford (negative weights)
- [ ] Detecting cycle with Union-Find

### LeetCode Problems
- [ ] [207] Course Schedule — Medium ⭐ *(topo sort)*
- [ ] [210] Course Schedule II — Medium ⭐ *(topo sort order)*
- [ ] [269] Alien Dictionary — Hard ⭐ *(topo sort on chars)*
- [ ] [684] Redundant Connection — Medium ⭐ *(Union-Find)*
- [ ] [1319] Number of Operations to Make Network Connected — Medium
- [ ] [743] Network Delay Time — Medium ⭐ *(Dijkstra)*
- [ ] [787] Cheapest Flights K Stops — Medium *(Bellman-Ford)*
- [ ] [778] Swim in Rising Water — Hard *(Dijkstra / binary search)*
- [ ] [1584] Min Cost to Connect All Points — Medium *(MST — Prim/Kruskal)*
- [ ] [332] Reconstruct Itinerary — Hard *(Hierholzer's — Eulerian path)*

### Key Patterns
```
Union-Find:
  parent = list(range(n))
  def find(x):
      if parent[x] != x: parent[x] = find(parent[x])  # path compression
      return parent[x]
  def union(x, y):
      px, py = find(x), find(y)
      if px == py: return False  # cycle!
      parent[px] = py
      return True

Kahn's Topo Sort:
  in_degree = [0] * n
  build adjacency list + count in-degrees
  queue = [nodes with in_degree == 0]
  while queue:
      node = queue.popleft(); result.append(node)
      for neighbor: in_degree[neighbor]--; if 0: enqueue
```

---

## Week 11 — Heaps & Priority Queues
**Priority:** ⭐⭐⭐⭐ High | **Companies:** Amazon, Google, Uber

### Concepts to Learn
- [ ] Min-heap and max-heap operations
- [ ] K largest / K smallest elements
- [ ] Merge K sorted lists/arrays
- [ ] Two-heap pattern (median finder)
- [ ] Task scheduling
- [ ] Lazy deletion in heaps

### LeetCode Problems
- [ ] [215] Kth Largest Element in Array — Medium ⭐
- [ ] [347] Top K Frequent Elements — Medium ⭐
- [ ] [23] Merge K Sorted Lists — Hard ⭐
- [ ] [295] Find Median from Data Stream — Hard ⭐ *(two heaps)*
- [ ] [355] Design Twitter — Medium *(heap + linked list)*
- [ ] [621] Task Scheduler — Medium ⭐
- [ ] [973] K Closest Points to Origin — Medium
- [ ] [1046] Last Stone Weight — Easy
- [ ] [2542] Maximum Subsequence Score — Medium *(sort + heap)*
- [ ] [778] Swim in Rising Water — Hard *(Dijkstra with heap)*

### Key Patterns
```
Python min-heap:
  import heapq
  heap = []
  heapq.heappush(heap, val)
  heapq.heappop(heap)

Max-heap (negate values):
  heapq.heappush(heap, -val)
  max_val = -heapq.heappop(heap)

Two-heap median:
  small (max-heap), large (min-heap)
  Balance: len(small) == len(large) or len(small) == len(large)+1
  median = small[0] or (small[0] + large[0]) / 2
```

---

## Week 12 — Classic DP 1D
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Companies:** All

### Concepts to Learn
- [ ] Memoization vs tabulation
- [ ] Fibonacci / climbing stairs pattern
- [ ] House robber (max non-adjacent sum)
- [ ] Coin change (unbounded knapsack)
- [ ] 0/1 Knapsack
- [ ] Longest Increasing Subsequence (LIS)
- [ ] Decode ways pattern

### LeetCode Problems
- [ ] [70] Climbing Stairs — Easy ⭐
- [ ] [198] House Robber — Medium ⭐
- [ ] [213] House Robber II — Medium ⭐ *(circular)*
- [ ] [322] Coin Change — Medium ⭐
- [ ] [518] Coin Change II — Medium ⭐ *(count ways)*
- [ ] [300] Longest Increasing Subsequence — Medium ⭐
- [ ] [91] Decode Ways — Medium ⭐
- [ ] [139] Word Break — Medium ⭐
- [ ] [416] Partition Equal Subset Sum — Medium ⭐ *(0/1 knapsack)*
- [ ] [152] Maximum Product Subarray — Medium ⭐

### Key Patterns
```
Tabulation template:
  dp = [base_case] * (n+1)
  for i in range(start, n+1):
      dp[i] = f(dp[i-1], dp[i-2], ...)

Coin Change (unbounded):
  dp[0] = 0
  for coin in coins:
      for amount in range(coin, target+1):
          dp[amount] = min(dp[amount], dp[amount-coin] + 1)

LIS:
  dp[i] = max(dp[j]+1 for j < i if nums[j] < nums[i])
  # O(n log n) with patience sorting / binary search
```

---

## Phase 2 Checkpoint ✅

Before moving to Phase 3, verify:
- [ ] Can implement BFS and DFS from scratch within 5 minutes
- [ ] Union-Find with path compression is second nature
- [ ] Can identify DP subproblems and write recurrence relation
- [ ] Tree DFS with return values (path sum, LCA) feels comfortable
- [ ] Heap-based solutions for top-K problems are fast
- [ ] Topological sort both ways (Kahn's + DFS post-order)
