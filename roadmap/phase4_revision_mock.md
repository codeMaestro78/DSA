# Phase 4 — Revision & Mock Interviews (Weeks 21–26)

> **Goal:** Solidify patterns, identify and fix weak areas, simulate real interview conditions.
> At this stage — speed, confidence, and communication matter as much as correctness.

---

## Week 21 — Arrays & Strings (Deep Revision)
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Focus:** Google, Meta, Amazon, Microsoft

### Revision Focus
- [ ] Re-solve Week 1 & 2 problems — target < 10 min each Easy, < 20 min each Medium
- [ ] Hard problems: Trapping Rain Water, Sliding Window Maximum, Minimum Window Substring
- [ ] Identify patterns you still hesitate on

### New Hard Problems (Stretch)
- [ ] [41] First Missing Positive — Hard *(index as hash)*
- [ ] [4] Median of Two Sorted Arrays — Hard *(binary search)*
- [ ] [30] Substring with Concatenation of All Words — Hard *(sliding window)*
- [ ] [632] Smallest Range Covering Elements from K Lists — Hard *(heap + sliding window)*
- [ ] [76] Minimum Window Substring — Hard *(timed, no hints)*

### Timed Practice
```
Set a timer for each problem:
  Easy:   10 minutes
  Medium: 20 minutes
  Hard:   35 minutes

If stuck after half the time: write brute force, then optimize.
```

---

## Week 22 — Two Pointers / Sliding Window (Deep Revision)
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Focus:** All companies

### Revision Focus
- [ ] Re-solve Week 2 problems timed
- [ ] Can you identify the pattern within 60 seconds of reading the problem?
- [ ] Template recall: fixed vs variable window, when to shrink

### New Hard Problems (Stretch)
- [ ] [992] Subarrays with K Different Integers — Hard *(at most K trick)*
- [ ] [2009] Minimum Number of Operations to Make Array Continuous — Hard
- [ ] [1675] Minimize Deviation in Array — Hard *(heap + sliding)*
- [ ] [727] Minimum Window Subsequence — Hard *(two pointers)*

### Communication Practice
```
Practice narrating while solving:
1. Restate the problem in your own words
2. State the approach before coding ("I'll use a sliding window because...")
3. Walk through a small example
4. Code and explain simultaneously
5. Mention time/space complexity at the end
```

---

## Week 23 — Binary Trees & BST (Deep Revision)
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Focus:** Google, Meta, Microsoft

### Revision Focus
- [ ] All traversals from memory (inorder iterative is tricky — practice it)
- [ ] LCA, serialize/deserialize, path sum variants
- [ ] BST operations — insert, delete, kth smallest

### New Hard Problems (Stretch)
- [ ] [99] Recover BST — Hard *(inorder + two-pointer)*
- [ ] [145] Binary Tree Postorder Traversal — Medium *(iterative)*
- [ ] [114] Flatten Binary Tree to Linked List — Medium
- [ ] [428] Serialize and Deserialize N-ary Tree — Hard
- [ ] [1485] Clone Binary Tree with Random Pointer — Medium

### BST Mastery Checklist
- [ ] kth smallest in BST: inorder traversal, track count
- [ ] Successor/predecessor in BST
- [ ] Convert sorted array to balanced BST
- [ ] Convert BST to Greater Sum Tree

---

## Week 24 — Graphs + DP Full Revision
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Focus:** All senior-level companies

### Graph Revision
- [ ] Re-solve: Number of Islands, Course Schedule II, Network Delay Time
- [ ] Practice: write Dijkstra from scratch in < 15 min
- [ ] Practice: Union-Find with path compression + rank in < 10 min

### DP Revision
- [ ] Re-solve: Coin Change, LIS, LCS, Edit Distance, Word Break
- [ ] Practice: identify recurrence relation within 2 minutes of reading
- [ ] Hard problems: Burst Balloons, Strange Printer, Scramble String

### New Stretch Problems
- [ ] [1293] Shortest Path in Grid with Obstacles Elimination — Hard *(BFS + state)*
- [ ] [312] Burst Balloons — Hard *(interval DP)*
- [ ] [1473] Paint House III — Hard *(3D DP)*
- [ ] [87] Scramble String — Hard *(memo DP)*
- [ ] [1235] Maximum Profit in Job Scheduling — Hard *(DP + binary search)*

---

## Week 25 — Mock Interviews (Company-Specific)

### Google Mock Focus
Google values: clean code, optimal complexity, edge cases, system thinking

**Priority topics for Google:**
- [ ] Graph algorithms (Dijkstra, Topo sort, Union-Find)
- [ ] Tree problems (LCA, serialize, diameter)
- [ ] DP (LIS, LCS, Word Break, Coin Change)
- [ ] Binary search on answer
- [ ] Clean backtracking (N-Queens, Sudoku)

