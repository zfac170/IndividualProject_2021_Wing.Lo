package algorithm.clustering.linkage;

import java.util.List;

public class GroupAvgLinkage extends Linkage{
    public GroupAvgLinkage(){
        super();
    }

    @Override
    public double clusterDistance(List<Integer> cluster1, List<Integer> cluster2){
        double total = 0;
        for (int u : cluster1) {
            for (int v: cluster2) {
                total += this.distanceMethod.distance(u,v);
            }
        }
        return total / ( cluster1.size() * cluster2.size() );
   }
}
