package optimization.singleobjective.pso;

import optimization.Bounds;
import optimization.ObjectiveFunction;

import static optimization.utils.Utils.constrict;

public class Particle {

    private double[] position;

    private double[] velocity;

    private double[] bestPosition;

    public Particle(double[] position, double[] velocity) {
        this(position, velocity, position);
    }

    protected Particle(double[] position, double[] velocity, double[] bestPosition) {
        this.position = position;
        this.velocity = velocity;
        this.bestPosition = bestPosition;
    }

    public void update(double[] bestSwarmPosition, Bounds bounds, ObjectiveFunction function, SwarmParams params) {
        updateVelocity(bestSwarmPosition, params);
        updatePosition(bounds);
        updateBestPosition(function);
    }

    protected void updateVelocity(double[] bestSwarmPosition, SwarmParams params) {
        for (int d = 0; d < velocity.length; d++) {
            velocity[d] = params.getInertiaFactor() * velocity[d] +
                    params.getPersonalWeight() * (bestPosition[d] - position[d]) +
                    params.getGlobalWeight() * (bestSwarmPosition[d] - position[d]);
        }
    }

    protected void updatePosition(Bounds bounds) {
        for (int d = 0; d < position.length; d++) {
            position[d] = constrict(position[d] + velocity[d], bounds.lower(d), bounds.upper(d));
        }
    }

    protected void updateBestPosition(ObjectiveFunction objectiveFunction) {
        if(objectiveFunction.fitness(position) < objectiveFunction.fitness(bestPosition)) {
            bestPosition = position;
        }
    }

    public double[] getBestPosition() {
        return bestPosition;
    }

    public double[] getPosition() {
        return position;
    }

    public double[] getVelocity() {
        return velocity;
    }
}
