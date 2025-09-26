package Leetcode_Solve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Q3408_DesignTaskManager {
    // Map: taskId -> [userId, priority]
    private Map<Integer, int[]> taskMap;
    // Max-Heap: (priority, taskId, userId)
    private PriorityQueue<int[]> maxHeap;

    public Q3408_DesignTaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();

        maxHeap = new PriorityQueue<>((a, b) -> {
            if (b[0] != a[0]) return b[0] - a[0];
            return b[1] - a[1];
        });

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            add(userId, taskId, priority);
        }
    }

    // Add a new task
    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[]{userId, priority});
        maxHeap.offer(new int[]{priority, taskId, userId});
    }

    // Edit task priority
    public void edit(int taskId, int newPriority) {
        if (!taskMap.containsKey(taskId)) return;
        int userId = taskMap.get(taskId)[0];
        taskMap.put(taskId, new int[]{userId, newPriority});
        maxHeap.offer(new int[]{newPriority, taskId, userId});
    }

    // Remove a task
    public void rmv(int taskId) {
        taskMap.remove(taskId); // lazy removal, old entries in heap become invalid
    }

    // Execute highest priority task
    public int execTop() {
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int priority = top[0], taskId = top[1], userId = top[2];

            // Validate with taskMap (lazy deletion)
            if (taskMap.containsKey(taskId)) {
                int[] current = taskMap.get(taskId);
                if (current != null && current[1] == priority) {
                    taskMap.remove(taskId); // remove after execution
                    return current[0];
                }
            }
        }
        return -1; // No tasks available
    }
}

