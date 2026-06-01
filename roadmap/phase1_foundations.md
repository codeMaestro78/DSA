# Phase 1 — Foundations (Weeks 1–6)

> **Focus:** Master the patterns that appear in 80% of all interviews.
> These topics alone cover the bulk of Easy/Medium questions at every FAANG company.

---

## Week 1 — Arrays & Strings
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Companies:** Google, Meta, Amazon, Microsoft

### Concepts to Learn
- [ ] Array traversal, in-place operations
- [ ] Kadane's algorithm (max subarray)
- [ ] String manipulation — palindrome, anagram, reversal
- [ ] Subarray sum / product patterns
- [ ] Rotation tricks (left/right rotate)
- [ ] Dutch National Flag (3-way partition)

### LeetCode Problems
- [ ] [1] Two Sum — Easy
- [ ] [26] Remove Duplicates from Sorted Array — Easy
- [ ] [53] Maximum Subarray — Medium *(Kadane's)*
- [ ] [121] Best Time to Buy and Sell Stock — Easy
- [ ] [217] Contains Duplicate — Easy
- [ ] [238] Product of Array Except Self — Medium ⭐
- [ ] [42] Trapping Rain Water — Hard *(revisit in Week 2)*
- [ ] [3] Longest Substring Without Repeating Characters — Medium
- [ ] [443] String Compression — Medium
- [ ] [75] Sort Colors — Medium *(Dutch National Flag)*

### Key Patterns
```
- Prefix product array: O(n) without division
- Kadane's: track local_max = max(nums[i], local_max + nums[i])
- In-place two-pass for "move zeros" type problems
```

---

## Week 2 — Two Pointers / Sliding Window
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Companies:** All

### Concepts to Learn
- [ ] Two pointers — opposite ends (sorted arrays)
- [ ] Two pointers — same direction (fast/slow)
- [ ] Fixed-size sliding window
- [ ] Variable-size sliding window with HashMap/Set
- [ ] Minimum window substring pattern

### LeetCode Problems
- [ ] [125] Valid Palindrome — Easy
- [ ] [167] Two Sum II — Medium
- [ ] [11] Container With Most Water — Medium ⭐
- [ ] [15] 3Sum — Medium ⭐
- [ ] [209] Minimum Size Subarray Sum — Medium
- [ ] [76] Minimum Window Substring — Hard ⭐
- [ ] [567] Permutation in String — Medium
- [ ] [424] Longest Repeating Character Replacement — Medium ⭐
- [ ] [239] Sliding Window Maximum — Hard *(deque)*
- [ ] [904] Fruit Into Baskets — Medium

### Key Patterns
```
Sliding Window Template:
  left = 0
  for right in range(len(s)):
      window.add(s[right])
      while window_invalid:
          window.remove(s[left])
          left++
      update_answer

Two Pointer Template (sorted):
  l, r = 0, len(arr)-1
  while l < r:
      if condition: l++
      else: r--
```

---

## Week 3 — Hash Tables & Sets
**Priority:** ⭐⭐⭐⭐⭐ Very High | **Companies:** All

### Concepts to Learn
- [ ] HashMap for O(1) lookup / frequency counting
- [ ] HashSet for O(1) existence check
- [ ] Grouping with HashMap (anagrams, etc.)
- [ ] Counting pattern (character frequency)
- [ ] Two-sum variant using complement lookup

### LeetCode Problems
- [ ] [242] Valid Anagram — Easy
- [ ] [49] Group Anagrams — Medium ⭐
- [ ] [347] Top K Frequent Elements — Medium ⭐ *(bucket sort)*
- [ ] [128] Longest Consecutive Sequence — Medium ⭐
- [ ] [383] Ransom Note — Easy
- [ ] [387] First Unique Character in String — Easy
- [ ] [290] Word Pattern — Easy
- [ ] [205] Isomorphic Strings — Easy
- [ ] [36] Valid Sudoku — Medium
- [ ] [560] Subarray Sum Equals K — Medium ⭐ *(prefix sum + hashmap)*

### Key Patterns
```
Frequency map:  freq = Counter(s)
Complement:     seen = {}; if target-x in seen: found
Prefix sum:     prefix_count[running_sum - k] gives subarray count
```

---

## Week 4 — Linked Lists
**Priority:** ⭐⭐⭐⭐ High | **Companies:** Google, Amazon, Microsoft

### Concepts to Learn
- [ ] Dummy node technique
- [ ] Fast & slow pointer (Floyd's cycle detection)
- [ ] Reverse a linked list (iterative + recursive)
- [ ] Merge two sorted lists
- [ ] Find middle node
- [ ] Delete Nth node from end

### LeetCode Problems
- [ ] [206] Reverse Linked List — Easy ⭐
- [ ] [21] Merge Two Sorted Lists — Easy ⭐
- [ ] [141] Linked List Cycle — Easy ⭐
- [ ] [142] Linked List Cycle II — Medium ⭐ *(find start of cycle)*
- [ ] [19] Remove Nth Node From End — Medium
- [ ] [876] Middle of Linked List — Easy
- [ ] [143] Reorder List — Medium ⭐
- [ ] [23] Merge K Sorted Lists — Hard *(heap-based)*
- [ ] [25] Reverse Nodes in K-Group — Hard
- [ ] [148] Sort List — Medium *(merge sort on LL)*

### Key Patterns
```
Dummy node prevents null edge cases on head deletion

Fast/Slow cycle:
  fast = slow = head
  while fast and fast.next:
      slow = slow.next
      fast = fast.next.next
  # slow is at middle (or cycle meeting point)

Reverse LL:
  prev, curr = None, head
  while curr:
      next = curr.next
      curr.next = prev
      prev, curr = curr, next
```

---

## Week 5 — Stacks & Queues
**Priority:** ⭐⭐⭐⭐ High | **Companies:** All

### Concepts to Learn
- [ ] Monotonic stack (next greater/smaller element)
- [ ] Stack for parenthesis / bracket matching
- [ ] Stack-based expression evaluation
- [ ] Queue using two stacks
- [ ] Deque for sliding window maximum
- [ ] BFS with queue

### LeetCode Problems
- [ ] [20] Valid Parentheses — Easy ⭐
- [ ] [155] Min Stack — Medium ⭐
- [ ] [150] Evaluate Reverse Polish Notation — Medium
- [ ] [739] Daily Temperatures — Medium ⭐ *(monotonic stack)*
- [ ] [84] Largest Rectangle in Histogram — Hard ⭐
- [ ] [85] Maximal Rectangle — Hard *(extends 84)*
- [ ] [232] Implement Queue using Stacks — Easy
- [ ] [225] Implement Stack using Queues — Easy
- [ ] [456] 132 Pattern — Medium *(monotonic stack)*
- [ ] [503] Next Greater Element II — Medium

### Key Patterns
```
Monotonic Stack (next greater):
  stack = []
  for i, val in enumerate(nums):
      while stack and nums[stack[-1]] < val:
          result[stack.pop()] = val
      stack.append(i)

Min Stack: maintain auxiliary min_stack tracking current minimum
```

---

## Week 6 — Binary Search & Sorting
**Priority:** ⭐⭐⭐⭐ High | **Companies:** Google, Meta, Amazon

### Concepts to Learn
- [ ] Classic binary search (exact match)
- [ ] Binary search on answer (search space, not array)
- [ ] Find first/last occurrence (bisect_left/right)
- [ ] Rotated sorted array search
- [ ] Merge sort, quick sort understanding
- [ ] Custom comparator sort

### LeetCode Problems
- [ ] [704] Binary Search — Easy
- [ ] [33] Search in Rotated Sorted Array — Medium ⭐
- [ ] [153] Find Minimum in Rotated Sorted Array — Medium ⭐
- [ ] [74] Search a 2D Matrix — Medium
- [ ] [35] Search Insert Position — Easy
- [ ] [162] Find Peak Element — Medium
- [ ] [875] Koko Eating Bananas — Medium ⭐ *(binary search on answer)*
- [ ] [1011] Capacity To Ship Packages Within D Days — Medium ⭐
- [ ] [4] Median of Two Sorted Arrays — Hard ⭐
- [ ] [315] Count of Smaller Numbers After Self — Hard

### Key Patterns
```
Binary search template (avoid infinite loops):
  l, r = 0, len(arr) - 1
  while l <= r:
      mid = l + (r - l) // 2
      if arr[mid] == target: return mid
      elif arr[mid] < target: l = mid + 1
      else: r = mid - 1

Binary search on answer:
  l, r = min_possible, max_possible
  while l < r:
      mid = (l + r) // 2
      if feasible(mid): r = mid
      else: l = mid + 1
```

---

## Phase 1 Checkpoint ✅

Before moving to Phase 2, verify:
- [ ] Can solve any Easy Array/String in < 10 minutes
- [ ] Can identify sliding window vs two-pointer pattern immediately
- [ ] HashMap-based solutions feel natural (complement, frequency, grouping)
- [ ] Dummy node + reverse LL + fast/slow pointer are muscle memory
- [ ] Monotonic stack intuition is clear
- [ ] Can apply binary search to non-trivial "search on answer" problems
