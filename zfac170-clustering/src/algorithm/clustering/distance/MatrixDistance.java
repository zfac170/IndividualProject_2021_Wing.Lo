package algorithm.clustering.distance;

public class MatrixDistance extends PairwiseDistance {
    private double[][] matrix;

    public MatrixDistance(double[][] matrix) {
        this.matrix = matrix;
        assert matrix.length == matrix[0].length;
    }

    @Override
    public double distance(int i, int j) {
        return matrix[i][j];
    }

    @Override
    public int size() {
        return matrix.length;
    }
}
