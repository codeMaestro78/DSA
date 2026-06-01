# Phase 3 — Advanced (Weeks 13–20)

> **Focus:** Multi-dimensional DP, backtracking, tries, and design questions.
> These topics separate candidates at Google/Meta senior roles and are increasingly appearing at top startups.

---

## Week 13 — Grid & Matrix DP
**Priority:** ⭐⭐⭐ Medium-High | **Companies:** Google, Meta

### Concepts to Learn
- [ ] 2D DP grid traversal (top-left to bottom-right)
- [ ] Path counting / minimum cost path
- [ ] Unique paths with obstacles
- [ ] Matrix chain / range DP
- [ ] Edit distance pattern on 2D grid

### LeetCode Problems
- [ ] [62] Unique Paths — Medium ⭐
- [ ] [63] Unique Paths II — Medium ⭐
- [ ] [64] Minimum Path Sum — Medium ⭐
- [ ] [120] Triangle — Medium *(bottom-up)*
- [ ] [221] Maximal Square — Medium ⭐
- [ ] [174] Dungeon Game — Hard *(reverse DP)*
- [ ] [329] Longest Increasing Path in Matrix — Hard ⭐ *(DFS + memo)*
- [ ] [1143] Longest Common Subsequence — Medium ⭐ *(2D DP)*
- [ ] [72] Edit Distance — Hard ⭐
- [ ] [115] Distinct Subsequences — Hard

### Key Patterns
```
2D DP initialization:
  dp[0][j] = prefix_cost_top_row
  dp[i][0] = prefix_cost_left_col
  dp[i][j] = cost[i][j] + min(dp[i-1][j], dp[i][j-1])

Maximal Square:
  dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
  (only if matrix[i][j] == '1')

LCS:
  if s1[i] == s2[j]: dp[i][j] = dp[i-1][j-1] + 1
  else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

---

## Week 14 — String DP
**Priority:** ⭐⭐⭐ Medium | **Companies:** Google, Meta

### Concepts to Learn
- [ ] LCS (Longest Common Subsequence)
- [ ] LPS (Longest Palindromic Subsequence)
- [ ] Edit distance
- [ ] Regex / wildcard matching
- [ ] Palindrome partitioning
- [ ] Interleaving string

### LeetCode Problems
- [ ] [5] Longest Palindromic Substring — Medium ⭐ *(expand around center)*
- [ ] [516] Longest Palindromic Subsequence — Medium ⭐
- [ ] [647] Palindromic Substrings — Medium *(count all)*
- [ ] [10] Regular Expression Matching — Hard ⭐
- [ ] [44] Wildcard Matching — Hard ⭐
- [ ] [97] Interleaving String — Hard
- [ ] [132] Palindrome Partitioning II — Hard *(min cuts)*
- [ ] [131] Palindrome Partitioning — Medium *(backtracking + DP)*
- [ ] [583] Delete Operation for Two Strings — Medium *(LCS variant)*
- [ ] [1312] Minimum Insertion Steps to Make String Palindrome — Hard

### Key Patterns
```
LPS (Longest Palindromic Subsequence):
  # Transform to LCS(s, reversed(s))
  # Or: dp[i][j] = dp[i+1][j-1]+2 if s[i]==s[j], else max(dp[i+1][j], dp[i][j-1])

Palindrome check (expand around center):
  for center in range(2*n-1):
      l = center // 2
      r = l + center % 2
      while l >= 0 and r < n and s[l] == s[r]:
          # palindrome found
          l--; r++
```

---

## Week 15 — Backtracking & Recursion
**Priority:** ⭐⭐⭐⭐ High | **Companies:** Google, Meta

### Concepts to Learn
- [ ] Backtracking template (choose, explore, unchoose)
- [ ] Subsets (power set)
- [ ] Permutations (with/without duplicates)
- [ ] Combinations (nCr)
- [ ] N-Queens, Sudoku solver
- [ ] Word search in grid
- [ ] Pruning strategies

### LeetCode Problems
- [ ] [78] Subsets — Medium ⭐
- [ ] [90] Subsets II — Medium *(with duplicates)*
- [ ] [46] Permutations — Medium ⭐
- [ ] [47] Permutations II — Medium *(with duplicates)*
- [ ] [77] Combinations — Medium
- [ ] [39] Combination Sum — Medium ⭐
- [ ] [40] Combination Sum II — Medium *(with duplicates)*
- [ ] [79] Word Search — Medium ⭐
- [ ] [51] N-Queens — Hard ⭐
- [ ] [37] Sudoku Solver — Hard

### Key Patterns
```
Backtracking Template:
  def backtrack(start, path):
      if base_case:
          result.append(path[:])  # copy!
          return
      for i in range(start, n):
          if should_skip(i): continue  # pruning
          path.append(candidates[i])
          backtrack(i+1, path)          # i+1 for combinations, i for repeats
          path.pop()                     # unchoose

