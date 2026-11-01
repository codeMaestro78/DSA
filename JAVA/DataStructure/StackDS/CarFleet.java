package DataStructure.StackDS;

import java.util.Arrays;

public class CarFleet {
    public static void main(String[] args) {

    }

    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        // store each car as {position , speed}

        int[][] cars = new int[n][2];

        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        // Sort cars by position on the descending order
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleets = 0;
        double lastFleettime = 0.0;

        for (int i = 0; i < n; i++) {
            int pos = cars[i][0];
            int spd = cars[i][1];

            double time = (double) (target - pos) / spd;

            // a new fleet forms if this cat needs more time than the fleet ahead.

            if (time > lastFleettime) {
                fleets++;
                lastFleettime = time;
            }

        }
        return fleets;
    }

}
