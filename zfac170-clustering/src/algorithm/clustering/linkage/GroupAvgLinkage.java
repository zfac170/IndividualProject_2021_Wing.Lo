package algorithm.clustering.linkage;

import java.util.Collection;

public class GroupAvgLinkage extends Linkage{
    public GroupAvgLinkage(){
        super();
    }

    @Override
    public double clusterDistance(Collection<Integer> cluster1, Collection<Integer> cluster2){
        double total = 0;
        for (int u : cluster1) {
            for (int v: cluster2) {
                total += this.distanceMethod.distance(u,v);
            }
        }
        return total / ( cluster1.size() * cluster2.size() );
   }
}
