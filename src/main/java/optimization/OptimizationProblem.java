package optimization;

public interface OptimizationProblem {

    ObjectiveFunction objectiveFunction();

    Bounds bounds();
}
