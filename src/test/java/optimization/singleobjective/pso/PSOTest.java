package optimization.singleobjective.pso;

import optimization.Bounds;
import optimization.ObjectiveFunction;
import optimization.OptimizationProblem;
import optimization.Solution;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.data.Offset.offset;

public class PSOTest {

    ObjectiveFunction objectiveFunction = new ObjectiveFunction() {
        @Override
        public int numberOfVariables() {
            return 1;
        }

        @Override
        public double fitness(double[] x) {
            return x[0] * x[0] + 4;
        }
    };

    Bounds bounds = new Bounds(new double[]{-5}, new double[]{5});

    OptimizationProblem optimizationProblem = new OptimizationProblem() {
        @Override
        public ObjectiveFunction objectiveFunction() {
            return objectiveFunction;
        }

        @Override
        public Bounds bounds() {
            return bounds;
        }
    };

    @Test
    public void shouldOptimizeFunction() {
        PSO pso = new PSO(100, 100, new SwarmParams(0.8, 0.2, 0.2));

        Solution solution = pso.optimize(optimizationProblem);

        assertThat(solution.getPosition()).hasSize(1);
        assertThat(solution.getPosition()[0]).isEqualTo(0.0, offset(1e-3));
        assertThat(solution.getFitness()).isEqualTo(4.0, offset(1e-3));
    }
}