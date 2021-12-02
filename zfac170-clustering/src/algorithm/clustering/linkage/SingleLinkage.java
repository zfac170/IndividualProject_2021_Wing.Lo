package algorithm.clustering.linkage;

import java.util.List;

public class SingleLinkage extends Linkage {
    public SingleLinkage(){
        super();
    }

    @Override
    public double clusterDistance(List<Integer> cluster1, List<Integer> cluster2) {
        double shortest = Double.MAX_VALUE;
        for (int u : cluster1) {
            for (int v: cluster2) {
                shortest = Math.min(shortest, this.distance.distance(u, v));
            }
        }
        return shortest;
    }
}
