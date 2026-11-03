# DSA question index

## Scope and use

This Phase 2 diagnostic contains **32 original, company-neutral exercises** across 16 core patterns. Difficulty is editorial [INFERRED], not a platform rating. Target: early-career SDE/backend candidates, usually 0–2 years; practice coding round. Each linked guide contains the prompt, evaluation objective, expected reasoning, followup, common mistake, related material and source boundary. Attempt before opening its answer details block.

This historical core index focuses Beginner/Intermediate foundations. Phase 5 adds a [public problem catalog](PUBLIC_PROBLEM_CATALOG.md) and [broader practical banks](../12-Interview-Question-Bank/Question_Bank_Index.md) without counting these 32 exercises again. Company-question attribution/frequency remains unestablished.

| ID | Topic | Difficulty | Prompt | Guide |
|---|---|---|---|---|
| DSA-01-01 | Arrays and strings | Beginner | Answer many `[l,r)` range-sum queries over a fixed int array. | [01-Arrays-and-Strings/Guide.md](01-Arrays-and-Strings/Guide.md) |
| DSA-01-02 | Arrays and strings | Intermediate | Return the maximum sum of a nonempty contiguous subarray. | [01-Arrays-and-Strings/Guide.md](01-Arrays-and-Strings/Guide.md) |
| DSA-02-01 | Hashing and prefix-state lookup | Beginner | Given integers, return whether duplicates exist. | [02-Hashing/Guide.md](02-Hashing/Guide.md) |
| DSA-02-02 | Hashing and prefix-state lookup | Intermediate | Count nonempty subarrays summing to an int target, allowing negatives. | [02-Hashing/Guide.md](02-Hashing/Guide.md) |
| DSA-03-01 | Two pointers | Beginner | Compact a sorted array to one copy of each value and return its logical length. | [03-Two-Pointers/Guide.md](03-Two-Pointers/Guide.md) |
| DSA-03-02 | Two pointers | Intermediate | Find two distinct indices with a target sum in nondecreasing input. | [03-Two-Pointers/Guide.md](03-Two-Pointers/Guide.md) |
| DSA-04-01 | Sliding window | Beginner | Find the largest sum among contiguous windows of exactly k integers, with 1≤k≤n. | [04-Sliding-Window/Guide.md](04-Sliding-Window/Guide.md) |
| DSA-04-02 | Sliding window | Intermediate | Return the length of the longest substring with distinct UTF-16 code units. | [04-Sliding-Window/Guide.md](04-Sliding-Window/Guide.md) |
| DSA-05-01 | Binary search | Beginner | Return the first index whose sorted value is at least target, or n. | [05-Binary-Search/Guide.md](05-Binary-Search/Guide.md) |
| DSA-05-02 | Binary search | Intermediate | Find the smallest integer capacity allowing ordered nonnegative workloads to fit within d groups. | [05-Binary-Search/Guide.md](05-Binary-Search/Guide.md) |
| DSA-06-01 | Sorting as a transformation | Beginner | Sort records by score ascending and then ID ascending, allowing extreme int values. | [06-Sorting/Guide.md](06-Sorting/Guide.md) |
| DSA-06-02 | Sorting as a transformation | Intermediate | Count inversions in an array; equal values are not inversions. | [06-Sorting/Guide.md](06-Sorting/Guide.md) |
| DSA-07-01 | Linked lists | Beginner | Reverse an acyclic singly linked list using the original nodes. | [07-Linked-Lists/Guide.md](07-Linked-Lists/Guide.md) |
| DSA-07-02 | Linked lists | Intermediate | Reorder nodes L0,L1,…,Ln into L0,Ln,L1,Ln−1,… in place. | [07-Linked-Lists/Guide.md](07-Linked-Lists/Guide.md) |
| DSA-08-01 | Stacks, queues and monotonic state | Beginner | Validate a sequence containing only (), [] and {} brackets. | [08-Stacks-and-Queues/Guide.md](08-Stacks-and-Queues/Guide.md) |
| DSA-08-02 | Stacks, queues and monotonic state | Intermediate | For each value return its distance to the next strictly larger value, or zero. | [08-Stacks-and-Queues/Guide.md](08-Stacks-and-Queues/Guide.md) |
| DSA-09-01 | Trees and binary trees | Beginner | Compute the maximum depth of a finite binary tree, with empty depth 0. | [09-Trees-and-Binary-Trees/Guide.md](09-Trees-and-Binary-Trees/Guide.md) |
| DSA-09-02 | Trees and binary trees | Intermediate | Count nodes whose value is at least every ancestor value. | [09-Trees-and-Binary-Trees/Guide.md](09-Trees-and-Binary-Trees/Guide.md) |
| DSA-10-01 | Binary search trees | Beginner | Search for a key in a distinct-key BST. | [10-BST/Guide.md](10-BST/Guide.md) |
| DSA-10-02 | Binary search trees | Intermediate | Validate the entire strict BST invariant. | [10-BST/Guide.md](10-BST/Guide.md) |
| DSA-11-01 | Heaps and priority queues | Beginner | Return the kth largest value, counting duplicates, for 1≤k≤n. | [11-Heaps-and-Priority-Queues/Guide.md](11-Heaps-and-Priority-Queues/Guide.md) |
| DSA-11-02 | Heaps and priority queues | Intermediate | Merge m sorted streams into one sorted output. | [11-Heaps-and-Priority-Queues/Guide.md](11-Heaps-and-Priority-Queues/Guide.md) |
| DSA-12-01 | Graph traversal and shortest paths | Beginner | Return minimum edge counts from a source in a directed unweighted graph. | [12-Graphs/Guide.md](12-Graphs/Guide.md) |
| DSA-12-02 | Graph traversal and shortest paths | Intermediate | Determine whether all courses can be completed from prerequisite edges. | [12-Graphs/Guide.md](12-Graphs/Guide.md) |
| DSA-13-01 | Backtracking | Beginner | Generate all subsets of an array of distinct values. | [13-Backtracking/Guide.md](13-Backtracking/Guide.md) |
| DSA-13-02 | Backtracking | Intermediate | Generate unique subsets when values may repeat. | [13-Backtracking/Guide.md](13-Backtracking/Guide.md) |
| DSA-14-01 | Dynamic programming | Beginner | For nonnegative house rewards, maximize reward without selecting adjacent houses. | [14-Dynamic-Programming/Guide.md](14-Dynamic-Programming/Guide.md) |
| DSA-14-02 | Dynamic programming | Intermediate | Return the minimum coins for an amount with unlimited positive denominations, or -1. | [14-Dynamic-Programming/Guide.md](14-Dynamic-Programming/Guide.md) |
| DSA-15-01 | Greedy algorithms | Beginner | Maximize the number of compatible nonempty half-open intervals. | [15-Greedy/Guide.md](15-Greedy/Guide.md) |
| DSA-15-02 | Greedy algorithms | Intermediate | Explain why the same greedy rule fails when intervals have positive weights. | [15-Greedy/Guide.md](15-Greedy/Guide.md) |
| DSA-17-01 | Intervals and sweep-line reasoning | Beginner | Merge closed integer intervals into their union components. | [17-Intervals/Guide.md](17-Intervals/Guide.md) |
| DSA-17-02 | Intervals and sweep-line reasoning | Intermediate | Find minimum rooms for nonempty half-open bookings. | [17-Intervals/Guide.md](17-Intervals/Guide.md) |

