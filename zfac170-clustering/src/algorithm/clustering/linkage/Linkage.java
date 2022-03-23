package algorithm.clustering.linkage;

import algorithm.clustering.distance.DistanceMethod;
import algorithm.clustering.distance.PairwiseDistance;

import java.util.List;
/*
Complete
Single
Group Average
 */
public abstract class Linkage{
    protected PairwiseDistance distanceMethod;

    public Linkage()  {
        this.distanceMethod = null;
    }

    public void setDistance(PairwiseDistance distanceMethod) {
        this.distanceMethod = distanceMethod;
    }

    public abstract double clusterDistance(List<Integer> cluster1, List<Integer> cluster2);

}

