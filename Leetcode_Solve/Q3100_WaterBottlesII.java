package Leetcode_Solve;

public class Q3100_WaterBottlesII {
    public static void main(String[] args) {

    }

    public static int maxBottlesDrunk(int numBottles, int numExchange) {
        int drunk = numBottles;
        int empties = numBottles;

        while (empties >= numExchange) {
            empties -= numExchange;
            numExchange++;
            drunk++;
            empties++;

        }
        return drunk;
    }
}
