package algorithm.clustering.distance;

public class EuclideanDistance extends PairwiseDistance implements DistanceMethod {
    private double[][] vectors;

    public EuclideanDistance() {
    }

    public EuclideanDistance(double[][] vectors) {
        this.vectors = vectors;
    }

    @Override
    public int size() {
        return vectors.length;
    }

    @Override
    public double distance(int i, int j) {
        return distance(vectors[i], vectors[j]);
    }

    @Override
    public double distance(double[] v1, double[] v2) {
        double sum = 0;
        for (int d = 0; d < v1.length; ++d) {
            sum += Math.pow(v1[d] - v2[d], 2);
        }
        return Math.sqrt(sum);
    }
}
