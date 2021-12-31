package algorithm.clustering.distance;

import jdk.jshell.spi.ExecutionControl;

public abstract class DistanceMethod {
    public abstract double distance(int i, int j);
    public double distance(double[] v, double[] u) {
        return 0;
    }
}
