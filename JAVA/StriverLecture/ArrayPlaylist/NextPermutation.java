package StriverLecture.ArrayPlaylist;

import java.util.*;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = { 2, 1, 3 };
        int[] ans = bruteForce(nums);
        System.out.println(Arrays.toString(ans));


        int []nums1 = { 1, 3, 5, 4, 2 };
        optimalSolution(nums1);

        for (int num : nums1) {
            System.out.println(num+ " ");
        }
    }

    // Generate permutations : O(n × n!)
    // Sort permutations : O(n × n! log(n!))
    // Find current : O(n!)

    // Total                 : O(n × n! log(n!))

// Space                 : O(n × n!)

    public static int[] bruteForce(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        generatePermutations(0, nums, permutations);

        // sort all the permutations lexiographically
        Collections.sort(permutations, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });

        // convert original array to List<Integer>
        List<Integer> current = new ArrayList<>();
        for (int num : nums) {
            current.add(num);
        }

        // find current permuatations

        for (int i = 0; i < permutations.size(); i++) {
            if (permutations.get(i).equals(current)) {
                // if the last permutation , return the first permutations

                if (i == permutations.size() - 1) {
                    return listToArray(permutations.get(0));
                }

                return listToArray(permutations.get(i + 1));
            }
        }
        return nums;
    }


    private static void generatePermutations(int index, int[] nums, List<List<Integer>> permutations) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();

            for (int num : nums) {
                temp.add(num);
            }

            permutations.add(temp);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            generatePermutations(index + 1, nums, permutations);
            swap(nums, index, i); // backtrack
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private static int[] listToArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // most Optimal solution


    public static void optimalSolution(int[] nums) {
        int n = nums.length;

        // find the pivot
        int pivot = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // if no pivot exists reverse the whole array.
        if (pivot == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // find the smallest element greater then pivot

        for (int i = n - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                swap(nums, i, pivot);
                break;
            }
        }

        // reverse the suffix

        reverse(nums, pivot + 1, n - 1);
    }


    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }




}