Duplicate handling: sort input first, skip if i > start and nums[i] == nums[i-1]
```

---

## Week 16 — Tries & String Trees
**Priority:** ⭐⭐⭐ Medium | **Companies:** Google, Microsoft

### Concepts to Learn
- [ ] Trie node structure and insert/search/startsWith
- [ ] Word dictionary with wildcard search
- [ ] Auto-complete systems
- [ ] Trie + DFS for word search
- [ ] Suffix arrays (concept)

### LeetCode Problems
- [ ] [208] Implement Trie — Medium ⭐
- [ ] [211] Design Add and Search Words — Medium ⭐
- [ ] [212] Word Search II — Hard ⭐ *(Trie + DFS)*
- [ ] [421] Maximum XOR of Two Numbers in Array — Medium *(binary trie)*
- [ ] [648] Replace Words — Medium
- [ ] [1268] Search Suggestions System — Medium *(auto-complete)*
- [ ] [472] Concatenated Words — Hard *(Trie + DP)*
- [ ] [745] Prefix and Suffix Search — Hard
- [ ] [820] Short Encoding of Words — Medium
- [ ] [336] Palindrome Pairs — Hard

### Key Patterns
```
Trie Node:
  class TrieNode:
      def __init__(self):
          self.children = {}
          self.is_end = False

Insert: for ch in word: node = node.children.setdefault(ch, TrieNode())
Search: traverse, check is_end
StartsWith: traverse, return True if path exists

Binary Trie for XOR:
  Insert bits from MSB to LSB
  Greedily pick opposite bit for max XOR
```

---

## Week 17 — Math & Bit Manipulation
**Priority:** ⭐⭐⭐ Medium | **Companies:** All

### Concepts to Learn
- [ ] Bitwise: AND, OR, XOR, NOT, shifts
- [ ] XOR properties: a^a=0, a^0=a
- [ ] Check/set/clear/toggle bit
- [ ] Brian Kernighan's algorithm (count set bits)
- [ ] Power of 2 check: n & (n-1) == 0
- [ ] Sieve of Eratosthenes
- [ ] GCD/LCM, modular arithmetic
- [ ] Fast power (binary exponentiation)

### LeetCode Problems
- [ ] [191] Number of 1 Bits — Easy ⭐
- [ ] [338] Counting Bits — Easy ⭐
- [ ] [190] Reverse Bits — Easy
- [ ] [268] Missing Number — Easy ⭐ *(XOR)*
- [ ] [136] Single Number — Easy ⭐ *(XOR)*
- [ ] [137] Single Number II — Medium *(bit counting)*
- [ ] [260] Single Number III — Medium *(XOR split)*
- [ ] [371] Sum of Two Integers — Medium *(bit addition)*
- [ ] [50] Pow(x, n) — Medium *(fast power)*
- [ ] [204] Count Primes — Medium *(sieve)*

### Key Patterns
```
XOR tricks:
  find missing:  xor all indices 1..n XOR all array elements
  single number: reduce all elements with XOR, duplicates cancel

Bit operations:
  check bit k:   (n >> k) & 1
  set bit k:     n | (1 << k)
  clear bit k:   n & ~(1 << k)
  count bits:    while n: count += n&1; n >>= 1
  Brian Kernighan: while n: n &= (n-1); count++

Fast power:
  result = 1
  while exp > 0:
      if exp & 1: result *= base
      base *= base; exp >>= 1
```

---

## Week 18 — Prefix Sum & Difference Arrays
**Priority:** ⭐⭐⭐ Medium | **Companies:** Google, Meta

### Concepts to Learn
- [ ] 1D prefix sum construction and range query
- [ ] 2D prefix sum (matrix region sum)
- [ ] Difference array for range update in O(1)
- [ ] Prefix sum + HashMap for subarray problems
- [ ] Running XOR prefix

### LeetCode Problems
- [ ] [303] Range Sum Query — Easy ⭐
- [ ] [304] Range Sum Query 2D — Medium ⭐
- [ ] [560] Subarray Sum Equals K — Medium ⭐
- [ ] [525] Contiguous Array — Medium ⭐ *(prefix sum + hashmap)*
- [ ] [528] Random Pick with Weight — Medium *(prefix + binary search)*
- [ ] [1109] Corporate Flight Bookings — Medium *(difference array)*
- [ ] [1094] Car Pooling — Medium *(difference array)*
- [ ] [1480] Running Sum of 1d Array — Easy
- [ ] [974] Subarray Sums Divisible by K — Medium ⭐
- [ ] [862] Shortest Subarray with Sum at Least K — Hard *(deque)*

### Key Patterns
```
Prefix sum:
  prefix[0] = 0
  prefix[i] = prefix[i-1] + arr[i-1]
  range sum [l, r] = prefix[r+1] - prefix[l]

