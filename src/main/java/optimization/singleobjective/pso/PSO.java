package optimization.singleobjective.pso;

import optimization.Bounds;
import optimization.ObjectiveFunction;
import optimization.OptimizationProblem;
import optimization.Optimizer;
import optimization.Solution;
import optimization.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;

public class PSO implements Optimizer {

    private final int swarmSize;
    private final int numberOfIterations;
    private final SwarmParams params;

    private double[] bestPosition;
    private double bestFitness;

    private ObjectiveFunction objectiveFunction;

    public PSO(int swarmSize, int numberOfIterations, SwarmParams params) {
        this.swarmSize = swarmSize;
        this.numberOfIterations = numberOfIterations;
        this.params = params;
    }

    @Override
    public Solution optimize(OptimizationProblem problem) {
        objectiveFunction = problem.objectiveFunction();
        Collection<Particle> particles = initializeSwarm(problem.bounds());
        updateBest(particles);
        for (int i = 0; i < numberOfIterations; i++) {
            updateSwarm(particles, problem.bounds());
            updateBest(particles);
        }
        return new Solution(bestPosition, bestFitness);
    }

    private Collection<Particle> initializeSwarm(Bounds bounds) {
        Collection<Particle> particles = new ArrayList<>();
        for (int i = 0; i < swarmSize; i++) {
            particles.add(randomParticle(objectiveFunction.numberOfVariables(), bounds));
        }
        return particles;
    }

    private Particle randomParticle(int dimensions, Bounds bounds) {
        double[] position = new double[dimensions];
        for (int d = 0; d < position.length; d++) {
            position[d] = Utils.randomInRange(bounds.lower(d), bounds.upper(d));
        }
        double[] velocity = new double[dimensions];
        return new Particle(position, velocity);
    }

    private void updateBest(Collection<Particle> particles) {
        for (Particle particle : particles) {
            if(isBestInSwarm(particle)) {
                bestPosition = particle.getPosition();
                bestFitness = objectiveFunction.fitness(bestPosition);
            }
        }
    }

    private boolean isBestInSwarm(Particle particle) {
        return bestPosition == null || objectiveFunction.fitness(particle.getPosition()) < objectiveFunction.fitness(bestPosition);
    }

    private void updateSwarm(Collection<Particle> particles, Bounds bounds) {
        for (Particle particle : particles) {
            particle.update(bestPosition, bounds, objectiveFunction, params);
        }
    }
}
