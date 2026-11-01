package DataStructure.SlidingWindow;




// Minimum window substring
// Given two strings s and t,return the shortest substring of s such that every character in t,
// including duplicates,is
//  present in the substring.If such a substring does not exist,return an empty string"".
public class MinimumWindowSubstring {
    public static void main(String[] args) {

    }

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int[] need = new int[128];
        
    }
}
