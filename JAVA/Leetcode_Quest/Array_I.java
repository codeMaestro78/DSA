//  concatenate the array
import java.util.*;
class Array_I{

    public int[] getConcatenation(int []nums){
        int n = nums.length;
        int []ans = new int[n*2];


        for(int i =0;i<n ;i++){
            ans[i]= nums[i];
            ans[i+n]=nums[i];
        }
        return ans;
    }

    public static void main(String []args){
        Array_I obj = new Array_I();
        int []nums = {1,2,1};
          System.out.println(Arrays.toString(obj.getConcatenation(nums)));
    }
}
