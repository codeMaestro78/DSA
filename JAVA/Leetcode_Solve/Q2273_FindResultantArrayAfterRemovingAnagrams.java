package Leetcode_Solve;

import java.util.*;

public class Q2273_FindResultantArrayAfterRemovingAnagrams {
    public static void main(String[] args) {
        String []words={"abba","baba","bbaa","cd","cd"};
        System.out.println(removeAnagram(words));

    }
    public static List<String> removeAnagram(String []words){
        List<String> result=new ArrayList<>();
        String prevSorted="";
        for(String word:words){
            char []chars=word.toCharArray();
            Arrays.sort(chars);
            String sorted=new String(chars);
            if(!sorted.equals(prevSorted)){
                result.add(word);
                prevSorted=sorted;

            }
        }
        return result;
    }
}
