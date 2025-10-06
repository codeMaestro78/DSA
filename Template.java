import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Template {

    public static void main(String[] args) throws IOException {
        // Fast I/O setup
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // Example: Read number of test cases
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            // Read input
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            // Add more input parsing as needed

            // Your solution logic here

            // Output result
            pw.println("Result: " + n);
        }

        pw.close();
        br.close();
    }

    // Helper method for fast array input
    static int[] readIntArray(BufferedReader br) throws IOException {
        String[] parts = br.readLine().split(" ");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    // Helper method for fast long array input
    static long[] readLongArray(BufferedReader br) throws IOException {
        String[] parts = br.readLine().split(" ");
        long[] arr = new long[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Long.parseLong(parts[i]);
        }
        return arr;
    }
}