**Practice problems (untimed first round, timed second round):**
- [ ] [269] Alien Dictionary — Hard
- [ ] [827] Making A Large Island — Hard *(Union-Find + BFS)*
- [ ] [1192] Critical Connections — Hard *(Tarjan's)*
- [ ] [336] Palindrome Pairs — Hard *(Trie)*
- [ ] [68] Text Justification — Hard *(simulation)*

### Meta Mock Focus
Meta values: speed, correctness, breadth, handling follow-ups

**Priority topics for Meta:**
- [ ] Arrays, Two Pointers (very fast solving expected)
- [ ] Graph BFS (social network traversals)
- [ ] Tree traversals + design
- [ ] Backtracking

**Practice problems:**
- [ ] [1570] Dot Product of Two Sparse Vectors — Medium *(system design flavor)*
- [ ] [1091] Shortest Path in Binary Matrix — Medium *(BFS)*
- [ ] [301] Remove Invalid Parentheses — Hard *(BFS + pruning)*
- [ ] [topological] Dependencies in task systems
- [ ] [408] Valid Word Abbreviation — Easy *(Meta screens with this)*

### Amazon Mock Focus
Amazon values: working solution first, scalability, clear communication

**Priority topics for Amazon:**
- [ ] Arrays & Strings (very common in OA)
- [ ] Heaps (top-K, scheduling)
- [ ] Greedy (intervals, task scheduling)
- [ ] Trees

**Practice problems:**
- [ ] [973] K Closest Points to Origin — Medium *(heap)*
- [ ] [621] Task Scheduler — Medium *(greedy + heap)*
- [ ] [253] Meeting Rooms II — Medium *(intervals + heap)*
- [ ] [200] Number of Islands — Medium *(frequent Amazon)*
- [ ] [42] Trapping Rain Water — Hard *(very frequent)*

### Uber / Stripe / Airbnb Mock Focus
**Common themes:** Map/geolocation problems, rate limiting (sliding window), pricing/scheduling

**Practice problems:**
- [ ] [355] Design Twitter — Medium
- [ ] [588] Design In-Memory File System — Hard
- [ ] [381] Insert Delete GetRandom — Hard
- [ ] [1146] Snapshot Array — Medium
- [ ] [253] Meeting Rooms II — Medium

---

## Week 26 — Final Mock + Weak Area Cleanup

### Day 1–2: Identify Your Weak Areas
- [ ] Review all problems marked wrong / took too long
- [ ] Categorize: pattern recognition? implementation? edge cases?

### Day 3–4: Targeted Weak Area Drill
Focus only on the 1–2 topics where you struggled most in Week 25 mocks.

### Day 5–6: Full Interview Simulation
Simulate complete interviews (45–60 min each):

**Session 1:** 2 Medium problems from different topics
**Session 2:** 1 Medium + 1 Hard (Google-style)
**Session 3:** 3 Easy-Medium rapid fire (Meta-style)

Rules for simulation:
```
✓ No hints, no looking at solutions
✓ Speak your thought process aloud
✓ Start with brute force, optimize explicitly
✓ State time/space complexity
✓ Handle edge cases (null, empty, single element, max constraints)
✓ Mention test cases you'd write
```

### Day 7: Rest & Review
- [ ] Review your personal pattern cheat sheet
- [ ] Verify you can write these 10 templates from memory:
  - [ ] BFS / DFS
  - [ ] Sliding window
  - [ ] Binary search (exact + on answer)
  - [ ] Union-Find
  - [ ] Topological sort (Kahn's)
  - [ ] Dijkstra
  - [ ] Trie
  - [ ] LRU Cache
  - [ ] Backtracking
  - [ ] 2D DP traversal

---

## Interview Day Checklist

### Before the Interview
- [ ] Sleep 7–8 hours (seriously — it affects pattern recognition)
- [ ] Review your personal cheat sheet (not new problems)
- [ ] Warm up with 1–2 Easy problems

### During the Interview
```
1. Read carefully (2–3 min) — don't code immediately
2. Clarify constraints: input size, duplicates, negative numbers?
3. State your approach out loud before coding
4. Write the brute force first if unsure
5. Optimize step by step, naming the optimization
6. Code cleanly — good variable names
7. Walk through example once coded
8. Mention edge cases: [], [1], all same elements
9. State final time/space complexity
```

### After Each Round
- [ ] Note what you were asked (for future reference)
- [ ] Note what you did well / could improve

---

## Templates Quick Reference Card

Save this card and review before every session.

```
# SLIDING WINDOW
l = 0
for r in range(n):
    # expand window
    while invalid:
        # shrink window
        l += 1
    # update answer

# BINARY SEARCH (on answer)
l, r = min_val, max_val
while l < r:
    mid = (l + r) // 2
    if feasible(mid): r = mid
    else: l = mid + 1

# UNION-FIND
parent = list(range(n))
def find(x): parent[x] = find(parent[x]) if parent[x]!=x else x; return parent[x]
def union(x,y): parent[find(x)] = find(y)

# BFS
from collections import deque
q = deque([start]); visited = {start}
while q:
    node = q.popleft()
    for nbr in graph[node]:
        if nbr not in visited:
            visited.add(nbr); q.append(nbr)

# BACKTRACKING
def bt(start, path):
    if done: result.append(path[:]); return
    for i in range(start, n):
        if skip(i): continue
        path.append(arr[i])
        bt(i+1, path)
        path.pop()

# TRIE
class Node:
    def __init__(self): self.c={}; self.end=False

# LRU (Python)
from collections import OrderedDict
cache = OrderedDict()
cache.move_to_end(key); cache.popitem(last=False)

# 2D DP
dp = [[0]*(n+1) for _ in range(m+1)]
for i in range(1,m+1):
    for j in range(1,n+1):
        dp[i][j] = f(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
```