## Phase 7 extension exercises

Six later pattern gaps now have full guides with **12 additional original exercises**. These are separate from the historical 32 Phase2 exercises, the 134 QB records and 22 public catalog entries. Difficulty remains editorial, not an employer/platform measure.

| IDs | Topic | Guide |
|---|---|---|
| DSA-16-01, DSA-16-02 | Tries | [Worked guide](16-Tries/Guide.md) |
| DSA-18-01, DSA-18-02 | Bit manipulation | [Worked guide](18-Bit-Manipulation/Guide.md) |
| DSA-19-01, DSA-19-02 | Math/number theory | [Worked guide](19-Math-and-Number-Theory/Guide.md) |
| DSA-20-01, DSA-20-02 | Fenwick/advanced structures | [Worked guide](20-Advanced-Algorithms/Guide.md) |
| DSA-21-01, DSA-21-02 | Concurrency | [Worked guide](21-Concurrency-and-Threading/Guide.md) |
| DSA-22-01, DSA-22-02 | SQL relational reasoning | [Worked guide](22-SQL-and-Data-Structures/Guide.md) |

## Review record

For each attempt record the ID, date, independence/hints, time, chosen invariant, complexity and failing/passing examples in [the tracker](../PROGRESS_TRACKER.md). On a second attempt, alter a constraint rather than reproducing a memorized implementation.

## References and last verified

[Pattern map](Pattern_Recognition.md), [source metadata](PHASE2_SOURCES.md), [company-question limits](DSA_Company_Question_Research.md).

Last reviewed: 2026-11-03; the historical 32 IDs plus 12 extension IDs link to 22 authored pattern guides.
