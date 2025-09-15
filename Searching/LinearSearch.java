package Searching;

public class LinearSearch {
    public static void main(String []args){
        int[] arr = {5, 2, 9, 1, 7};
        System.out.println(linearSearch(arr, 9));
    }
    static int linearSearch(int []arr,int target){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==target) return i;

            }
            return -1;
    }
}