package algorithm.clustering.distance;

public class MatrixDistance extends DistanceMethod {
    private double[][] matrix;

    public MatrixDistance(double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public double distance(int i, int j) {
        return matrix[i][j];
    }
}
