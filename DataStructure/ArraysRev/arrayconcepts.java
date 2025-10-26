package DataStructure.ArraysRev;

import java.util.Collections;
import java.util.List;

public class arrayconcepts {
}


//| Operation            | Time Complexity |                      Notes                                     |
//        | -------------------- | --------------- | ----------------------------------------- |
//        | Access / Update      | O(1)            | Direct index access                       |
//        | Search (unsorted)    | O(n)            | Linear scan                               |
//        | Insertion / Deletion | O(n)            | Needs shifting                            |
//        | Reverse Array        | O(n)            | Two-pointer swap                          |
//        | Rotate Array by 1    | O(n)            | Use temporary variable or reversal method |
//


//reverse an  array(two pointer)

class reverseArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5,6,7};
        int n = arr.length;
        int i = 0,
                j = n - 1;
        int k=3;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        for(int num:arr){
            System.out.print(num);
        }
    }

}
