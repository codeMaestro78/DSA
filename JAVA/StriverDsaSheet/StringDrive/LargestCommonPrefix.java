package StriverDsaSheet.StringDrive;

import java.util.Arrays;

public class LargestCommonPrefix {
    public static void main(String[] args) {

    }

    public static String largestCommon(String[] s) {
        if (s == null || s.length == 0) return "";

        String first = s[0];
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);

            for (int j = 1; j < s.length; i++) {
                if (i == s[j].length() || s[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        return first;

    }

//    Alternative using sorting

    public static String largestCommon2(String[] str) {
        Arrays.sort(str);

        String first = str[0];
        String last = str[str.length - 1];
        int i = 0;

        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        return first.substring(0, i);

    }
}
