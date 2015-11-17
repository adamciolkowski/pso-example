package optimization;

import java.util.Arrays;

public class Solution {

    private final double[] position;
    private final double fitness;

    public Solution(double[] position, double fitness) {
        this.position = position;
        this.fitness = fitness;
    }

    public double[] getPosition() {
        return position;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        if (Double.compare(solution.fitness, fitness) != 0) return false;
        return Arrays.equals(position, solution.position);
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(fitness);
        return 31 * Arrays.hashCode(position) + (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Solution{" +
                "position=" + Arrays.toString(position) +
                ", fitness=" + fitness +
                '}';
    }
}
