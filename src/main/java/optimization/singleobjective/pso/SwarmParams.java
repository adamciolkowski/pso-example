package optimization.singleobjective.pso;

public class SwarmParams {

    private final double inertiaFactor;
    private final double personalWeight;
    private final double globalWeight;

    public SwarmParams(double inertiaFactor, double personalWeight, double globalWeight) {
        this.inertiaFactor = inertiaFactor;
        this.personalWeight = personalWeight;
        this.globalWeight = globalWeight;
    }

    public double getInertiaFactor() {
        return inertiaFactor;
    }

    public double getPersonalWeight() {
        return personalWeight;
    }

    public double getGlobalWeight() {
        return globalWeight;
    }
}