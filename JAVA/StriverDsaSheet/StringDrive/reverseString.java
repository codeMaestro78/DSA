package StriverDsaSheet.StringDrive;

public class reverseString {
    public static void main(String[] args) {
        String s = "this is an amazing program";
        System.out.println(reverseWords(s));

    }

    public static String palindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static String reverseWords(String s) {
        if (s == null || s.isEmpty()) return "";
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if(i!=0) sb.append(" ");
        }
        return sb.toString();
    }

}
