package DataStructure.SlidingWindow;


// Neetcode
public class PermutationInString {
    public static void main(String[] args) {

    }

    // Brute force solution
    // we can generate all the substring

    public static boolean checkInclusion(String s1, String s2) {
        return generatePermutations(s1, "", s2);
    }

    private static boolean generatePermutations(String remaining, String current, String s2) {

        if (remaining.length() == 0) {
            return s2.contains(current);
        }

        for (int i = 0; i < remaining.length(); i++) {
            char ch = remaining.charAt(i);

            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);

            if (generatePermutations(newRemaining, current + ch, s2)) {
                return true;
            }
        }
        return false;
    }
}
