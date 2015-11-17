package optimization;

public interface ObjectiveFunction {

    int numberOfVariables();

    double fitness(double[] vars);
}
