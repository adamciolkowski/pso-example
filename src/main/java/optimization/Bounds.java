package optimization;

public class Bounds {

    private final double[] lower;
    private final double[] upper;

    public Bounds(double[] lower, double[] upper) {
        if(lower.length != upper.length)
            throw new IllegalArgumentException("Lower and upper bounds have different sizes");
        this.lower = lower;
        this.upper = upper;
    }

    public double lower(int dimension) {
        return lower[dimension];
    }

    public double upper(int dimension) {
        return upper[dimension];
    }
}
