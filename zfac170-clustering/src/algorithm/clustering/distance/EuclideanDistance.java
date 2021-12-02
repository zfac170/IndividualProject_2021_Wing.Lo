package algorithm.clustering.distance;

public class EuclideanDistance extends Distance {
    private double[][] vectors;

    public EuclideanDistance(double[][] vectors) {
        this.vectors = vectors;
    }

    @Override
    public double distance(int i, int j) {
        double[] v1 = vectors[i];
        double[] v2 = vectors[j];
        double sum = 0;
        for (int d = 0; d < v1.length; ++d) {
            sum += Math.pow(v1[d] - v2[d], 2);
        }
        return Math.sqrt(sum);
    }
}
