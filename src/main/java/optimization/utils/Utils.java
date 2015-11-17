package optimization.utils;

import java.util.Random;

public class Utils {

    private static Random random;

    public static double randomInRange(double a, double b, Random r) {
        if (a > b)
            throw new IllegalArgumentException("Lower bound greater than upper bound: " + a + " > " + b);
        return a + (b - a) * r.nextDouble();
    }

    public static double randomInRange(double a, double b) {
        if (random == null)
            random = new Random();
        return randomInRange(a, b, random);
    }

    public static double constrict(double value, double min, double max) {
        if(value < min)
            return min;
        if(value > max)
            return max;
        return value;
    }
}