2D prefix:
  P[i][j] = matrix[i-1][j-1] + P[i-1][j] + P[i][j-1] - P[i-1][j-1]

Difference array (range update +val from l to r):
  diff[l] += val; diff[r+1] -= val
  restore with prefix sum
```

---

## Week 19 — System Design (LRU / LFU Cache)
**Priority:** ⭐⭐⭐ Medium | **Companies:** Meta, Google, Twitter/X

### Concepts to Learn
- [ ] LRU Cache — HashMap + Doubly Linked List
- [ ] LFU Cache — HashMap + min-frequency tracking
- [ ] Ordered data structures (OrderedDict in Python)
- [ ] Design patterns: cache eviction policies
- [ ] Consistent hashing (concept)

### LeetCode Problems
- [ ] [146] LRU Cache — Medium ⭐
- [ ] [460] LFU Cache — Hard ⭐
- [ ] [355] Design Twitter — Medium *(heap + design)*
- [ ] [706] Design HashMap — Easy
- [ ] [705] Design HashSet — Easy
- [ ] [381] Insert Delete GetRandom O(1) — Hard ⭐
- [ ] [380] Insert Delete GetRandom — Medium ⭐
- [ ] [1603] Design Parking System — Easy
- [ ] [729] My Calendar I — Medium
- [ ] [588] Design In-Memory File System — Hard *(Trie-based)*

### Key Patterns
```
LRU Cache:
  HashMap<key, Node> + Doubly Linked List
  get: move node to head (most recent)
  put: add to head; if over capacity, remove tail
  Dummy head and tail nodes simplify edge cases

LFU Cache:
  freq_map: key -> frequency
  group_map: frequency -> OrderedDict of {key: val}
  min_freq tracker
  on get/put: update frequency, move between groups
```

---

## Week 20 — Bitmask DP / Advanced DP
**Priority:** ⭐⭐ Low-Medium | **Companies:** Google (rare)

### Concepts to Learn
- [ ] Bitmask to represent subsets
- [ ] Traveling Salesman (TSP) with bitmask DP
- [ ] State compression DP
- [ ] DP on subsets: iterate over subsets of a bitmask
- [ ] Broken profile DP (competitive programming)

### LeetCode Problems
- [ ] [464] Can I Win — Medium ⭐ *(bitmask + memo)*
- [ ] [526] Beautiful Arrangement — Medium ⭐ *(bitmask)*
- [ ] [847] Shortest Path Visiting All Nodes — Hard ⭐ *(BFS + bitmask)*
- [ ] [1986] Minimum Number of Work Sessions — Medium *(bitmask DP)*
- [ ] [2002] Maximum Product of the Length — Hard *(bitmask)*
- [ ] [1125] Smallest Sufficient Team — Hard *(bitmask DP)*
- [ ] [943] Find the Shortest Superstring — Hard *(bitmask DP — TSP)*
- [ ] [691] Stickers to Spell Word — Hard
- [ ] [996] Number of Squareful Arrays — Hard *(permutation + bitmask)*
- [ ] [1994] The Number of Good Subsets — Hard

### Key Patterns
```
Bitmask DP (TSP-style):
  dp[mask][i] = min cost to visit all nodes in mask, ending at node i

  for mask in range(1 << n):
      for last in range(n):
          if not (mask >> last) & 1: continue
          for next_node in range(n):
              if (mask >> next_node) & 1: continue
              new_mask = mask | (1 << next_node)
              dp[new_mask][next_node] = min(dp[new_mask][next_node],
                                            dp[mask][last] + cost[last][next_node])

Iterate subsets of a mask:
  sub = mask
  while sub > 0:
      # process sub
      sub = (sub - 1) & mask
```

---

## Phase 3 Checkpoint ✅

Before moving to Phase 4, verify:
- [ ] 2D DP table transitions are clean (no off-by-one errors)
- [ ] Backtracking template with proper pruning is fast to write
- [ ] Trie insert/search/startsWith from memory
- [ ] LRU cache implementation (HashMap + DLL) within 15 minutes
- [ ] Bit manipulation tricks (XOR cancel, power of 2, fast power)
- [ ] Prefix sum 1D and 2D queries are instant
