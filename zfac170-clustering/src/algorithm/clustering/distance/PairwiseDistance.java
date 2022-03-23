package algorithm.clustering.distance;

public abstract class PairwiseDistance {
    public abstract int size();
    public abstract double distance(int i, int j);
}
