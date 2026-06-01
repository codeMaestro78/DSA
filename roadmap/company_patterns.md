# Company-Specific Interview Patterns

> Based on publicly reported interview experiences (Glassdoor, LeetCode discuss, Blind, Interview Query, interviewing.io)
> Updated patterns as of 2024–2025 hiring cycles.

---

## Google

### Interview Format
- 2–3 coding rounds (45 min each)
- 1 system design (L4+)
- Googliness / behavioral

### Frequently Asked Topics
| Rank | Topic | Notes |
|------|-------|-------|
| 1 | Graphs (BFS/DFS/Dijkstra) | Multi-source BFS very common |
| 2 | Trees (LCA, traversal, diameter) | Expect iterative variants |
| 3 | Dynamic Programming | LIS, LCS, Word Break, Coin Change |
| 4 | Binary Search | Always on answer, not just arrays |
| 5 | String manipulation | Palindromes, parsing, regex |
| 6 | Topological Sort | Task scheduling, dependency resolution |
| 7 | Backtracking | N-Queens, Word Search II |
| 8 | Tries | Autocomplete, dictionary matching |
| 9 | Union-Find | Connectivity, redundant connections |
| 10 | Bit Manipulation | Rare but appears in hard rounds |

### Google-Specific Problem Tags (reported on LeetCode)
- [ ] [1293] Shortest Path in Grid with Obstacles Elimination
- [ ] [269] Alien Dictionary
- [ ] [1192] Critical Connections in a Network *(Tarjan's bridges)*
- [ ] [295] Find Median from Data Stream
- [ ] [68] Text Justification
- [ ] [336] Palindrome Pairs
- [ ] [827] Making A Large Island
- [ ] [1235] Maximum Profit in Job Scheduling
- [ ] [465] Optimal Account Balancing *(bitmask DP)*
- [ ] [843] Guess the Word *(minimax)*

### What Google Looks For
```
✓ Optimal time AND space complexity
✓ Clean, readable code (they read it carefully)
✓ Edge case handling (null, empty, single-element, negative)
✓ Can you handle follow-ups? (they always ask)
✓ Communication — explain before you code
```

---

## Meta (Facebook)

### Interview Format
- 2 coding rounds (35 min each — faster than Google)
- 1 system design (E4+)
- Behavioral (values-based)

### Frequently Asked Topics
| Rank | Topic | Notes |
|------|-------|-------|
| 1 | Arrays & Strings | Speed expected — these should be fast |
| 2 | Two Pointers / Sliding Window | Very common in both rounds |
| 3 | Binary Trees | Level order, LCA, paths are favorites |
| 4 | Graphs (BFS) | Social graph traversals |
| 5 | Dynamic Programming | Classic 1D DP (simpler than Google's) |
| 6 | Backtracking | Permutations, subsets |
| 7 | Greedy | Interval scheduling |
| 8 | Hash Maps | Two Sum variants, grouping |

### Meta-Specific Problem Tags (reported)
- [ ] [408] Valid Word Abbreviation — Easy *(very common screen)*
- [ ] [301] Remove Invalid Parentheses — Hard
- [ ] [543] Diameter of Binary Tree — Easy
- [ ] [1249] Minimum Remove to Make Valid Parentheses — Medium ⭐
- [ ] [1570] Dot Product of Two Sparse Vectors — Medium
- [ ] [227] Basic Calculator II — Medium
- [ ] [953] Verifying an Alien Dictionary — Easy
- [ ] [199] Binary Tree Right Side View — Medium ⭐
- [ ] [347] Top K Frequent Elements — Medium
- [ ] [2] Add Two Numbers *(Linked List)* — Medium

### What Meta Looks For
```
✓ Speed — they value fast, correct solutions
✓ Clean working code over clever-but-broken
✓ Handling multiple follow-ups (they iterate the problem)
✓ Communication is critical — they assess "building the right thing"
✓ Test cases stated explicitly
```

---

## Amazon

### Interview Format
- 2 coding rounds (online assessment + 1 live)
- 1–2 system design (SDE II+)
- Leadership principles (behavioral — very important)

### Frequently Asked Topics
| Rank | Topic | Notes |
|------|-------|-------|
| 1 | Arrays & Strings | OA is almost always arrays |
| 2 | Heaps & Priority Queues | Top-K, scheduling |
| 3 | Greedy & Intervals | Meeting rooms, task scheduler |
| 4 | Trees | BFS level order, BST ops |
| 5 | Graphs | Connectivity, shortest path |
| 6 | Linked Lists | Reverse, cycle detection |
| 7 | Stacks & Queues | Expression evaluation |
| 8 | Sorting | Custom sort, merge |

### Amazon OA Common Patterns
- [ ] [42] Trapping Rain Water — Hard *(very very common)*
- [ ] [973] K Closest Points to Origin — Medium *(heap)*
- [ ] [200] Number of Islands — Medium
- [ ] [621] Task Scheduler — Medium *(greedy + heap)*
- [ ] [1] Two Sum — Easy *(appears surprisingly often)*
- [ ] [253] Meeting Rooms II — Medium
- [ ] [146] LRU Cache — Medium *(common in live round)*
- [ ] [23] Merge K Sorted Lists — Hard
- [ ] [297] Serialize and Deserialize Binary Tree — Hard
- [ ] [460] LFU Cache — Hard *(rare but appears)*

### Amazon Leadership Principles (Behavioral)
Prepare STAR stories for:
- [ ] Customer Obsession
- [ ] Ownership
- [ ] Invent and Simplify
- [ ] Are Right, A Lot
- [ ] Deliver Results

---

## Microsoft

### Interview Format
- 4–5 coding + design rounds (45 min each)
- Often has a hiring manager round
- Mix of Medium and Hard

### Frequently Asked Topics
| Rank | Topic | Notes |
|------|-------|-------|
| 1 | Arrays & Strings | Broad coverage |
| 2 | Trees | Traversals, BST, LCA |
| 3 | Graphs | Connected components, BFS |
| 4 | Linked Lists | Merge, reverse, cycle |
| 5 | Dynamic Programming | Classic problems |
| 6 | Stacks & Queues | Monotonic, bracket matching |
| 7 | Tries | Less frequent than Google |
| 8 | Binary Search | On arrays and answer space |

### Microsoft-Specific Problems (reported)
- [ ] [206] Reverse Linked List *(classic)*
- [ ] [102] Binary Tree Level Order Traversal
- [ ] [200] Number of Islands
- [ ] [297] Serialize Binary Tree
- [ ] [146] LRU Cache
- [ ] [98] Validate BST
- [ ] [236] LCA of Binary Tree
- [ ] [56] Merge Intervals
- [ ] [15] 3Sum
- [ ] [155] Min Stack

---

## Uber

### Interview Format
- 2–4 coding rounds
- Strong focus on system design (backend roles)
- Coding often involves maps/geo/optimization

### Frequently Asked Topics
| Rank | Topic | Notes |
|------|-------|-------|
| 1 | Graphs | Navigation, BFS/Dijkstra |
| 2 | Heaps | Scheduling, top-K |
| 3 | Arrays & Sorting | Custom comparators |
| 4 | Design (LRU/Rate Limiting) | Very important for backend |
| 5 | DP | Optimization problems |

### Uber-Specific Problems
- [ ] [743] Network Delay Time *(Dijkstra)*
- [ ] [253] Meeting Rooms II *(driver scheduling)*
- [ ] [692] Top K Frequent Words *(heap)*
- [ ] [621] Task Scheduler
- [ ] [146] LRU Cache
- [ ] [380] Insert Delete GetRandom O(1)

---

## Top Startups (Stripe, Airbnb, Databricks, Figma, Notion)

### General Pattern
- Startups tend to ask more practical / applied problems
- Often: implement a mini-feature, parse structured data, simulate a system
- Less "trick" problems, more "can you actually build things" problems

### Common Topics
| Topic | Notes |
|-------|-------|
| Arrays & Strings | Parsing, transformation |
| Hash Maps | Frequency, lookups |
| Design patterns | LRU, rate limiter |
| Graphs | Network analysis |
| Sorting | Custom business logic sort |

### Stripe-Specific Patterns (reported)
- Parsing / financial data manipulation
- Rate limiting (sliding window)
- Detecting duplicates in transactions
- Custom sort / priority scheduling

### Airbnb-Specific Patterns (reported)
- Calendar / booking systems (interval overlap)
- Geo / distance sorting
- [ ] [56] Merge Intervals
- [ ] [252] Meeting Rooms
- [ ] [759] Employee Free Time *(Hard — interval merge)*

---

## Topic × Company Frequency Matrix

| Topic | Google | Meta | Amazon | Microsoft | Uber |
|-------|--------|------|--------|-----------|------|
| Arrays & Strings | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| Two Pointers | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| Hash Tables | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| Linked Lists | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐ |
| Stacks & Queues | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Binary Search | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ |
| Greedy | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ |
| Trees | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| Graphs | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| Topo Sort | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Heaps | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐ |
| Classic DP | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| Grid DP | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Backtracking | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Tries | ⭐⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐ |
| Bit Manipulation | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐ |
| Design (LRU etc.) | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐ |
