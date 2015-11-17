package optimization.singleobjective.pso;

import optimization.Bounds;
import optimization.ObjectiveFunction;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParticleTest {

    SwarmParams params = new SwarmParams(0.8, 0.2, 0.2);

    Bounds infiniteBounds = new Bounds(new double[]{Double.NEGATIVE_INFINITY},
                                       new double[]{Double.POSITIVE_INFINITY});

    ObjectiveFunction function = mock(ObjectiveFunction.class);

    @Test
    public void shouldUpdateVelocity() {
        double[] position = {1};
        double[] velocity = {2};
        double[] bestPosition = {3};
        Particle particle = new Particle(position, velocity, bestPosition);

        double[] bestSwarmPosition = {4};
        particle.updateVelocity(bestSwarmPosition, params);

        assertThat(particle.getVelocity()).isEqualTo(new double[]{0.8 * 2 + 0.2 * (3 - 1) + 0.2 * (4 - 1)});
    }

    @Test
    public void shouldUpdatePosition() {
        double[] position = {1};
        double[] velocity = {2};
        Particle particle = new Particle(position, velocity);

        particle.updatePosition(infiniteBounds);

        assertThat(particle.getPosition()).isEqualTo(new double[]{3});
    }

    @Test
    public void shouldUConstrictPositionWhenOutsideBounds() {
        double[] position = {2};
        double[] velocity = {5};
        Particle particle = new Particle(position, velocity);

        particle.updatePosition(new Bounds(new double[]{-5}, new double[]{5}));

        assertThat(particle.getPosition()).isEqualTo(new double[]{5});
    }

    @Test
    public void shouldUpdateBestPositionIfCurrentIsBetter() {
        double[] position = {1};
        double[] velocity = {2};
        double[] bestPosition = {3};
        Particle particle = new Particle(position, velocity, bestPosition);

        when(function.fitness(position)).thenReturn(6.0);
        when(function.fitness(bestPosition)).thenReturn(7.0);

        particle.updateBestPosition(function);

        assertThat(particle.getBestPosition()).isEqualTo(new double[]{1});
    }

    @Test
    public void shouldDoNothingWhenCurrentPositionIsWorseThanBestPosition() {
        double[] position = {1};
        double[] velocity = {2};
        double[] bestPosition = {3};
        Particle particle = new Particle(position, velocity, bestPosition);

        when(function.fitness(position)).thenReturn(7.0);
        when(function.fitness(bestPosition)).thenReturn(6.0);

        particle.updateBestPosition(function);

        assertThat(particle.getBestPosition()).isEqualTo(new double[]{3});
    }

    @Test
    public void shouldUpdateParticle() {
        double[] position = {1};
        double[] velocity = {2};
        double[] bestPosition = {3};
        double newVelocity = 0.8 * 2 + 0.2 * (3 - 1) + 0.2 * (4 - 1);
        Particle particle = new Particle(position, velocity, bestPosition);

        when(function.fitness(new double[]{newVelocity})).thenReturn(6.0);
        when(function.fitness(bestPosition)).thenReturn(7.0);

        double[] bestSwarmPosition = {4};
        particle.update(bestSwarmPosition, infiniteBounds, function, params);


        assertThat(particle.getVelocity()).isEqualTo(new double[]{newVelocity});
        assertThat(particle.getPosition()).isEqualTo(new double[]{1 + newVelocity});
        assertThat(particle.getBestPosition()).isEqualTo(new double[]{1 + newVelocity});
    }
}