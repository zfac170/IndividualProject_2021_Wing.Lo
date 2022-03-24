package algorithm.clustering;

import algorithm.clustering.distance.PairwiseDistance;
import algorithm.clustering.linkage.Linkage;

import java.util.List;

public abstract class HierarchicalClustering {
    protected Linkage linkage;

    public HierarchicalClustering(Linkage linkage) {
        this.linkage = linkage;
    }

    // return dendrogram encoding
    // see parameter Z in https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.dendrogram.html#scipy.cluster.hierarchy.dendrogram
    public abstract List<List<Double>> fit(PairwiseDistance distanceMethod);
}
