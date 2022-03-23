package algorithm.clustering;

import algorithm.clustering.distance.PairwiseDistance;
import algorithm.clustering.linkage.Linkage;

import java.util.List;

public abstract class HierarchicalClustering {
    protected Linkage linkage;

    public HierarchicalClustering(Linkage linkage) {
        this.linkage = linkage;
    }

    public abstract BinaryTree fit(PairwiseDistance distanceMethod);
}
