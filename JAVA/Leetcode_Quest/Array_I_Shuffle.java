import java.util.*;

class Array_I_Shuffle {
    public static void main(String[] args) {
        Array_I_Shuffle obj = new Array_I_Shuffle();
        int []arr = {2,5,1,3,4,7};
        System.out.println(Arrays.toString(obj.shuffle(arr,3)));
    }

    public static int[] shuffle(int []nums,int n ){
        int []res = new int[2*n];
        int index = 0;

        for (int i =0;i<n;i++){
            res[index++] = nums[i];
            res[index++] = nums[i+n];
        }
        return res;
    }
}
