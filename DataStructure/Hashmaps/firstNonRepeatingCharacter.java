package DataStructure.Hashmaps;

import java.util.HashMap;

public class firstNonRepeatingCharacter {
    public static void main(String[] args) {
        String s = "ssssssssssssssssssssssssss";
        char result = nonRepeating(s);
        if (result != '-') {
            System.out.println(result);
        } else {
            System.out.println("No unique character");
        }
    }

    public static char nonRepeating(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {
            if (freq.get(c) == 1) {
                return c;
            }
        }
        return '-';
    }
}
