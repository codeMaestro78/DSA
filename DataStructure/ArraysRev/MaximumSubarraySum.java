package DataStructure.ArraysRev;

public class MaximumSubarraySum {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum: " + maximumSubarraySum(arr));
    }

    public static int maximumSubarraySum(int []arr){
        if(arr == null || arr.length==0) return 0;

        int currMax=arr[0];
        int globalMax=arr[0];


        for(int i=1;i<arr.length;i++){
            currMax=Math.max(arr[i],currMax+arr[i]);
            globalMax=Math.max(globalMax,currMax);
        }
        return globalMax;
    }

}


//Kadane's Algorithm
// used to find the subarray which has the maximum possible sum.

//
//The key insight is that at each position in the array, we decide whether to:
//
//Extend the existing subarray by including the current element, OR
//Start a new subarray from the current element
