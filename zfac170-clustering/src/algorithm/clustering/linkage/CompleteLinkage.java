package algorithm.clustering.linkage;

import java.util.Collection;


public class CompleteLinkage extends Linkage{
    public CompleteLinkage(){
        super();
    }

    @Override
    public double clusterDistance(Collection<Integer> cluster1, Collection<Integer> cluster2){
        double longest = Double.MIN_VALUE;
        for (int u : cluster1) {
            for (int v: cluster2) {
                longest = Math.max(longest, this.distanceMethod.distance(u,v));
           }
        }
        return longest;
   }
}
