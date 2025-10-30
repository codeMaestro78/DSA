package DataStructure.ArraysRev;

public class MaximumSubarraySum {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum: " + maximumSubarraySum(arr));

    }

    public static int maximumSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int currMax = arr[0];
        int globalMax = arr[0];


        for (int i = 1; i < arr.length; i++) {
            currMax = Math.max(arr[i], currMax + arr[i]);
            globalMax = Math.max(globalMax, currMax);
        }
        return globalMax;
    }


}


class kadaneAlgo {
    public static class Result {
        int maxSum;
        int startIndex;
        int endIndex;


        public Result(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return String.format("Max Sum: %d,Start Index: %d,End Index: %d", maxSum, startIndex, endIndex);
        }

        public static Result maxSubarrayWithIndices(int[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("array cannot be null or empty");
            }

            int currMax = arr[0];
            int globalMax = arr[0];
            int currStart = 0;
            int globalStart = 0;
            int globalEnd = 0;

            for (int i = 1; i < arr.length; i++) {
                if (currMax < 0) {
                    currMax = arr[i];
                    currStart = i;
                } else {
                    currMax += arr[i];
                }

                if (currMax > globalMax) {
                    globalMax = currMax;
                    globalStart = currStart;
                    globalEnd = i;
                }

            }
            return new Result(globalMax, globalStart, globalEnd);
        }
        public static void main(String []args){
            int []arr={-2, 1, -3, 4, -1, 2, 1, -5, 4};
            Result result = maxSubarrayWithIndices(arr);

            System.out.println(result);
            System.out.print("Subarray: [");
            for(int i= result.startIndex;i< result.endIndex;i++){
                System.out.print(arr[i]+(i<result.endIndex ?", ":""));
            }
            System.out.println("]");
        }
    }
}


//Kadane's Algorithm
// used to find the subarray which has the maximum possible sum.

//
//The key insight is that at each position in the array, we decide whether to:
//
//Extend the existing subarray by including the current element, OR
//Start a new subarray from the current element
