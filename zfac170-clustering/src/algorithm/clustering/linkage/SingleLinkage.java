package algorithm.clustering.linkage;

import java.util.Collection;

public class SingleLinkage extends Linkage {
    public SingleLinkage(){
        super();
    }

    @Override
    public double clusterDistance(Collection<Integer> cluster1, Collection<Integer> cluster2) {
        double shortest = Double.MAX_VALUE;
        for (int u : cluster1) {
            for (int v: cluster2) {
                shortest = Math.min(shortest, this.distanceMethod.distance(u, v));
            }
        }
        return shortest;
    }
}
